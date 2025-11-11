package kz.bsbnb.usci.ws.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WsDaoImpl implements WsDao {
    private final JdbcTemplate jdbcTemplate;

    public WsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long getUserIdByName(String name) {
        return jdbcTemplate.queryForObject("select USER_ID from USCI_ADM.USERS where SCREEN_NAME = ?", new Object[]{name.toLowerCase()}, Long.class);
    }

    @Override
    public Integer checkUserProduct(Long userId, Long productId) {
        Integer count = jdbcTemplate.queryForObject("select count(1)\n " +
                "  from USCI_ADM.USER_PRODUCT\n" +
                " where USER_ID = ?\n" +
                "   and PRODUCT_ID = ?", new Object[] {userId, productId}, Integer.class);
        return count;
    }

    @Override
    public List<Map<String, Object>> getUsersForUpload() {
        return jdbcTemplate.queryForList("select * from LPORTAL.USER_ where SCREENNAME in ('momurzak','arzu','rano','gdautali','nurakhmetova','adykanbayeva')");
    }
}
