package kz.bsbnb.usci.jobstore.service;

import kz.bsbnb.usci.jobstore.dao.ScheduleDao;
import kz.bsbnb.usci.model.job.JobConfig;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleDao scheduleDao;

    public ScheduleServiceImpl(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Override
    public List<JobConfig> getJobList() {
        return scheduleDao.loadAllJobConfigs();
    }

    @Override
    public void saveJobConfig(Long id, String cron, boolean isStarted) {
        scheduleDao.saveJobConfig(id, cron, isStarted);
    }
}
