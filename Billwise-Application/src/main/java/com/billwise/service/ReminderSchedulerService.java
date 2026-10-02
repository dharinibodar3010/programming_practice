package com.billwise.service;

import com.billwise.entity.*;
import com.billwise.repository.InvoiceRepository;
import com.billwise.repository.JobExecutionRepository;
import com.billwise.repository.ScheduledJobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderSchedulerService {

    private final ScheduledJobRepository scheduledJobRepository;
    private final JobExecutionRepository jobExecutionRepository;
    private final InvoiceRepository invoiceRepository;
    private final ReminderEligibilityService reminderEligibilityService;
    private final ReminderProcessingService reminderProcessingService;

    public ReminderSchedulerService(ScheduledJobRepository scheduledJobRepository,
                                    JobExecutionRepository jobExecutionRepository,
                                    InvoiceRepository invoiceRepository,
                                    ReminderEligibilityService reminderEligibilityService,
                                    ReminderProcessingService reminderProcessingService) {
        this.scheduledJobRepository = scheduledJobRepository;
        this.jobExecutionRepository = jobExecutionRepository;
        this.invoiceRepository = invoiceRepository;
        this.reminderEligibilityService = reminderEligibilityService;
        this.reminderProcessingService = reminderProcessingService;
    }

    @Transactional
    public JobExecution executeJob(Long jobId, LocalDate processingDate) {
        ScheduledJob job = scheduledJobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found: " + jobId));

        JobExecution execution = new JobExecution();
        execution.setScheduledJob(job);
        execution.setStartedAt(LocalDateTime.now());
        execution.setStatus(JobExecutionStatus.RUNNING);
        execution = jobExecutionRepository.save(execution);

        int foundCount = 0;
        int successCount = 0;
        int skippedCount = 0;

        try {
            List<Invoice> activeInvoices = invoiceRepository.findByStatusNot(InvoiceStatus.PAID);
            ReminderType reminderType = determineReminderTypeFromJobName(job.getJobName());

            for (Invoice invoice : activeInvoices) {
                if (reminderEligibilityService.isEligible(invoice, reminderType, processingDate)) {
                    foundCount++;
                    boolean processed = reminderProcessingService.processReminderSafely(invoice, reminderType, processingDate);
                    if (processed) {
                        successCount++;
                    } else {
                        skippedCount++;
                    }
                }
            }

            execution.setStatus(JobExecutionStatus.SUCCESS);
        } catch (Exception e) {
            execution.setStatus(JobExecutionStatus.FAILED);
            execution.setErrorMessage(e.getMessage());
        } finally {
            execution.setFoundCount(foundCount);
            execution.setSuccessCount(successCount);
            execution.setSkippedCount(skippedCount);
            execution.setProcessedCount(successCount + skippedCount);
            execution.setCompletedAt(LocalDateTime.now());
            jobExecutionRepository.save(execution);

            job.setLastRunAt(LocalDateTime.now());
            scheduledJobRepository.save(job);
        }

        return execution;
    }

    private ReminderType determineReminderTypeFromJobName(String jobName) {
        if (jobName.toLowerCase().contains("upcoming")) return ReminderType.UPCOMING;
        if (jobName.toLowerCase().contains("due today")) return ReminderType.DUE_TODAY;
        if (jobName.toLowerCase().contains("overdue")) return ReminderType.OVERDUE;
        throw new IllegalArgumentException("Unknown job type for job name: " + jobName);
    }
}
