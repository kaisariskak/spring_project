package kz.bsbnb.usci.receiver.batch;

import kz.bsbnb.usci.model.Constants;
import kz.bsbnb.usci.receiver.batch.service.BatchService;
import kz.bsbnb.usci.receiver.model.Batch;
import kz.bsbnb.usci.receiver.model.BatchStatusType;
import kz.bsbnb.usci.receiver.model.ReceiverStatusSingleton;
import kz.bsbnb.usci.receiver.queue.BatchQueueHolder;
import kz.bsbnb.usci.util.client.ConfigClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class BatchPendingJob {
    private static final Logger logger = LoggerFactory.getLogger(BatchPendingJob.class);

    private final BatchService batchService;
    private final BatchQueueHolder batchQueueHolder;
    private final ReceiverStatusSingleton receiverStatus;
    private final ConfigClient configClient;


    @Value("${cluster}")
    private String clusterNumber;

    public BatchPendingJob(BatchService batchService,
                           BatchQueueHolder batchQueueHolder,
                           ReceiverStatusSingleton receiverStatus,
                           ConfigClient configClient) {
        this.batchService = batchService;
        this.batchQueueHolder = batchQueueHolder;
        this.receiverStatus = receiverStatus;
        this.configClient = configClient;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void run() {
        if(clusterNumber.equals("2")) {
            logger.info("Проверка новых батчей по кредиторам 46 из БД...");

            List<Batch> pendingBatches = batchService.getPendingBatchList();

            Set<Long> orgIds = configClient.getDigitalSigningOrgIds();

            // удаляем батчи если они уже есть в очереди
            Map<Long, Batch> actualQueue = batchQueueHolder.getActualQueue();
            pendingBatches.removeIf(batch -> actualQueue.containsKey(batch.getId()));

            pendingBatches.removeIf(batch -> batch.getStatusId() == BatchStatusType.PROCESSING.getId());
            // удаляем батчи ожидающие подписи
            pendingBatches.removeIf(batch -> batch.getStatusId() == BatchStatusType.WAITING_FOR_SIGNATURE.getId() &&
                    batch.getSignature() == null &&
                    batch.getRespondentId() != null &&
                    !batch.getRespondentId().equals(Constants.NBK_AS_RESPONDENT_ID) &&
                    orgIds.contains(batch.getRespondentId()));

            if (pendingBatches.size() > 0) {

                logger.info("Найдены новые батчи: {}", pendingBatches.size());

                logger.info("-------------------------------------------------------------------------");

                for (Batch batch : pendingBatches)
                    logger.info(batch.getId() + ", " + batch.getFilePath() + ", " + batch.getReportDate().format(Constants.DATE_FORMATTER_ISO));

                logger.info("-------------------------------------------------------------------------");

                for (Batch batch : pendingBatches) {
                    logger.info("Добавление батча в очередь : id = {}, fileName = {}", batch.getId(), batch.getFilePath());

                    try {
                        batchQueueHolder.addBatch(batch);
                        receiverStatus.batchReceived();
                    } catch (Exception e) {
                        logger.error(String.format("Ошибка перезагрузки батча id = %s", batch.getId()), e);
                    }
                }
            }
        }
    }

}

