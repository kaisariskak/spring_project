package kz.bsbnb.usci.report.controller;

import kz.bsbnb.usci.model.ws.OutputForm;
import kz.bsbnb.usci.model.ws.UserReportInfo;
import kz.bsbnb.usci.report.model.*;
import kz.bsbnb.usci.report.model.json.InputParametersJson;
import kz.bsbnb.usci.report.service.ReportService;
import kz.bsbnb.usci.util.json.ext.ExtJsList;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/report")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate npJdbcTemplate;
    private final ReportService reportService;

    public ReportController(JdbcTemplate jdbcTemplate,
                            NamedParameterJdbcTemplate npJdbcTemplate,
                            ReportService reportService) {
        this.jdbcTemplate = jdbcTemplate;
        this.npJdbcTemplate = npJdbcTemplate;
        this.reportService = reportService;

    }

    @GetMapping(value = "loadReportList")
    public ExtJsList loadReports(@RequestParam String reportType) {
        return new ExtJsList(reportService.loadReportList(reportType));
    }

    @GetMapping(value = "getReportList")
    public ExtJsList getReportList(@RequestParam(name = "userId") Long userId,
                                   @RequestParam(name = "respondentIds") List<Long> respondentIds,
                                   @RequestParam(name = "productIds") List<Long> productIds,
                                   @RequestParam(name = "reportDate") String reportDate,
                                   @RequestParam(name = "vrepName") String vrepName) {
        return new ExtJsList(reportService.getReportList(userId,respondentIds, productIds,reportDate,vrepName));
    }

    @GetMapping(value = "getReportFile")
    public byte[] getReportFile(@RequestParam(name = "id") Long id) {
        return reportService.getReportFile(id);
    }

    @PostMapping(value = "getReportData")
    public ResponseEntity<InputStreamResource> getReportData(@RequestParam(name = "userId") long userId,
                                                             @RequestParam(name = "reportId") long reportId,
                                                             @RequestBody List<InputParametersJson> parameterValues) {
       InputStreamResource resource = null;
       Report report = reportService.getReport(reportId);
       String fileName = report.getName();
       for (ExportType exportType : report.getExportTypesList()) {
            if (ExportType.JASPER_XLS.equals(exportType.getName())) {
                resource = new InputStreamResource(new ByteArrayInputStream(reportService.jasperToXls(userId, report, parameterValues)));
            } else if (ExportType.TEMPLATE_XLS.equals(exportType.getName())) {
                byte[] bytes = reportService.templateReportToXls(userId, report, parameterValues);
                if (bytes != null) {
                    resource = new InputStreamResource(new ByteArrayInputStream(bytes));
                }
            }
       }
       HttpHeaders header = createHeader();
       header.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());

        return ResponseEntity
                .ok()
                .headers(header)
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(resource);
    }

    @PostMapping(value = "executeReport")
    public void executeReport(@RequestParam(name = "userId") long userId,
                              @RequestParam(name = "isEscape") long isEscape,
                              @RequestBody List<InputParametersJson> parameterValues) {
        reportService.callRunReport(userId, isEscape, parameterValues);
    }

    @PostMapping (value = "loadTableReport")
    public byte[] loadTableReport(@RequestParam(name = "userId") long userId,
                                  @RequestParam(name = "reportId") long reportId,
                                  @RequestBody List<InputParametersJson> parameterValues) throws SQLException {
        JSONObject json = new JSONObject();
        Report report = reportService.getReport(reportId);
        ReportLoad reportLoad = reportService.loadStarted(userId, report, parameterValues);
        ClosableRS rs = reportService.getDataFromProcudeure(userId, report.getProcedureName(), parameterValues);
        try {
            ReportData data = new ReportData(rs.getResultSet());
            List<Map<String, Object>> rows = data.getMap();

            JSONObject detail = new JSONObject();
            detail.put("rows", data.getColumnCount());
            json.put("data", rows);
            json.put("detail", detail);

            reportService.loadFinished(reportLoad);
            rs.close();
            logger.info("Connection closed getDataFromProcudeure");
        } catch (JSONException jse) {
            rs.close();
            logger.info("Connection closed getDataFromProcudeure");
        }
        catch ( Exception e) {
            rs.close();
            logger.info("Connection closed getDataFromProcudeure");
        }

        return json.toString().getBytes();
    }

    @GetMapping(value = "getValueList")
    public List<ValuePair> getValueList(@RequestParam long userId,
                                        @RequestParam String tableName,
                                        @RequestParam String procedureName) {
        return  reportService.getValueListFromStoredProcedure(procedureName, tableName, userId);
    }

    @GetMapping(value = "loadReportLoads")
    public ExtJsList loadReportLoads(@RequestParam Long userId) {
        return new ExtJsList(reportService.loadReportLoads(userId));
    }

    private static HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        //без этих значений выходило: Refused to get unsafe header "Accept-Ranges"
        headers.add("Access-Control-Allow-Headers", "Range");
        headers.add("Access-Control-Expose-Headers", "Accept-Ranges, Content-Encoding, Content-Length, Content-Range, Content-Disposition");
        return headers;
    }
    @PostMapping(value = "executeReportWs")
    public void executeReportWs(@RequestParam(name = "respondentId") Long respondentId,
                                @RequestParam(name = "productId") Long productId,
                                @RequestParam(name = "userId") Long userId,
                                @RequestParam(name = "reportDate")  @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate,
                                @RequestParam(name = "vitrina") String vitrina) {
        reportService.callRunReportWs(respondentId, productId, userId,reportDate,vitrina);
    }

    @GetMapping(value = "getOutputFormList")
    public List<OutputForm>  getOutputFormList(@RequestParam long userId) {
        return  reportService.getOutputFormList(userId);
    }
    @GetMapping(value = "getReportListWs")
    public List<UserReportInfo> getReportListWs(@RequestParam(name = "userId") Long userId,
                                                @RequestParam(name = "respondentId") Long respondentId,
                                                @RequestParam(name = "productId") Long productId,
                                                @RequestParam(name = "reportDate")  @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate,
                                                @RequestParam(name = "vrepName") String vrepName) {
        return reportService.getReportListWs(userId,respondentId, productId,reportDate,vrepName);
    }
}
