package com.billwise.service;

import com.billwise.entity.Invoice;
import com.billwise.entity.InvoiceStatus;
import com.billwise.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
    }

    @Transactional
    public Invoice createInvoice(Invoice invoice) {
        if (invoiceRepository.findByInvoiceNumber(invoice.getInvoiceNumber()).isPresent()) {
            throw new RuntimeException("Invoice number already exists: " + invoice.getInvoiceNumber());
        }
        invoice.setStatus(InvoiceStatus.NEW);
        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Invoice updateInvoiceStatus(Long id, InvoiceStatus status) {
        Invoice invoice = getInvoiceById(id);
        invoice.setStatus(status);
        return invoiceRepository.save(invoice);
    }
}
