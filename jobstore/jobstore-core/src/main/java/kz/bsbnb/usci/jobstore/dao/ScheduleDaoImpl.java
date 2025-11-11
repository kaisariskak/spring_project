package kz.bsbnb.usci.jobstore.dao;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.model.job.JobConfig;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;

    public ScheduleDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
    }

    @Override
    public void saveJobConfig(Long id, String cron, boolean isStarted) {
        String query = "update USCI_UTIL.JOB_CONFIG\n";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("IS_STARTED", isStarted ? "1": "0")
                .addValue("ID", id);
        if (cron == null) {
            query = query +  "   set IS_STARTED  = :IS_STARTED\n" +
                             " where ID = :ID";
        } else {
            query = query +  "   set CRON        = :CRON,\n" +
                            "       IS_STARTED  = :IS_STARTED\n" +
                            " where ID = :ID";
            params.addValue("CRON", cron);
        }

        int count = npJdbcTemplate.update(query,params);

        if (count != 1)
            throw new UsciException("Ошибка update записи в таблице USCI_UTIL.JOB_CONFIG");
    }

    @Override
    public List<JobConfig> loadAllJobConfigs() {
        return jdbcTemplate.query("select * from USCI_UTIL.JOB_CONFIG", new JobConfigMapper());
    }

    private class JobConfigMapper implements RowMapper<JobConfig> {

        JobConfigMapper() {
        }

        public JobConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobConfig jobConfig = new JobConfig();

            jobConfig.setId(rs.getLong("ID"));
            jobConfig.setJobName(rs.getString("JOB_NAME"));
            jobConfig.setCron(rs.getString("CRON"));
            jobConfig.setStarted(SqlJdbcConverter.convertVarchar2ToBoolean(rs.getString("IS_STARTED")));
            jobConfig.setJobTitle(rs.getString("JOB_TITLE"));

            return jobConfig;
        }
    }
}
