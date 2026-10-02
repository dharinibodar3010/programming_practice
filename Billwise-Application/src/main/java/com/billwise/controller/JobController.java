package com.billwise.controller;

import com.billwise.entity.JobExecution;
import com.billwise.entity.ScheduledJob;
import com.billwise.repository.JobExecutionRepository;
import com.billwise.repository.ScheduledJobRepository;
import com.billwise.service.ReminderSchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final ScheduledJobRepository scheduledJobRepository;
    private final JobExecutionRepository jobExecutionRepository;
    private final ReminderSchedulerService reminderSchedulerService;

    public JobController(ScheduledJobRepository scheduledJobRepository,
                         JobExecutionRepository jobExecutionRepository,
                         ReminderSchedulerService reminderSchedulerService) {
        this.scheduledJobRepository = scheduledJobRepository;
        this.jobExecutionRepository = jobExecutionRepository;
        this.reminderSchedulerService = reminderSchedulerService;
    }

    @GetMapping
    public List<ScheduledJob> getAllJobs() {
        return scheduledJobRepository.findAll();
    }

    @PatchMapping("/{id}/enable")
    public ScheduledJob enableJob(@PathVariable Long id) {
        ScheduledJob job = scheduledJobRepository.findById(id).orElseThrow();
        job.setEnabled(true);
        return scheduledJobRepository.save(job);
    }

    @PatchMapping("/{id}/disable")
    public ScheduledJob disableJob(@PathVariable Long id) {
        ScheduledJob job = scheduledJobRepository.findById(id).orElseThrow();
        job.setEnabled(false);
        return scheduledJobRepository.save(job);
    }

    @PatchMapping("/{id}/cron")
    public ScheduledJob updateCron(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        ScheduledJob job = scheduledJobRepository.findById(id).orElseThrow();
        job.setCronExpression(body.get("cronExpression"));
        return scheduledJobRepository.save(job);
    }

    @PostMapping("/{id}/run")
    public ResponseEntity<JobExecution> runJobNow(@PathVariable Long id) {
        JobExecution execution = reminderSchedulerService.executeJob(id, LocalDate.now());
        return ResponseEntity.ok(execution);
    }

    @GetMapping("/executions")
    public List<JobExecution> getRecentExecutions() {
        return jobExecutionRepository.findTop10ByOrderByIdDesc();
    }
}
