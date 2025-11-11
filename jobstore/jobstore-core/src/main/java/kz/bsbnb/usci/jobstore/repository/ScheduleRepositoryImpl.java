package kz.bsbnb.usci.jobstore.repository;

import kz.bsbnb.usci.jobstore.dao.ScheduleDao;
import kz.bsbnb.usci.model.job.JobConfig;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.wsclient.client.KGDClient;
import kz.bsbnb.usci.wsclient.client.NSIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final ScheduleDao scheduleDao;
    private final NSIClient nsiClient;
    private final KGDClient kdgClient;

    @Autowired
    TaskScheduler taskScheduler;

    Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public ScheduleRepositoryImpl(ScheduleDao scheduleDao,
                                  NSIClient nsiClient,
                                  KGDClient kdgClient) {
        this.scheduleDao = scheduleDao;
        this.nsiClient = nsiClient;
        this.kdgClient = kdgClient;
    }

    @PostConstruct
    public void init() {
        loadAndStartJobs();
    }


    @Override
    public void start(JobConfig jobConfig) {
        if (jobConfig.getJobName().equals("NSI_CURRENCY")) {
            ScheduledFuture<?> scheduledTask = taskScheduler.schedule(nsiCurrency(), new CronTrigger(jobConfig.getCron()));
            jobsMap.put(jobConfig.getId(), scheduledTask);
        } else if (jobConfig.getJobName().equals("KGD_50PLUS")) {
            ScheduledFuture<?> scheduledTask = taskScheduler.schedule(kgd50Plus(), new CronTrigger(jobConfig.getCron()));
            jobsMap.put(jobConfig.getId(), scheduledTask);
        }
    }

    @Override
    public void stop(Long id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(id, null);
        }

    }

    private void loadAndStartJobs() {
        List<JobConfig> jobConfigList = scheduleDao.loadAllJobConfigs();

        for (JobConfig jobConfig : jobConfigList) {
            if (jobConfig.isStarted())
                start(jobConfig);
        }

    }

    private Runnable nsiCurrency() {
        return () -> {
            LocalDate date = LocalDate.now();
            nsiClient.saveCurrencyRates(SqlJdbcConverter.convertToString(date));
        };
    }

    private Runnable kgd50Plus() {
        return () -> {
            kdgClient.sendRequestToKGD();
        };
    }
}
