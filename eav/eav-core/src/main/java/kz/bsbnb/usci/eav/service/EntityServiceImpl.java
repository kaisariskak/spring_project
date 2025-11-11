package kz.bsbnb.usci.eav.service;

import com.google.common.collect.Lists;
import kz.bsbnb.usci.eav.dao.BaseEntityRegistryDao;
import kz.bsbnb.usci.eav.dao.BaseEntityStatusDao;
import kz.bsbnb.usci.eav.model.Constants;
import kz.bsbnb.usci.eav.model.base.BaseEntity;
import kz.bsbnb.usci.eav.model.base.BaseEntityRegistry;
import kz.bsbnb.usci.eav.model.core.BaseEntityStatus;
import kz.bsbnb.usci.eav.model.meta.MetaAttribute;
import kz.bsbnb.usci.eav.model.meta.MetaClass;
import kz.bsbnb.usci.eav.model.meta.MetaSet;
import kz.bsbnb.usci.eav.model.meta.MetaType;
import kz.bsbnb.usci.eav.repository.MetaClassRepository;
import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Artur Tkachenko
 * @author Alexandr Motov
 * @author Kanat Tulbassiev
 * @author Baurzhan Makhambetov
 * @author Jandos Iskakov
 */

@Service
public class EntityServiceImpl implements EntityService {
    private static final Logger logger = LoggerFactory.getLogger(BaseEntityProcessorImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final MetaClassRepository metaClassRepository;
    private final BaseEntityRegistryDao baseEntityRegistryDao;
    private final BaseEntityStatusDao baseEntityStatusDao;

    public EntityServiceImpl(JdbcTemplate jdbcTemplate,
                             MetaClassRepository metaClassRepository,
                             BaseEntityRegistryDao baseEntityRegistryDao,
                             BaseEntityStatusDao baseEntityStatusDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.metaClassRepository = metaClassRepository;
        this.baseEntityRegistryDao = baseEntityRegistryDao;
        this.baseEntityStatusDao = baseEntityStatusDao;
    }

    /**
     * метод проверят имеется ли сущность за отчетную дату в таблице БД
     * запрос выполняется за INDEX RANGE SCAN, COST = 1
     * (сочетание ENTITY_ID, REPORT_DATE, CREDITOR_ID является PRIMARY KEY)
     * */
    @Override
    public boolean existsBaseEntity(BaseEntity baseEntity, LocalDate reportDate) {
        Objects.requireNonNull(baseEntity.getId(), String.format("Отсутствует ID у сущности %s", baseEntity));
        Objects.requireNonNull(baseEntity.getRespondentId(), String.format("Отсутствует ID кредитора у сущности %s", baseEntity));
        Objects.requireNonNull(baseEntity.getMetaClass(), String.format("Отсутствует мета класс у сущности %s", baseEntity));
        Objects.requireNonNull(reportDate, "Отчетная дата не задана");

        MetaClass metaClass = baseEntity.getMetaClass();

        String query = String.format("select ENTITY_ID from %s.%s where REPORT_DATE = ? and ENTITY_ID = ? and CREDITOR_ID = ? and rownum < 2",
                metaClass.getSchemaData(), metaClass.getTableName());

        List<Long> rows = jdbcTemplate.queryForList(query, new Object[] {SqlJdbcConverter.convertToSqlDate(reportDate),
                baseEntity.getId(), baseEntity.getRespondentId()}, Long.class);
        if (rows.size() > 1)
            throw new UsciException(String.format("Найдено более одной записи %s", baseEntity));

        return rows.size() == 1;
    }

    @Override
    public long countBaseEntityEntries(BaseEntity baseEntity) {
        Objects.requireNonNull(baseEntity.getId(), String.format("Отсутствует ID у сущности %s", baseEntity));
        Objects.requireNonNull(baseEntity.getRespondentId(), String.format("Отсутствует ID кредитора у сущности %s", baseEntity));
        Objects.requireNonNull(baseEntity.getMetaClass(), String.format("Отсутствует мета класс у сущности %s", baseEntity));

        MetaClass metaClass = baseEntity.getMetaClass();

        String query = String.format("select count(ENTITY_ID) from %s.%s where ENTITY_ID = ? and CREDITOR_ID = ?",
                metaClass.getSchemaData(), metaClass.getTableName());

        return jdbcTemplate.queryForObject(query, new Object[] {baseEntity.getId(), baseEntity.getRespondentId()}, Long.class);
    }

    @Override
    public LocalDate getMinReportDate(BaseEntity baseEntity) {
        Objects.requireNonNull(baseEntity.getId(), String.format("Отсутствует ID у сущности %s", baseEntity));
        Objects.requireNonNull(baseEntity.getRespondentId(), String.format("Отсутствует ID кредитора у сущности %s", baseEntity));
        Objects.requireNonNull(baseEntity.getMetaClass(), String.format("Отсутствует мета класс у сущности %s", baseEntity));

        MetaClass metaClass = baseEntity.getMetaClass();

        String query = String.format("select min(REPORT_DATE) from %s.%s where ENTITY_ID = ? and CREDITOR_ID = ?",
                metaClass.getSchemaData(), metaClass.getTableName());

        return jdbcTemplate.queryForObject(query, new Object[] {baseEntity.getId(), baseEntity.getRespondentId()}, LocalDate.class);
    }

    /**
     * определяет существую ли ссылки на сущность из других сущностей
     * */
    @Override
    public boolean hasReference(BaseEntity baseEntity) {
        for (MetaClass metaClass : metaClassRepository.getMetaClasses()) {
            for (MetaAttribute metaAttribute : metaClass.getAttributes()) {
                MetaType metaType = metaAttribute.getMetaType();
                if (!metaType.isComplex())
                    continue;

                MetaClass childMetaClass;

                if (metaType.isSet()) {
                    MetaSet metaSet = (MetaSet) metaType;
                    childMetaClass = (MetaClass) metaSet.getMetaType();
                    Long mcId = baseEntity.getMetaClass().getId();


                    if(baseEntity.getMetaClass().getId().equals(267L)) {
                        String query = "select /*+parallel(16)*/ cr.entity_id \n" +
                                "from eav_data.deposit cr, eav_data.depositor_info sbj \n" +
                                "where cr.depositor_info_id = sbj.entity_id \n" +
                                " and cr.operation_id <> 3 and \n" +
                                "      sbj.entity_id = ?";
                        List<Long> sList = jdbcTemplate.queryForList(query, Long.class, baseEntity.getId());
                        if (sList.size() > 0)
                            return  true;
                    } else if (baseEntity.getMetaClass().getId().equals(269L)){
                        String query = "SELECT /*+parallel(16)*/ I.ENTITY_ID\n" +
                                "  FROM EAV_DATA.DEPOSIT_IBAN I\n" +
                                "   LEFT JOIN (SELECT D.*\n" +
                                "               FROM EAV_DATA.DEPOSIT D\n" +
                                "            ) D\n" +
                                "    ON (I.DEPOSIT_AGREEMENT_ID = D.DEPOSIT_AGREEMENT_ID AND I.CREDITOR_ID = D.CREDITOR_ID)   \n" +
                                " WHERE 1 = 1\n" +
                                "   and I.operation_id <> 3\n" +
                                "   AND D.ENTITY_ID = ?\n" +
                                "   FETCH FIRST 1 ROW ONLY";

                        List<Long> sList = jdbcTemplate.queryForList(query, Long.class, baseEntity.getId());
                        if (sList.size() > 0)
                            return  true;
                    } else if (baseEntity.getMetaClass().getId().equals(276L)) {
                        String query = "select /*+parallel(16)*/ ch.entity_id \n" +
                                "from eav_data.deposit_iban i ,eav_data.deposit_change ch \n" +
                                "where i.deposit_iic_id = ch.deposit_iic_id \n" +
                                " and ch.operation_id <> 3 and \n" +
                                "      i.entity_id = ?";
                        List<Long> sList = jdbcTemplate.queryForList(query, Long.class, baseEntity.getId());
                        if (sList.size() > 0)
                            return  true;
                    } else return !baseEntity.getMetaClass().getId().equals(279L);
                }
            }
        }

        return false;
    }

    @Override
    public boolean checkForDeleteRow(BaseEntity baseEntity) {
        for (MetaClass metaClass : metaClassRepository.getMetaClasses()) {
            for (MetaAttribute metaAttribute : metaClass.getAttributes()) {
                MetaType metaType = metaAttribute.getMetaType();
                if (!metaType.isComplex())
                    continue;

                MetaClass childMetaClass;
                List<Map<String, Object>> list;


                if (metaType.isSet()) {
                    MetaSet metaSet = (MetaSet) metaType;
                    childMetaClass = (MetaClass) metaSet.getMetaType();
                    if (childMetaClass.getProductId() == null)  {
                        continue;
                    } else {
                        if (!childMetaClass.getId().equals(baseEntity.getMetaClass().getId()))
                            continue;
                        list = jdbcTemplate.queryForList(String.format("select /*+parallel(16)*/ t.ENTITY_ID, max(t.REPORT_DATE) as REPORT_DATE from EAV_DATA.%s t, table(t.%s) ids where ids.column_value = ? group by entity_id", metaClass.getTableName(),
                                metaAttribute.getColumnName()),baseEntity.getId());
                    }
                } else {
                    childMetaClass = (MetaClass) metaType;
                    if (!childMetaClass.getId().equals(baseEntity.getMetaClass().getId()))
                        continue;
                    list = jdbcTemplate.queryForList(String.format("select /*+parallel(16)*/ t.ENTITY_ID, max(t.REPORT_DATE) as REPORT_DATE from EAV_DATA.%s t where %s = ? group by entity_id", metaClass.getTableName(),
                            metaAttribute.getColumnName()),  baseEntity.getId());
                }

                LocalDate minReportDate = getMinReportDate(baseEntity);

                for (Map<String, Object> values : list) {
                    LocalDate reportDate = SqlJdbcConverter.convertToLocalDate((java.sql.Timestamp) values.get("REPORT_DATE"));
                    if (minReportDate.isAfter(reportDate)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void insert(List<BaseEntityRegistry> baseEntityRegistries) {
        List<List<BaseEntityRegistry>> partitions = Lists.partition(baseEntityRegistries, Constants.OPTIMAL_BATCH_SIZE[1]);

        for (List<BaseEntityRegistry> partition: partitions) {
            if (partition.size() >= Constants.OPTIMAL_BATCH_SIZE[0] && partition.size() <= Constants.OPTIMAL_BATCH_SIZE[1])
                baseEntityRegistryDao.insert(partition);
            else {
                for (BaseEntityRegistry baseEntityRegistry : partition)
                    baseEntityRegistryDao.insert(Collections.singletonList(baseEntityRegistry));
            }
        }
    }

    @Override
    public Long addEntityStatus(BaseEntityStatus entityStatus) {
        return baseEntityStatusDao.insert(entityStatus).getId();
    }

}
