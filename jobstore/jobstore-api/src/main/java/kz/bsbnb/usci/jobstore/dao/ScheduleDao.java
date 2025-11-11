package kz.bsbnb.usci.jobstore.dao;

import kz.bsbnb.usci.model.job.JobConfig;

import java.util.List;

public interface ScheduleDao {
    void saveJobConfig(Long id, String cron, boolean isStarted);
    List<JobConfig> loadAllJobConfigs();
}
