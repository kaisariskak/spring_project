package kz.bsbnb.usci.receiver.processor;

import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.receiver.model.Batch;
import kz.bsbnb.usci.receiver.model.BatchFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BatchReceiver {

    boolean processBatch(long batchId);

    void declineMaintenanceBatch(long batchId);

    void cancelBatch(long batchId);

    void receiveBatch(BatchFile batchFile);

    void receiveBatchFromWebservice(Long userId, Long respondentId, Product product, String batchName, String filePath, byte[] fileContent,
                                    LocalDate reportDate, String sign, String signInfo, LocalDateTime signTime);

    boolean processBatch(Batch batch);

}
