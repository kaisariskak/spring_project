package kz.bsbnb.usci.wsclient.job;

import kz.bsbnb.usci.wsclient.service.KGDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class KGDServiceJob {
    private static final Logger logger = LoggerFactory.getLogger(KGDServiceJob.class);

    private final KGDService kgdService;

    public KGDServiceJob(KGDService kgdService) {
        this.kgdService = kgdService;
    }

    //@Scheduled(cron = "1 * * * * *")
    //@Scheduled(cron = "0 0 0 ? * THU")
    public void sendRequestToKGD() {
        LocalDate sendDate = LocalDate.now();
        LocalDate reportDate = sendDate.with(TemporalAdjusters.firstDayOfMonth());
        reportDate = reportDate.minusMonths(1);

        kgdService.ctrRequest(sendDate, reportDate, null, 1,false);
        kgdService.ctrRequest(sendDate, reportDate, null, 2,false);
        kgdService.ctrRequest(sendDate, reportDate, null, 3,false);
    }
}
