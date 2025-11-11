package kz.bsbnb.usci.eav.dao;

import kz.bsbnb.usci.eav.model.base.OperType;
import kz.bsbnb.usci.eav.model.core.BaseEntityStatus;
import kz.bsbnb.usci.eav.model.core.EntityStatusType;
import kz.bsbnb.usci.model.ws.EntityError;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Maksat Nussipzhan
 * @author Jandos Iskakov
 */

@Repository
public class BaseEntityStatusDaoImpl implements BaseEntityStatusDao {
    private final SimpleJdbcInsert baseEntityStatusInsert;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BaseEntityStatusDaoImpl.class);

    public BaseEntityStatusDaoImpl(JdbcTemplate baseEntityStatusInsert,
                                   JdbcTemplate jdbcTemplate,
                                   NamedParameterJdbcTemplate npJdbcTemplate) {

        this.baseEntityStatusInsert = new SimpleJdbcInsert(baseEntityStatusInsert)
                .withTableName("EAV_ENTITY_STATUS")
                .withSchemaName("EAV_DATA")
                .usingColumns("BATCH_ID", "META_CLASS_ID", "EXCEPTION_TRACE",
                        "ENTITY_INDEX", "ENTITY_ID", "SYSTEM_DATE", "ENTITY_TEXT",
                        "STATUS_ID", "OPERATION_ID", "ERROR_CODE", "ERROR_MESSAGE")
                .usingGeneratedKeyColumns("ID");
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
    }

    @Override
    public BaseEntityStatus insert(BaseEntityStatus baseEntityStatus) {
        String exceptionTrace = baseEntityStatus.getExceptionTrace();
        if (exceptionTrace != null && exceptionTrace.length() > 4000)
            exceptionTrace = exceptionTrace.substring(0, 3000);

        Number id = baseEntityStatusInsert
                .executeAndReturnKey(new MapSqlParameterSource("BATCH_ID", baseEntityStatus.getBatchId())
                        .addValue("ENTITY_ID", baseEntityStatus.getEntityId())
                        .addValue("META_CLASS_ID", baseEntityStatus.getMetaClassId())
                        .addValue("SYSTEM_DATE", baseEntityStatus.getSystemDate())
                        .addValue("ENTITY_INDEX", baseEntityStatus.getIndex())
                        .addValue("STATUS_ID", baseEntityStatus.getStatus().getId())
                        .addValue("OPERATION_ID", baseEntityStatus.getOperation() != null ? baseEntityStatus.getOperation().getId() : null)
                        .addValue("ENTITY_TEXT", baseEntityStatus.getEntityText())
                        //.addValue("CREDITOR_ID",baseEntityStatus.getCreditorId())
                        .addValue("ERROR_CODE", baseEntityStatus.getErrorCode())
                        .addValue("ERROR_MESSAGE", baseEntityStatus.getErrorMessage())
                        .addValue("EXCEPTION_TRACE", exceptionTrace));
        baseEntityStatus.setId(id.longValue());

        return baseEntityStatus;
    }
    //TODO
    /*
       Данный метод реалтзован для времнного решение проблем с очередью.
       Проблема заключается в том, что когда нагрузка большая executeAndReturnKey застревает
     */
    @Override
    public BaseEntityStatus insertBatchMethod(BaseEntityStatus baseEntityStatus) {
        String exceptionTrace = baseEntityStatus.getExceptionTrace();
        String errorMessage = baseEntityStatus.getErrorMessage();
        if (exceptionTrace != null && exceptionTrace.length() > 4000)
            exceptionTrace = exceptionTrace.substring(0, 3000);
        if (errorMessage != null && errorMessage.length() > 4000)
            errorMessage = errorMessage.substring(0, 3000);

        baseEntityStatusInsert
                .executeBatch(new MapSqlParameterSource("BATCH_ID", baseEntityStatus.getBatchId())
                        .addValue("ENTITY_ID", baseEntityStatus.getEntityId())
                        .addValue("META_CLASS_ID", baseEntityStatus.getMetaClassId())
                        .addValue("SYSTEM_DATE", baseEntityStatus.getSystemDate())
                        .addValue("ENTITY_INDEX", baseEntityStatus.getIndex())
                        .addValue("STATUS_ID", baseEntityStatus.getStatus().getId())
                        .addValue("OPERATION_ID", baseEntityStatus.getOperation() != null ? baseEntityStatus.getOperation().getId() : null)
                        .addValue("ENTITY_TEXT", baseEntityStatus.getEntityText())
                        //.addValue("CREDITOR_ID",baseEntityStatus.getCreditorId())
                        .addValue("ERROR_CODE", baseEntityStatus.getErrorCode())
                        .addValue("ERROR_MESSAGE", errorMessage)
                        .addValue("EXCEPTION_TRACE", exceptionTrace));

        return baseEntityStatus;
    }

    @Override
    public void update(BaseEntityStatus baseEntityStatus) {
        String exceptionTrace = baseEntityStatus.getExceptionTrace();
        String errorMessage = baseEntityStatus.getErrorMessage();
        if (exceptionTrace != null && exceptionTrace.length() > 4000)
            exceptionTrace = exceptionTrace.substring(0, 3000);
        if (errorMessage != null && errorMessage.length() > 4000)
            errorMessage = errorMessage.substring(0, 3000);

        int count = npJdbcTemplate.update("update EAV_DATA.EAV_ENTITY_STATUS\n" +
                        "   set " +
                        //"ENTITY_ID = :ENTITY_ID, " +
                        "STATUS_ID = :STATUS_ID, OPERATION_ID = :OPERATION_ID, SYSTEM_DATE = :SYSTEM_DATE, " +
                        "       ERROR_MESSAGE = :ERROR_MESSAGE, ERROR_CODE = :ERROR_CODE, EXCEPTION_TRACE = :EXCEPTION_TRACE  \n" +
                        " where BATCH_ID = :BATCH_ID and ENTITY_ID = :APPROVED_ENTITY_ID and STATUS_ID = 9",
                new MapSqlParameterSource("BATCH_ID", baseEntityStatus.getBatchId())
                        .addValue("ENTITY_ID", baseEntityStatus.getEntityId())
                        .addValue("APPROVED_ENTITY_ID", baseEntityStatus.getApprovedEntityId())
                        .addValue("STATUS_ID", baseEntityStatus.getStatus().getId())
                        .addValue("OPERATION_ID", baseEntityStatus.getOperation() != null ? baseEntityStatus.getOperation().getId() : null)
                        .addValue("SYSTEM_DATE", baseEntityStatus.getSystemDate())
                        .addValue("ERROR_MESSAGE",errorMessage)
                        .addValue("EXCEPTION_TRACE", exceptionTrace)
                        .addValue("ERROR_CODE", baseEntityStatus.getErrorCode()));

        /*if (count != 1)
            throw new UsciException("Ошибка update записи в таблице EAV_DATA.EAV_ENTITY_STATUS");*/
    }

    @Override
    public Object[] getStatusList(Long batchId, List<EntityStatusType> statuses) {
        String selectClause = "select *\n" +
                "  from EAV_DATA.EAV_ENTITY_STATUS t\n";

        String whereClause =  "where BATCH_ID = :batchId\n" +
        "   and STATUS_ID in (:statusIds)\n";

        String fetchQuery = selectClause + whereClause + "order by SYSTEM_DATE desc\n";


        String countQuery = "select count(1) from EAV_DATA.EAV_ENTITY_STATUS\n" + whereClause;

        MapSqlParameterSource params = new MapSqlParameterSource("batchId", batchId)
                .addValue("statusIds", statuses.stream()
                        .map(EntityStatusType::getId)
                        .collect(Collectors.toList()));

        int count = npJdbcTemplate.queryForObject(countQuery, params, Integer.class);

        List<Map<String, Object>> rows = npJdbcTemplate.queryForList(fetchQuery, params);

        List<BaseEntityStatus> list = new ArrayList<>();

        for (Map<String, Object> row : rows)
            list.add(getBaseEntityStatusFromJdbcMap(row));

        return new Object[] {list, count};
    }

    @Override
    public int getErrorEntityCount(long batchId) {
        return jdbcTemplate.queryForObject("select count(1) from EAV_DATA.EAV_ENTITY_STATUS where BATCH_ID = ? and STATUS_ID = ?",
                new Object[] {batchId, EntityStatusType.ERROR.getId()}, Integer.class);
    }

    @Override
    public int getSuccessEntityCount(long batchId) {
        return jdbcTemplate.queryForObject("select count(1) from EAV_DATA.EAV_ENTITY_STATUS where BATCH_ID = ? and STATUS_ID = ?",
                new Object[] {batchId, EntityStatusType.COMPLETED.getId()}, Integer.class);
    }

    @Override
    public List<EntityError> getStatusListWs(LocalDate reportDate, Long respondentId , Long productId, Long userId) {
        String selectClause = " select regexp_substr(b.FILE_NAME, '[^/]+$') as FILE_NAME,\n" +
                       "               b.REP_DATE,\n" +
                       "               t.ERROR_MESSAGE, \n" +
                       "               t.ENTITY_TEXT, \n" +
                       "               t.SYSTEM_DATE " +
                       "          from EAV_DATA.EAV_ENTITY_STATUS t ,\n" +
                       "               USCI_BATCH.BATCH b " ;
        String whereClause =  "  where t.BATCH_ID = b.ID  " +
                              "    and t.STATUS_ID = 8  " +
                              "    and b.REP_DATE = :reportDate" +
                              "    and b.PRODUCT_ID = :productId" +
                              "    and b.USER_ID = :userId " +
                              "    and b.CREDITOR_ID = :respondentId ";

        String fetchQuery = selectClause + whereClause + "order by SYSTEM_DATE desc\n";

        MapSqlParameterSource params = new MapSqlParameterSource("reportDate", reportDate)
                .addValue("productId", productId)
                .addValue("userId",userId)
                .addValue("respondentId",respondentId);

        List<Map<String, Object>> rows = npJdbcTemplate.queryForList(fetchQuery, params);

        List<EntityError> list = new ArrayList<>();

        for (Map<String, Object> row : rows)
            list.add(getBaseEntityStatusWsFromJdbcMap(row));

        return list;

    }

    private BaseEntityStatus getBaseEntityStatusFromJdbcMap(Map<String, Object> row) {
        BaseEntityStatus entityStatus = new BaseEntityStatus();
        entityStatus.setId(SqlJdbcConverter.convertToLong(row.get("ID")));
        entityStatus.setEntityId(SqlJdbcConverter.convertToLong(row.get("ENTITY_ID")));
        entityStatus.setMetaClassId(SqlJdbcConverter.convertToLong(row.get("META_CLASS_ID")));
        entityStatus.setBatchId(SqlJdbcConverter.convertToLong(row.get("BATCH_ID")));
        entityStatus.setIndex(SqlJdbcConverter.convertToLong(row.get("INDEX")));
        entityStatus.setOperation(row.get("OPERATION_ID") != null? OperType.getOperType(SqlJdbcConverter.convertToShort(row.get("OPERATION_ID"))): null);
        entityStatus.setErrorCode(SqlJdbcConverter.convertObjectToString(row.get("ERROR_CODE")));
        entityStatus.setErrorMessage(SqlJdbcConverter.convertObjectToString(row.get("ERROR_MESSAGE")));
        entityStatus.setStatus(EntityStatusType.getEntityStatus(SqlJdbcConverter.convertToInt(row.get("STATUS_ID"))));
        entityStatus.setEntityText(SqlJdbcConverter.convertObjectToString(row.get("ENTITY_TEXT")));
        return entityStatus;
    }

    private EntityError getBaseEntityStatusWsFromJdbcMap(Map<String, Object> row) {
        EntityError entityError = new EntityError();
        entityError.setFileName(SqlJdbcConverter.convertObjectToString(row.get("FILE_NAME")));
        entityError.setReportDate(SqlJdbcConverter.convertToLocalDate(row.get("REP_DATE")));
        entityError.setErrorMessage(SqlJdbcConverter.convertObjectToString(row.get("ERROR_MESSAGE")));
        entityError.setEntityText(SqlJdbcConverter.convertObjectToString(row.get("ENTITY_TEXT")));
        entityError.setSystemDate(SqlJdbcConverter.convertToLocalDateTime(row.get("SYSTEM_DATE")));
        return entityError;
    }
}
