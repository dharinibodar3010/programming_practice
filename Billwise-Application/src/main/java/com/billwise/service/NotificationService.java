package com.billwise.service;

import com.billwise.entity.Invoice;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendReminderNotification(Invoice invoice, String reminderType) {
        // Simulate real-world delay and operation
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Reminder notification simulated successfully for Invoice: " 
            + invoice.getInvoiceNumber() + ", Type: " + reminderType);
    }
}
