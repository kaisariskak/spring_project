package kz.bsbnb.usci.ws.service;

import kz.bsbnb.usci.core.client.RespondentClient;
import kz.bsbnb.usci.eav.client.ProductClient;
import kz.bsbnb.usci.model.adm.User;
import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.respondent.Respondent;
import kz.bsbnb.usci.model.ws.OutputForm;
import kz.bsbnb.usci.model.ws.UserReportInfo;
import kz.bsbnb.usci.report.client.ReportServiceWs;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.ws.dao.WsDao;
import kz.bsbnb.usci.ws.modal.FullValidationResult;
import kz.bsbnb.usci.ws.modal.info.ReportInfo;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import kz.bsbnb.usci.ws.modal.report.OutputFormResponse;
import kz.bsbnb.usci.ws.modal.report.RunReportResponse;
import kz.bsbnb.usci.ws.modal.report.UserReportResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    public final ReportServiceWs reportServiceWs;
    private final TokenValidationService tokenValidationService;
    private final WsDao wsDao;
    private final RespondentClient respondentClient;
    private final ProductClient productClient;

    public ReportServiceImpl(ReportServiceWs reportServiceWs, TokenValidationService tokenValidationService, WsDao wsDao, RespondentClient respondentClient, ProductClient productClient) {
        this.reportServiceWs = reportServiceWs;
        this.tokenValidationService = tokenValidationService;
        this.wsDao = wsDao;
        this.respondentClient = respondentClient;
        this.productClient = productClient;
    }

    @Override
    public ResponseInfo callRunReport(ReportInfo request) {
        //RunReportResponse getRunReportResponse = new RunReportResponse();
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserToken(),request.getUser(),request.getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                return responseInfo;
            }
            Long userId = wsDao.getUserIdByName(request.getUser());
            Respondent respondent = respondentClient.getRespondentByUser(new User(userId, false));
            Product product = productClient.findProductByCode(request.getProductCode());
            LocalDate reportDate = SqlJdbcConverter.convertToLocalDate(request.getReportDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            reportServiceWs.executeReportWs(respondent.getId(),product.getId(),userId,reportDate.format(formatter),request.getVitrina());
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            return responseInfo;
        }
        return responseInfo;
    }

    @Override
    public OutputFormResponse getOutputFormList(UserInfoRequest request) {
        OutputFormResponse outputFormResponse = new OutputFormResponse();
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserToken(),request.getUser(),request.getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                outputFormResponse.setResponseInfo(responseInfo);
                return outputFormResponse;
            }
            Long userId = wsDao.getUserIdByName(request.getUser());
            List<OutputForm> outputFormList= reportServiceWs.getOutputFormList(userId);
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
            outputFormResponse.setResponseInfo(responseInfo);
            outputFormResponse.setOutputFormList(outputFormList);
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            outputFormResponse.setResponseInfo(responseInfo);
            return outputFormResponse;
        }
        return outputFormResponse;
    }

    @Override
    public UserReportResponse getUserReport(ReportInfo request) {
        UserReportResponse getUserReportResponse = new UserReportResponse();
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserToken(),request.getUser(),request.getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                getUserReportResponse.setResponseInfo(responseInfo);
                return getUserReportResponse;
            }
            Long userId = wsDao.getUserIdByName(request.getUser());
            Respondent respondent = respondentClient.getRespondentByUser(new User(userId, false));
            Product product = productClient.findProductByCode(request.getProductCode());
            LocalDate reportDate = SqlJdbcConverter.convertToLocalDate(request.getReportDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            List<UserReportInfo> userReportInfoList = reportServiceWs.getReportListWs(userId,respondent.getId(),product.getId(),reportDate.format(formatter),request.getVitrina());
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
            getUserReportResponse.setResponseInfo(responseInfo);
            getUserReportResponse.setUserReportInfoList(userReportInfoList);
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            getUserReportResponse.setResponseInfo(responseInfo);
            return getUserReportResponse;
        }
        return getUserReportResponse;
    }
}
