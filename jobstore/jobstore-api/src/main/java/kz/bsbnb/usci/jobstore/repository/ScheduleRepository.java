package kz.bsbnb.usci.jobstore.repository;

import kz.bsbnb.usci.model.job.JobConfig;

public interface ScheduleRepository {

    public void start(JobConfig jobConfig);
    public void stop(Long id);
}
