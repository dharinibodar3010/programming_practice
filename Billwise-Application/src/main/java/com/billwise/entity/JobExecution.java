package com.billwise.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_execution")
public class JobExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheduled_job_id", nullable = false)
    private ScheduledJob scheduledJob;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobExecutionStatus status;

    @Column(name = "found_count")
    private int foundCount = 0;

    @Column(name = "processed_count")
    private int processedCount = 0;

    @Column(name = "success_count")
    private int successCount = 0;

    @Column(name = "failed_count")
    private int failedCount = 0;

    @Column(name = "skipped_count")
    private int skippedCount = 0;

    @Column(name = "error_message")
    private String errorMessage;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ScheduledJob getScheduledJob() { return scheduledJob; }
    public void setScheduledJob(ScheduledJob scheduledJob) { this.scheduledJob = scheduledJob; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public JobExecutionStatus getStatus() { return status; }
    public void setStatus(JobExecutionStatus status) { this.status = status; }
    public int getFoundCount() { return foundCount; }
    public void setFoundCount(int foundCount) { this.foundCount = foundCount; }
    public int getProcessedCount() { return processedCount; }
    public void setProcessedCount(int processedCount) { this.processedCount = processedCount; }
    public int getSuccessCount() { return successCount; }
    public void setSuccessCount(int successCount) { this.successCount = successCount; }
    public int getFailedCount() { return failedCount; }
    public void setFailedCount(int failedCount) { this.failedCount = failedCount; }
    public int getSkippedCount() { return skippedCount; }
    public void setSkippedCount(int skippedCount) { this.skippedCount = skippedCount; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
