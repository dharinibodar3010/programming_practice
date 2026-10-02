package com.billwise.repository;

import com.billwise.entity.ScheduledJob;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ScheduledJobRepository extends JpaRepository<ScheduledJob, Long> {
    Optional<ScheduledJob> findByJobName(String jobName);
}
