package com.billwise.service;

import com.billwise.entity.Invoice;
import com.billwise.entity.InvoiceStatus;
import com.billwise.entity.ReminderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReminderEligibilityServiceTest {

    private ReminderEligibilityService eligibilityService;

    @BeforeEach
    void setUp() {
        eligibilityService = new ReminderEligibilityService();
    }

    @Test
    void testPaidInvoiceIsSkipped() {
        Invoice invoice = new Invoice();
        invoice.setStatus(InvoiceStatus.PAID);
        invoice.setDueDate(LocalDate.now());

        assertFalse(eligibilityService.isEligible(invoice, ReminderType.DUE_TODAY, LocalDate.now()));
    }

    @Test
    void testUpcomingEligibility() {
        Invoice invoice = new Invoice();
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice.setDueDate(LocalDate.now().plusDays(2));

        assertTrue(eligibilityService.isEligible(invoice, ReminderType.UPCOMING, LocalDate.now()));
        assertFalse(eligibilityService.isEligible(invoice, ReminderType.UPCOMING, LocalDate.now().plusDays(3)));
    }

    @Test
    void testDueTodayEligibility() {
        Invoice invoice = new Invoice();
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice.setDueDate(LocalDate.now());

        assertTrue(eligibilityService.isEligible(invoice, ReminderType.DUE_TODAY, LocalDate.now()));
        assertFalse(eligibilityService.isEligible(invoice, ReminderType.DUE_TODAY, LocalDate.now().minusDays(1)));
    }

    @Test
    void testOverdueEligibility() {
        Invoice invoice = new Invoice();
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice.setDueDate(LocalDate.now().minusDays(2));

        assertTrue(eligibilityService.isEligible(invoice, ReminderType.OVERDUE, LocalDate.now()));
        assertFalse(eligibilityService.isEligible(invoice, ReminderType.OVERDUE, LocalDate.now().minusDays(3)));
    }
}
