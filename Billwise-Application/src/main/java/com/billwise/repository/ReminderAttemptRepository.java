package com.billwise.repository;

import com.billwise.entity.ReminderAttempt;
import com.billwise.entity.ReminderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReminderAttemptRepository extends JpaRepository<ReminderAttempt, Long> {
    Optional<ReminderAttempt> findByInvoiceIdAndReminderTypeAndReminderPeriod(Long invoiceId, ReminderType reminderType, LocalDate reminderPeriod);
    List<ReminderAttempt> findByInvoiceIdOrderByAttemptedAtDesc(Long invoiceId);
    List<ReminderAttempt> findTop10ByOrderByIdDesc();
}
