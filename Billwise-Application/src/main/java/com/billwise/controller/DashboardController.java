package com.billwise.controller;

import com.billwise.dto.DashboardDto;
import com.billwise.entity.Invoice;
import com.billwise.entity.InvoiceStatus;
import com.billwise.repository.InvoiceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final InvoiceRepository invoiceRepository;

    public DashboardController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping
    public DashboardDto getDashboardStats() {
        System.out.println("GET /api/dashboard called! Fetching stats...");
        List<Invoice> invoices = invoiceRepository.findAll();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        DashboardDto dto = new DashboardDto();
        dto.setTotalInvoices(invoices.size());

        long dueToday = 0;
        long dueTomorrow = 0;
        long overdue = 0;
        long paid = 0;

        for (Invoice invoice : invoices) {
            if (invoice.getStatus() == InvoiceStatus.PAID) {
                paid++;
            } else {
                if (invoice.getDueDate().isEqual(today)) {
                    dueToday++;
                } else if (invoice.getDueDate().isEqual(tomorrow)) {
                    dueTomorrow++;
                } else if (invoice.getDueDate().isBefore(today)) {
                    overdue++;
                }
            }
        }

        dto.setDueToday(dueToday);
        dto.setDueTomorrow(dueTomorrow);
        dto.setOverdue(overdue);
        dto.setPaid(paid);

        return dto;
    }
}
