package com.billwise.service;

import com.billwise.entity.Invoice;
import com.billwise.entity.InvoiceStatus;
import com.billwise.entity.ReminderType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReminderEligibilityService {

    public boolean isEligible(Invoice invoice, ReminderType reminderType, LocalDate currentDate) {
        if (invoice.getStatus() == InvoiceStatus.PAID) {
            return false;
        }

        LocalDate dueDate = invoice.getDueDate();

        return switch (reminderType) {
            case UPCOMING -> currentDate.isBefore(dueDate); // Case study: before due date. We can say 1 or more days before.
            case DUE_TODAY -> currentDate.isEqual(dueDate);
            case OVERDUE -> currentDate.isAfter(dueDate);
        };
    }
}
