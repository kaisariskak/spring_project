package kz.bsbnb.usci.eav.dao;

import kz.bsbnb.usci.eav.model.core.BaseEntityStatus;
import kz.bsbnb.usci.eav.model.core.EntityStatusType;
import kz.bsbnb.usci.model.ws.EntityError;

import java.time.LocalDate;
import java.util.List;


public interface BaseEntityStatusDao {

    BaseEntityStatus insert(BaseEntityStatus baseEntityStatus);

    BaseEntityStatus insertBatchMethod(BaseEntityStatus baseEntityStatus);

    void update(BaseEntityStatus baseEntityStatus);

    Object[] getStatusList(Long batchId, List<EntityStatusType> statuses);

    int getErrorEntityCount(long batchId);

    int getSuccessEntityCount(long batchId);

    List<EntityError> getStatusListWs(LocalDate reportDate, Long respondentId , Long productId, Long userId);

}
