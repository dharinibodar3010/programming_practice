package com.billwise.controller;

import com.billwise.entity.Invoice;
import com.billwise.entity.InvoiceStatus;
import com.billwise.entity.ReminderAttempt;
import com.billwise.repository.ReminderAttemptRepository;
import com.billwise.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final ReminderAttemptRepository reminderAttemptRepository;

    public InvoiceController(InvoiceService invoiceService, ReminderAttemptRepository reminderAttemptRepository) {
        this.invoiceService = invoiceService;
        this.reminderAttemptRepository = reminderAttemptRepository;
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.createInvoice(invoice);
    }

    @PatchMapping("/{id}/status")
    public Invoice changeStatus(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        InvoiceStatus status = InvoiceStatus.valueOf(body.get("status"));
        return invoiceService.updateInvoiceStatus(id, status);
    }

    @PatchMapping("/{id}/mark-paid")
    public Invoice markAsPaid(@PathVariable Long id) {
        return invoiceService.updateInvoiceStatus(id, InvoiceStatus.PAID);
    }

    @GetMapping("/{id}/reminders")
    public List<ReminderAttempt> getRemindersForInvoice(@PathVariable Long id) {
        return reminderAttemptRepository.findByInvoiceIdOrderByAttemptedAtDesc(id);
    }
}
