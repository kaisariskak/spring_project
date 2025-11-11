package kz.bsbnb.usci.wsclient.dao;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.wsclient.model.currency.CurrencyEntityCustom;
import kz.bsbnb.usci.wsclient.model.currency.HolidayEntityCustom;
import kz.bsbnb.usci.wsclient.model.currency.NSIEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Repository
public class NSIDaoImpl implements NSIDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;

    public NSIDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
    }

    @Override
    public void saveCurrencyRates(List<NSIEntity> currList) {
        /*if (true)
            throw new UsciException("Ошибка обновления курсов валют");*/

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withSchemaName("USCI_WS")
                .withTableName("NSI_CURRENCY")
                .usingGeneratedKeyColumns("ID");

        List<Map<String, Object>> batchValues = new ArrayList<>(currList.size());
        for (NSIEntity NSIEntity : currList) {
            Map<String, Object> map = new HashMap<>();
            map.put("OPER_TYPE", NSIEntity.getNSIEntitySystem().getOperType());
            map.put("ENTITY_ID", NSIEntity.getNSIEntitySystem().getEntityID());
            map.put("OPER_DATE", SqlJdbcConverter.convertToSqlDate(NSIEntity.getNSIEntitySystem().getOperDate()));
            map.put("BEGIN_DATE", SqlJdbcConverter.convertToSqlDate(NSIEntity.getNSIEntitySystem().getBeginDate()));
            map.put("END_DATE", SqlJdbcConverter.convertToSqlDate(NSIEntity.getNSIEntitySystem().getEndDate()));
            map.put("CURRENCY_ID", ((CurrencyEntityCustom) NSIEntity.getNSIEntityCustom()).getCurrId());
            map.put("CURRENCY_CODE", ((CurrencyEntityCustom) NSIEntity.getNSIEntityCustom()).getCurrCode());
            map.put("COURSE_DATE", SqlJdbcConverter.convertToSqlDate(((CurrencyEntityCustom) NSIEntity.getNSIEntityCustom()).getCourseDate()));
            map.put("COURSE_KIND", ((CurrencyEntityCustom) NSIEntity.getNSIEntityCustom()).getCourseKind());
            map.put("COURSE", ((CurrencyEntityCustom) NSIEntity.getNSIEntityCustom()).getCourse());
            map.put("CORELLATION", ((CurrencyEntityCustom) NSIEntity.getNSIEntityCustom()).getCorellation());
            map.put("WD_KIND", ((CurrencyEntityCustom) NSIEntity.getNSIEntityCustom()).getWdKind());

            batchValues.add(map);
        }
        try {
            simpleJdbcInsert.executeBatch(batchValues.toArray(new Map[currList.size()]));
        } catch(Exception e) {
            throw new UsciException("Ошибка обновления курсов валют", e);
        }
    }

    @Override
    public LocalDate getMaxCourseDate() {
        return SqlJdbcConverter.convertToLocalDate(jdbcTemplate.queryForObject("select max(COURSE_DATE) from usci_ws.nsi_currency", Date.class));
    }

    @Override
    public void saveHolidayDates(List<NSIEntity> currList) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withSchemaName("USCI_WS")
                .withTableName("NSI_HOLIDAY")
                .usingGeneratedKeyColumns("ID");

        List<Map<String, Object>> batchValues = new ArrayList<>(currList.size());
        for (NSIEntity NSIEntity : currList) {
            Map<String, Object> map = new HashMap<>();
            map.put("OPER_TYPE", NSIEntity.getNSIEntitySystem().getOperType());
            map.put("ENTITY_ID", NSIEntity.getNSIEntitySystem().getEntityID());
            map.put("OPER_DATE", SqlJdbcConverter.convertToSqlDate(NSIEntity.getNSIEntitySystem().getOperDate()));
            map.put("BEGIN_DATE", SqlJdbcConverter.convertToSqlDate(NSIEntity.getNSIEntitySystem().getBeginDate()));
            map.put("END_DATE", SqlJdbcConverter.convertToSqlDate(NSIEntity.getNSIEntitySystem().getEndDate()));
            map.put("HOLIDAY_DATE", SqlJdbcConverter.convertToSqlDate(((HolidayEntityCustom) NSIEntity.getNSIEntityCustom()).getHolidayDate()));
            map.put("HOLIDAY_TYPE", ((HolidayEntityCustom) NSIEntity.getNSIEntityCustom()).getHolidayType());

            batchValues.add(map);
        }
        simpleJdbcInsert.executeBatch(batchValues.toArray(new Map[currList.size()]));
    }
}
