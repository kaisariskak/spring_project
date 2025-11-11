package kz.bsbnb.usci.report.dao;


import kz.bsbnb.usci.core.client.RespondentClient;
import kz.bsbnb.usci.core.client.UserClient;
import kz.bsbnb.usci.eav.client.ProductClient;
import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.respondent.Respondent;
import kz.bsbnb.usci.model.ws.UserReportInfo;
import kz.bsbnb.usci.report.audit.AuditClinet;
import kz.bsbnb.usci.report.audit.model.AuditSend;
import kz.bsbnb.usci.report.exception.ReportException;
import kz.bsbnb.usci.report.model.*;
import kz.bsbnb.usci.report.model.json.InputParametersJson;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReportDaoImpl implements  ReportDao{
    private static final Logger logger = LoggerFactory.getLogger(ReportDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;
    private final SimpleJdbcInsert reportLoadInsert;
    private final SimpleJdbcCall simpleJdbcCall;

    private final UserClient userClient;
    private final RespondentClient respondentClient;
    private final ProductClient productClient;

    public AuditClinet auditClinet;
    public AuditSend auditSend;

    @Autowired
    public ReportDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate,
                         UserClient userClient, RespondentClient respondentClient, ProductClient productClient,AuditClinet auditClinet) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
        this.reportLoadInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withSchemaName("REPORTER")
                .withTableName("REPORT_LOAD")
                .usingGeneratedKeyColumns("ID");
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("REPORTER")
                .withCatalogName("PKG_REPORT")
                .withProcedureName("RUN_REPORT");
        this.userClient = userClient;
        this.respondentClient = respondentClient;
        this.productClient = productClient;
        this.auditClinet = auditClinet;
    }

    @Override
    public List<Report>  loadReportList(String reportType) {
        List<Report> reportList = new ArrayList<>();
        String type;
        if(reportType.equals("BANKS"))
            type = reportType;
        else
            type = "'" + reportType + "','REFERENCEBANKS'";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("REPORT_TYPE", type);

        String query = "SELECT r.* FROM reporter.Report r WHERE r.type IN (:REPORT_TYPE)  order by r.order_number, r.id";

        List<Map<String, Object>> rows = npJdbcTemplate.queryForList(query,params);

        for (Map<String, Object> row : rows) {
            Report report = mapReport(row);
            reportList.add(report);
        }
        return reportList;
    }

    @Override
    public Report getReport(long reportId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("REPORT_ID", reportId);
        String query = "SELECT r.* FROM reporter.Report r WHERE r.ID = :REPORT_ID ";
        List<Map<String, Object>> rows = npJdbcTemplate.queryForList(query, params);
        if (rows == null || rows.isEmpty())
            return null;
        Map<String, Object> row = rows.get(0);
        return  mapReport(row);
    }

    public List<ReportInputParameter> loadReportInputParameter(Long reportId) {
        List<ReportInputParameter> inputParametersList = new ArrayList<>();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("REPORT_ID", reportId);

        String query = "select r.* from reporter.report_input_parameter r where r.report_id = :REPORT_ID order by order_number ";
        inputParametersList = mapReportInputParameter(npJdbcTemplate.queryForList(query,params));
        return inputParametersList;
    }

    @Override
    public List<ExportType> loadExportType(Long reportId) {
        List<ExportType> exportTypesList = new ArrayList<>();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("REPORT_ID", reportId);
        String query = "select ep.* from reporter.report_export_type rep, reporter.export_type ep where rep.export_type_id = ep.id and rep.report_id = :REPORT_ID";
        exportTypesList = mapExportType(npJdbcTemplate.queryForList(query,params));
        return exportTypesList;
    }

    @Override
    public List<ReportLoad> loadReportLoads(Long userId) {
        List<ReportLoad> reportLoadList = new ArrayList<>();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        String query = "SELECT rl.*, u.first_name || ' ' || u.last_name as username \n" +
                "  FROM reporter.REPORT_LOAD rl, \n" +
                "       usci_adm.users u \n" +
                " WHERE rl.PORTAL_USER_ID = :userId\n" +
                "   and rl.PORTAL_USER_ID = u.user_id \n" +
                "   ORDER BY rl.start_time desc";
        List<Map<String, Object>> rows = npJdbcTemplate.queryForList(query,params);
        for (Map<String, Object> row : rows) {
            ReportLoad reportLoad = mapReportLoad(row);
            reportLoadList.add(reportLoad);
        }
        return reportLoadList;
    }
    @Override
    public void insertOrUpdateReportLoad(ReportLoad reportLoad) {
        if (reportLoad.getId() == null) {
            Number id = reportLoadInsert.executeAndReturnKey(new MapSqlParameterSource()
                    .addValue("REPORT_ID", reportLoad.getReport().getId())
                    .addValue("PORTAL_USER_ID", reportLoad.getPortalUserId())
                    .addValue("START_TIME", reportLoad.getStartTime())
                    .addValue("FINISH_TIME", reportLoad.getFinishTime())
                    .addValue("NOTE", reportLoad.getNote()));
            reportLoad.setId(id.longValue());
        }
    }

    @Override
    public ClosableRS getDataFromProcudeure (long userId, String procedureName, List<InputParametersJson> parameterList) {
        Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
        logger.info("Connection opened getDataFromProcudeure");
        final CallableStatement statement ;
        final OracleCallableStatement ocs;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        final ResultSet rs;

        try {
            StringBuilder procedureCallBuilder = new StringBuilder("{ call ");
            procedureCallBuilder.append(procedureName);
            procedureCallBuilder.append("(");
            int parametersCount = parameterList.size();
            for (int parameterIndex = 0; parameterIndex < parametersCount + 3; parameterIndex++) {
                procedureCallBuilder.append("?");
                procedureCallBuilder.append(",");
            }
            procedureCallBuilder.deleteCharAt(procedureCallBuilder.length() - 1);
            procedureCallBuilder.append(")}");

            statement = connection.prepareCall(procedureCallBuilder.toString());
            ocs = statement.unwrap(OracleCallableStatement.class);
            statement.registerOutParameter(1, OracleTypes.CURSOR);


            statement.setLong(2, userId);
            boolean hasAccess = true;
            statement.setLong(3, hasAccess ? 1 : 0);

            parameterList.sort(new Comparator<InputParametersJson>() {
                public int compare(InputParametersJson c1, InputParametersJson c2) {
                    if (c1.getOrder() > c2.getOrder()) return -1;
                    if (c1.getOrder() < c2.getOrder()) return 1;
                    return 0;
                }});
            String parameterValueString = null;
            for(InputParametersJson parameter : parameterList) {
                if (parameter.getType().equals("DATE")) {
                    if (parameter.getValue().toString().equals("")) {
                        statement.setNull((int) (parameter.getOrder() + 3), Types.DATE);
                    } else {
                        try {
                            java.util.Date date = formatter.parse(parameter.getValue().toString());
                            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                            statement.setDate((int) (parameter.getOrder() + 3), sqlDate);
                        } catch (ParseException e) {

                        }
                    }
                }
                else {
                    parameterValueString = parameter.getValue().toString();
                    statement.setString((int) (parameter.getOrder() + 3), parameterValueString);
                }
            }

        } catch (Exception sqle) {
            closeResources(null, null, null, connection);
            logger.info("Connection closed getDataFromProcudeure");
            throw new ReportException("Ошибка : " + procedureName);
        }

        // execute procedure
        try {
            statement.execute();
            rs = ocs.getCursor(1);
            return new ClosableRS() {
                @Override
                public ResultSet getResultSet() {
                    return rs;
                }

                @Override
                public void close() {
                    closeResources(rs, ocs, statement, connection);
                }
            };
        } catch (SQLException sqle) {
            closeResources(null, ocs, statement, connection);
            logger.info("Connection closed getDataFromProcudeure");
            throw new ReportException("Ошибка выполнения процедуры: " + procedureName);
        }

    }

    @Override
    public List<ValuePair> getValueListFromStoredProcedure(String procedureName, String tableName, long userId) {
        Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
        CallableStatement statement = null;
        OracleCallableStatement ocs = null;
        ResultSet cursor = null;
        try {
            String procedureCallString="";
            if(procedureName.equals("REPORTER.INPUT_PARAMETER_SC_FIELDS"))
                procedureCallString = "{ call " + procedureName + "(?,?,?)}";
            else
                procedureCallString = "{ call " + procedureName + "(?,?)}";
            statement = connection.prepareCall(procedureCallString);
            ocs = statement.unwrap(OracleCallableStatement.class);
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.setLong(2, userId);
            if(procedureName.equals("REPORTER.INPUT_PARAMETER_SC_FIELDS"))
                statement.setString(3, tableName);
            statement.execute();
            cursor = ocs.getCursor(1);
            List<ValuePair> result = new ArrayList<ValuePair>();
            int rowNumber = 0;
            while (cursor.next()) {
                ++rowNumber;
                result.add(new ValuePair(cursor.getString(1), cursor.getString(2)));
            }
            return result;
        } catch (SQLException sqle) {
            throw new ReportException("Ошибка выполнения процедуры: " + procedureName);
        } finally {
            closeResources(cursor, ocs, statement, connection);
        }
    }


    public Date loadInputDateValue(String procedureName, long userId) {
        String procedureCallString = "{ call " + procedureName + "(?,?)}";
        Date repDate = jdbcTemplate.execute(connection -> {
            CallableStatement stmt = connection.prepareCall(procedureCallString);
            stmt.registerOutParameter(1, OracleTypes.DATE);
            stmt.setLong(2, userId);
            return stmt;
        }, new CallableStatementCallback<Date>() {
            public Date doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.execute();
                return cs.getDate(1);
            }
        });
      return repDate;
    }

    @Override
    @Async
    public void callRunReport(long userId, long isEscape, List<InputParametersJson> parameterList) {
        String reportName=null;
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_user_id", userId)
                .addValue("p_is_escape", isEscape);

        parameterList.sort(new Comparator<InputParametersJson>() {
            public int compare(InputParametersJson c1, InputParametersJson c2) {
                if (c1.getOrder() > c2.getOrder()) return -1;
                if (c1.getOrder() < c2.getOrder()) return 1;
                return 0;
            }});

        for(InputParametersJson parameter : parameterList) {
            switch (parameter.getType()) {
                case "DATE":
                    ((MapSqlParameterSource) in).addValue("p_report_date", parameter.getValue().toString() == "" ? null : SqlJdbcConverter.convertToSqlDate(parameter.getValue().toString()));
                    break;
                case "LIST":
                    ((MapSqlParameterSource) in).addValue("p_creditor_id", parameter.getValue().toString());
                    break;
                case "VITRINA":
                    ((MapSqlParameterSource) in).addValue("p_table_name", parameter.getValue().toString());
                    reportName = parameter.getValue().toString();
                    break;
                case "FILTER":
                    ((MapSqlParameterSource) in).addValue("p_filter", parameter.getValue().toString());
                    break;
                case "OPTION":
                    ((MapSqlParameterSource) in).addValue("p_attributes", parameter.getValue().toString());
                    break;
                default:
                    break;
            }
        }

        try {
            auditSend = new AuditSend(null,"AUKNREPORTER",null,userId,reportName);
            auditSend=  auditClinet.send(auditSend);
            Map out = simpleJdbcCall.execute(in);
        } catch (Exception e) {
            auditSend.errorText=e.getMessage();
            auditSend= auditClinet.send(auditSend);
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<UserReport> getReportList(Long userId, List<Long> respondentIds, List<Long> productIds, String reportDate, String vrepName) {
        List<UserReport> reportList = new ArrayList<>();

        Map<Long, Product> products = productClient.getProducts().stream()
                .collect(Collectors.toMap(Product::getId, o -> o));

        Map<Long, Respondent> respondents = respondentClient.getRespondentList().stream()
                .collect(Collectors.toMap(Respondent::getId, o -> o));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate repDate = LocalDate.parse(reportDate, formatter);

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("CREDITOR_IDS", respondentIds)
                .addValue("PRODUCT_IDS", productIds)
                .addValue("REPORT_DATE", repDate);

        StringBuilder query = new StringBuilder();
        query.append(
                " SELECT rd.RUN_ID, " +
                        "       rd.USER_ID, " +
                        "       rd.CREDITOR_ID, " +
                        "       rd.PRODUCT_ID, " +
                        "       rd.TABLE_NAME, " +
                        "       rd.REPORT_DATE, " +
                        "       rd.ATTRIBUTES, " +
                        "       rd.FILTER, " +
                        "       rd.BEGIN_DATE, " +
                        "       rd.END_DATE, " +
                        "       rd.STATUS_NAME, " +
                        "      (SELECT CASE " +
                        "                 WHEN rq.RQ_START_TS IS NOT NULL THEN NULL  " +
                        "                 WHEN rq.REPORT_QUEUE_ID IS NULL THEN NULL  " +
                        "                 ELSE COUNT(1) " +
                        "              END " +
                        "         FROM reporter.REPORT_QUEUE nrq " +
                        "        WHERE nrq.RQ_START_TS IS NULL  " +
                        "          AND nrq.RQ_TIMESTAMP < rq.RQ_TIMESTAMP " +
                        "          AND nrq.REPORT_QUEUE_ID <> rq.REPORT_QUEUE_ID " +
                        "          AND rq.RQ_START_TS IS NULL " +
                        "      ) AS WAITING_REPORTS_CNT ," +
                        "  u.first_name ||' ' || u.last_name as FIO " +
                        "  FROM REPORTER.REPORT_DATA rd " +
                        "      ,reporter.REPORT_QUEUE rq " +
                        "      ,USCI_ADM.USERS u " +
                        " WHERE rd.CREDITOR_ID IN (:CREDITOR_IDS) " +
                        "   AND rd.PRODUCT_ID IN (:PRODUCT_IDS) ");

        if (vrepName != null && !vrepName.trim().isEmpty()) {
            query.append(" AND rd.TABLE_NAME = :TABLE_NAME ");
            params.addValue("TABLE_NAME", vrepName);
        }

        query.append(
                "   AND rd.REPORT_DATE = :REPORT_DATE " +
                        "   AND rd.User_Id = u.user_id " +
                        "   AND rd.RD_REPORT_QUEUE_ID = rq.REPORT_QUEUE_ID(+) " +
                        "   AND rd.begin_date > trunc(add_months(sysdate, -4)) " +
                        " ORDER BY rd.begin_date DESC"
        );

        List<Map<String, Object>> rows = npJdbcTemplate.queryForList(query.toString(), params);

        if (rows.isEmpty())
            return Collections.emptyList();

        for (Map<String, Object> row : rows) {
            UserReport report = mapUserReport(row);
            report.setRespondent(respondents.get(SqlJdbcConverter.convertToLong(row.get("CREDITOR_ID"))).getName());
            report.setProduct(products.get(SqlJdbcConverter.convertToLong(row.get("PRODUCT_ID"))).getName());
            reportList.add(report);
        }

        return reportList;
    }

    @Override
    public byte[] getReportFile(Long id) {
        return jdbcTemplate.queryForObject("select DATA from REPORTER.REPORT_DATA  where RUN_ID = ?",
                new Object[]{id},
                (rs, rowNum) -> rs.getBytes(1));
    }

    @Override
    public void callRunReportWs(Long respondentId, Long productId, Long userId, LocalDate reportDate, String vitrina) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_user_id", userId)
                .addValue("p_is_escape", 1)
                .addValue("p_report_date",reportDate)
                .addValue("p_creditor_id",respondentId)
                .addValue("p_table_name",vitrina)
                .addValue("p_filter",null)
                .addValue("p_attributes",null);

        try {
            auditSend = new AuditSend(null,"AUKNREPORTER",null,userId,vitrina);
            auditSend=  auditClinet.send(auditSend);
            Map out = simpleJdbcCall.execute(in);
        } catch (Exception e) {
            auditSend.errorText=e.getMessage();
            auditSend= auditClinet.send(auditSend);
            logger.error(e.getMessage());
        }

    }

    @Override
    public List<UserReportInfo> getReportListWs(Long userId, Long respondentId, Long productId, LocalDate reportDate, String vrepName) {
        List<UserReportInfo> reportList = new ArrayList<>();

        Map<Long, Product> products = productClient.getProducts().stream()
                .collect(Collectors.toMap(Product::getId, o -> o));

        Map<Long, Respondent> respondents = respondentClient.getRespondentList().stream()
                .collect(Collectors.toMap(Respondent::getId, o -> o));



        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("CREDITOR_ID", respondentId)
                .addValue("PRODUCT_ID", productId)
                .addValue("REPORT_DATE", reportDate);

        StringBuilder query = new StringBuilder();
        query.append(
                      " SELECT  rd.USER_ID, " +
                        "       rd.CREDITOR_ID, " +
                        "       rd.PRODUCT_ID, " +
                        "       rd.TABLE_NAME, " +
                        "       rd.REPORT_DATE, " +
                        "       rd.BEGIN_DATE, " +
                        "       rd.END_DATE, " +
                        "       rd.STATUS_NAME, " +
                        "       rd.DATA, " +
                        "      (SELECT CASE " +
                        "                 WHEN rq.RQ_START_TS IS NOT NULL THEN NULL  " +
                        "                 WHEN rq.REPORT_QUEUE_ID IS NULL THEN NULL  " +
                        "                 ELSE COUNT(1) " +
                        "              END " +
                        "         FROM reporter.REPORT_QUEUE nrq " +
                        "        WHERE nrq.RQ_START_TS IS NULL  " +
                        "          AND nrq.RQ_TIMESTAMP < rq.RQ_TIMESTAMP " +
                        "          AND nrq.REPORT_QUEUE_ID <> rq.REPORT_QUEUE_ID " +
                        "          AND rq.RQ_START_TS IS NULL " +
                        "      ) AS WAITING_REPORTS_CNT ," +
                        "  u.first_name ||' ' || u.last_name as FIO " +
                        "  FROM REPORTER.REPORT_DATA rd " +
                        "      ,reporter.REPORT_QUEUE rq " +
                        "      ,USCI_ADM.USERS u " +
                        " WHERE rd.CREDITOR_ID = :CREDITOR_ID " +
                        "   AND rd.PRODUCT_ID = :PRODUCT_ID ");

        if (vrepName != null && !vrepName.trim().isEmpty()) {
            query.append(" AND rd.TABLE_NAME = :TABLE_NAME ");
            params.addValue("TABLE_NAME", vrepName);
        }

        query.append(
                "   AND rd.REPORT_DATE = :REPORT_DATE " +
                        "   AND rd.User_Id = u.user_id " +
                        "   AND rd.RD_REPORT_QUEUE_ID = rq.REPORT_QUEUE_ID(+) " +
                        "   AND rd.begin_date > trunc(add_months(sysdate, -1)) " +
                        " ORDER BY rd.begin_date DESC"
        );

        List<Map<String, Object>> rows = npJdbcTemplate.queryForList(query.toString(), params);

        if (rows.isEmpty())
            return Collections.emptyList();

        for (Map<String, Object> row : rows) {
            UserReportInfo report = mapUserReportWs(row);
            report.setRespondent(respondents.get(SqlJdbcConverter.convertToLong(row.get("CREDITOR_ID"))).getName());
            report.setProduct(products.get(SqlJdbcConverter.convertToLong(row.get("PRODUCT_ID"))).getName());
            reportList.add(report);
        }

        return reportList;
    }


    private void closeResources(ResultSet resultSet, Statement ocs, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqle) {
                throw new ReportException("Ошибка при закрытии подключения: " + sqle);
            }
        }
        if (ocs != null) {
            try {
                ocs.close();
            } catch (SQLException sqle) {
                throw new ReportException("Ошибка при закрытии подключения: " + sqle);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqle) {
                throw new ReportException("Ошибка при закрытии подключения: " + sqle);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqle) {
                throw new ReportException("Ошибка при закрытии подключения: " + sqle);
            }
        }
    }

    private Report mapReport(Map<String, Object> row) {
        Report report = new Report();
        report.setId(SqlJdbcConverter.convertToLong(row.get("ID")));
        report.setNameRu(String.valueOf(row.get("NAME_RU")));
        report.setNameKz(String.valueOf(row.get("NAME_KZ")));
        report.setName(String.valueOf(row.get("NAME")));
        report.setProcedureName(String.valueOf(row.get("PROCEDURE_NAME")));
        report.setType(String.valueOf(row.get("TYPE")));
        report.setOrderNumber(SqlJdbcConverter.convertToLong(row.get("ORDER_NUMBER")));
        report.setInputParameters(loadReportInputParameter(report.getId()));
        report.setExportTypeList(loadExportType(report.getId()));
        return report;
    }

    private ReportLoad mapReportLoad(Map<String, Object> row) {
        ReportLoad reportLoad = new ReportLoad();
        reportLoad.setId(SqlJdbcConverter.convertToLong(row.get("ID")));
        reportLoad.setReport(getReport(SqlJdbcConverter.convertToLong(row.get("REPORT_ID"))));
        reportLoad.setPortalUserId(SqlJdbcConverter.convertToLong(row.get("PORTAL_USER_ID")));
        reportLoad.setStartTime(SqlJdbcConverter.convertToLocalDateTime((Timestamp)row.get("START_TIME")));
        reportLoad.setFinishTime(SqlJdbcConverter.convertToLocalDateTime((Timestamp)row.get("FINISH_TIME")));
        reportLoad.setUserName((String)row.get("USERNAME"));
        reportLoad.setNote((String) row.get("NOTE"));
        return reportLoad;
    }

    private List<UserReport> mapUserReportList(List<Map<String, Object>> rows) {
        List<UserReport> reportList = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            UserReport report = new UserReport();
            report.setId(SqlJdbcConverter.convertToLong(row.get("RUN_ID")));
            report.setUser(userClient.getUser(SqlJdbcConverter.convertToLong(row.get("USER_ID"))).getFullName());
            report.setRespondent(respondentClient.getRespondentById(SqlJdbcConverter.convertToLong(row.get("CREDITOR_ID"))).getName());
            report.setProduct(productClient.getProductById(SqlJdbcConverter.convertToLong(row.get("PRODUCT_ID"))).getName());
            report.setTableName(String.valueOf(row.get("TABLE_NAME")));
            report.setReportDate(SqlJdbcConverter.convertToLocalDate(row.get("REPORT_DATE")));
            report.setAttributes(SqlJdbcConverter.convertObjectToString(row.get("ATTRIBUTES")));
            report.setFilter(SqlJdbcConverter.convertObjectToString(row.get("FILTER")));
            report.setBeginDate(SqlJdbcConverter.convertToLocalDateTime(row.get("BEGIN_DATE")));
            report.setEndDate(SqlJdbcConverter.convertToLocalDateTime(row.get("END_DATE")));
            report.setStatus(String.valueOf(row.get("STATUS_NAME")));
            reportList.add(report);
        }
        return reportList;
    }

    private UserReport mapUserReport(Map<String, Object> row) {
        UserReport report = new UserReport();
        report.setId(SqlJdbcConverter.convertToLong(row.get("RUN_ID")));
        report.setTableName(String.valueOf(row.get("TABLE_NAME")));
        report.setReportDate(SqlJdbcConverter.convertToLocalDate(row.get("REPORT_DATE")));
        report.setAttributes(SqlJdbcConverter.convertObjectToString(row.get("ATTRIBUTES")));
        report.setFilter(SqlJdbcConverter.convertObjectToString(row.get("FILTER")));
        report.setBeginDate(SqlJdbcConverter.convertToLocalDateTime(row.get("BEGIN_DATE")));
        report.setEndDate(SqlJdbcConverter.convertToLocalDateTime(row.get("END_DATE")));
        report.setStatus(String.valueOf(row.get("STATUS_NAME")));
        report.setCountQueue(SqlJdbcConverter.convertToLong(row.get("WAITING_REPORTS_CNT")));
        report.setUser(String.valueOf(row.get("FIO")));
        return report;
    }
    private UserReportInfo mapUserReportWs(Map<String, Object> row) {
        UserReportInfo report = new UserReportInfo();
        report.setTableName(String.valueOf(row.get("TABLE_NAME")));
        report.setReportDate(SqlJdbcConverter.convertToLocalDate(row.get("REPORT_DATE")));
        report.setBeginDate(SqlJdbcConverter.convertToLocalDateTime(row.get("BEGIN_DATE")));
        report.setEndDate(SqlJdbcConverter.convertToLocalDateTime(row.get("END_DATE")));
        report.setStatus(String.valueOf(row.get("STATUS_NAME")));
        report.setCountQueue(SqlJdbcConverter.convertToLong(row.get("WAITING_REPORTS_CNT")));
        report.setUser(String.valueOf(row.get("FIO")));
        report.setFile(SqlJdbcConverter.convertTobyte(row.get("DATA")));
        return report;
    }

    private List<ReportInputParameter> mapReportInputParameter(List<Map<String, Object>> rows) {
        List<ReportInputParameter> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            ReportInputParameter reportInputParameter = new ReportInputParameter();
            reportInputParameter.setId(SqlJdbcConverter.convertToLong(row.get("ID")));
            reportInputParameter.setNameRu(String.valueOf(row.get("NAME_RU")));
            reportInputParameter.setNameKz(String.valueOf(row.get("NAME_KZ")));
            reportInputParameter.setParameterName(String.valueOf(row.get("NAME")));
            reportInputParameter.setProcedureName(String.valueOf(row.get("PROCEDURE_NAME")));
            if (row.get("TYPE") != null)
                reportInputParameter.setType(String.valueOf(row.get("TYPE")));
            else
                reportInputParameter.setType("LIST");
            reportInputParameter.setOrderNumber(SqlJdbcConverter.convertToLong(row.get("ORDER_NUMBER")));
            reportInputParameter.setMaximum(SqlJdbcConverter.convertToLong(row.get("MAXIMUM")));
            reportInputParameter.setMinimum(SqlJdbcConverter.convertToLong(row.get("MINIMUM")));
            reportInputParameter.setReportId(SqlJdbcConverter.convertToLong(row.get("REPORT_ID")));
            list.add(reportInputParameter);
        }
        return list;
    }

    private List<ExportType> mapExportType(List<Map<String, Object>> rows) {
        List<ExportType> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            ExportType exportType = new ExportType();
            exportType.setId(SqlJdbcConverter.convertToLong(row.get("ID")));
            exportType.setName(String.valueOf(row.get("NAME")));
            list.add(exportType);
        }
        return list;
    }
}
