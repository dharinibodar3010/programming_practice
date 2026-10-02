package com.billwise.service;

import com.billwise.entity.*;
import com.billwise.repository.InvoiceRepository;
import com.billwise.repository.ReminderAttemptRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReminderProcessingService {

    private final ReminderAttemptRepository reminderAttemptRepository;
    private final NotificationService notificationService;

    public ReminderProcessingService(ReminderAttemptRepository reminderAttemptRepository,
                                     NotificationService notificationService) {
        this.reminderAttemptRepository = reminderAttemptRepository;
        this.notificationService = notificationService;
    }

    /**
     * Processes a single reminder safely.
     * Uses REQUIRES_NEW propagation to ensure each invoice reminder attempt commits independently.
     * This ensures that one failure doesn't rollback the entire job.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean processReminderSafely(Invoice invoice, ReminderType reminderType, LocalDate processingDate) {
        
        Optional<ReminderAttempt> existingAttemptOpt = reminderAttemptRepository
                .findByInvoiceIdAndReminderTypeAndReminderPeriod(invoice.getId(), reminderType, processingDate);

        ReminderAttempt attempt;
        if (existingAttemptOpt.isPresent()) {
            attempt = existingAttemptOpt.get();
            if (attempt.getStatus() == ReminderStatus.SUCCESS) {
                // Already processed successfully, skip.
                return false;
            }
        } else {
            attempt = new ReminderAttempt();
            attempt.setInvoice(invoice);
            attempt.setReminderType(reminderType);
            attempt.setReminderPeriod(processingDate);
        }

        attempt.setAttemptedAt(LocalDateTime.now());
        
        try {
            // Save initial attempt (or updated failure attempt)
            attempt = reminderAttemptRepository.saveAndFlush(attempt);
        } catch (DataIntegrityViolationException e) {
            // This catches concurrent insertions perfectly.
            // If another thread inserted the same attempt just now, we ignore this one.
            System.out.println("Duplicate attempt prevented for invoice: " + invoice.getInvoiceNumber());
            return false;
        }

        try {
            // Simulate notification logic
            notificationService.sendReminderNotification(invoice, reminderType.name());
            
            attempt.setStatus(ReminderStatus.SUCCESS);
            attempt.setFailureReason(null);
            attempt.setNotificationMessage("Notification sent successfully");
            reminderAttemptRepository.save(attempt);
            return true;

        } catch (Exception e) {
            attempt.setStatus(ReminderStatus.FAILED);
            attempt.setFailureReason(e.getMessage());
            reminderAttemptRepository.save(attempt);
            return false;
        }
    }
}
