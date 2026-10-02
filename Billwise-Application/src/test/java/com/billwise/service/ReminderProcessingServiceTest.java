package com.billwise.service;

import com.billwise.entity.Invoice;
import com.billwise.entity.ReminderAttempt;
import com.billwise.entity.ReminderStatus;
import com.billwise.entity.ReminderType;
import com.billwise.repository.ReminderAttemptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReminderProcessingServiceTest {

    @Mock
    private ReminderAttemptRepository reminderAttemptRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ReminderProcessingService processingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessReminderSafely_Success() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(reminderAttemptRepository.findByInvoiceIdAndReminderTypeAndReminderPeriod(
                1L, ReminderType.DUE_TODAY, LocalDate.now())).thenReturn(Optional.empty());

        when(reminderAttemptRepository.saveAndFlush(any())).thenAnswer(i -> i.getArguments()[0]);

        boolean result = processingService.processReminderSafely(invoice, ReminderType.DUE_TODAY, LocalDate.now());

        assertTrue(result);
        verify(notificationService, times(1)).sendReminderNotification(any(), any());
    }

    @Test
    void testProcessReminderSafely_DuplicatePrevented() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        ReminderAttempt existingAttempt = new ReminderAttempt();
        existingAttempt.setStatus(ReminderStatus.SUCCESS);

        when(reminderAttemptRepository.findByInvoiceIdAndReminderTypeAndReminderPeriod(
                1L, ReminderType.DUE_TODAY, LocalDate.now())).thenReturn(Optional.of(existingAttempt));

        boolean result = processingService.processReminderSafely(invoice, ReminderType.DUE_TODAY, LocalDate.now());

        assertFalse(result); // Skipped
        verify(notificationService, never()).sendReminderNotification(any(), any());
    }

    @Test
    void testProcessReminderSafely_ConcurrentInsertThrowsException() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        when(reminderAttemptRepository.findByInvoiceIdAndReminderTypeAndReminderPeriod(
                1L, ReminderType.DUE_TODAY, LocalDate.now())).thenReturn(Optional.empty());

        when(reminderAttemptRepository.saveAndFlush(any())).thenThrow(new DataIntegrityViolationException("Duplicate"));

        boolean result = processingService.processReminderSafely(invoice, ReminderType.DUE_TODAY, LocalDate.now());

        assertFalse(result); // Skipped due to concurrent constraint failure
        verify(notificationService, never()).sendReminderNotification(any(), any());
    }

    @Test
    void testProcessReminderSafely_FailedNotificationRetries() {
        Invoice invoice = new Invoice();
        invoice.setId(1L);

        ReminderAttempt existingAttempt = new ReminderAttempt();
        existingAttempt.setStatus(ReminderStatus.FAILED); // Important: previous was FAILED

        when(reminderAttemptRepository.findByInvoiceIdAndReminderTypeAndReminderPeriod(
                1L, ReminderType.DUE_TODAY, LocalDate.now())).thenReturn(Optional.of(existingAttempt));

        when(reminderAttemptRepository.saveAndFlush(any())).thenAnswer(i -> i.getArguments()[0]);

        boolean result = processingService.processReminderSafely(invoice, ReminderType.DUE_TODAY, LocalDate.now());

        assertTrue(result); // It retried successfully
        verify(notificationService, times(1)).sendReminderNotification(any(), any());
    }
}
