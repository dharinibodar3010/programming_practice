package com.billwise.repository;

import com.billwise.entity.Invoice;
import com.billwise.entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findByStatusNot(InvoiceStatus status);
}
