package kz.bsbnb.usci.wsclient.dao;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.wsclient.model.gbdul.GBDULEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GBDULDaoImpl implements GBDULDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;
    private final SimpleJdbcInsert gbdulInsert;

    public GBDULDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
        this.gbdulInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withSchemaName("EAV_DATA")
                .withTableName("XX_MSB_GDBULINFO")
                .usingColumns("DOC_NO", "FULLNAME", "PRIVATE_ENTERPRISE_TYPE_CODE", "PRIVATE_ENTERPRISE_TYPE_NAME_RU", "PRIVATE_ENTERPRISE_TYPE_NAME_KZ", "REQUEST_STATUS");
    }

    @Override
    public void saveGBDULInfo(GBDULEntity gbdulEntity) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("DOC_NO", gbdulEntity.getBin())
                .addValue("FULLNAME",  gbdulEntity.getFullName())
                .addValue("PRIVATE_ENTERPRISE_TYPE_CODE", gbdulEntity.getPrivateEnterpriseTypeCode())
                .addValue("PRIVATE_ENTERPRISE_TYPE_NAME_RU", gbdulEntity.getPrivateEnterpriseTypeNameRu())
                .addValue("PRIVATE_ENTERPRISE_TYPE_NAME_KZ", gbdulEntity.getPrivateEnterpriseTypeNameKz())
                .addValue("REQUEST_STATUS", gbdulEntity.getRequestStatus());

        int count = gbdulInsert.execute(params);
        if (count != 1)
            throw new UsciException("Ошибка insert записи в таблицу EAV_DATA.XX_MSB_GDBULINFO");
    }

    @Override
    public List<String> getBinList() {
        return jdbcTemplate.queryForList("select doc_no from eav_data.xx_msb_selected where checked is null", String.class);
    }

    @Override
    public void updateCheck(String bin) {
        int count = npJdbcTemplate.update("update EAV_DATA.XX_MSB_SELECTED\n" +
                        "   set CHECKED = 1\n" +
                        " where DOC_NO = :DOC_NO",
                new MapSqlParameterSource("DOC_NO", bin));

        if (count != 1)
            throw new UsciException("Ошибка update записи в таблице EAV_DATA.XX_MSB_SELECTED");

    }

}
