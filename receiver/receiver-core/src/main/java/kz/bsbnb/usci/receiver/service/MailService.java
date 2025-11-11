package kz.bsbnb.usci.receiver.service;

import kz.bsbnb.usci.receiver.model.Batch;

import java.util.List;
import java.util.Map;

public interface MailService {

    void notifyRegulatorMaintenance(Batch batch);

    void notifyBatchProcessCompleted(Batch batch);

    void notifyOnError(String templateName, Map<String, String> params, List<Long> userIds);

}
