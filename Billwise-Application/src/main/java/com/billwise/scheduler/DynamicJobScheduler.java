package com.billwise.scheduler;

import com.billwise.entity.ScheduledJob;
import com.billwise.repository.ScheduledJobRepository;
import com.billwise.service.ReminderSchedulerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
public class DynamicJobScheduler implements SchedulingConfigurer {

    private final ScheduledJobRepository scheduledJobRepository;
    private final ReminderSchedulerService reminderSchedulerService;

    public DynamicJobScheduler(ScheduledJobRepository scheduledJobRepository, ReminderSchedulerService reminderSchedulerService) {
        this.scheduledJobRepository = scheduledJobRepository;
        this.reminderSchedulerService = reminderSchedulerService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));

        List<ScheduledJob> jobs = scheduledJobRepository.findAll();

        for (ScheduledJob job : jobs) {
            taskRegistrar.addTriggerTask(
                () -> {
                    // Check if job is still enabled by fetching latest from DB
                    ScheduledJob currentJob = scheduledJobRepository.findById(job.getId()).orElse(null);
                    if (currentJob != null && currentJob.isEnabled()) {
                        reminderSchedulerService.executeJob(currentJob.getId(), LocalDate.now());
                    }
                },
                triggerContext -> {
                    // Fetch latest cron expression dynamically
                    ScheduledJob currentJob = scheduledJobRepository.findById(job.getId()).orElse(job);
                    CronTrigger trigger = new CronTrigger(currentJob.getCronExpression());
                    return trigger.nextExecution(triggerContext);
                }
            );
        }
    }
}
