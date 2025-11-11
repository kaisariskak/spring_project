package kz.bsbnb.usci.jobstore.service;

import kz.bsbnb.usci.model.job.JobConfig;

import java.util.List;

public interface ScheduleService {
    List<JobConfig> getJobList();
    void saveJobConfig(Long id, String cron, boolean isStarted);
}
