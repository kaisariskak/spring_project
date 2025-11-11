package kz.bsbnb.usci.ws.service;

import kz.bsbnb.usci.core.client.RespondentClient;
import kz.bsbnb.usci.eav.client.ProductClient;
import kz.bsbnb.usci.model.adm.User;
import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.respondent.Respondent;
import kz.bsbnb.usci.model.ws.CrossCheckWs;
import kz.bsbnb.usci.report.client.CrossCheckServiceWs;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.ws.dao.WsDao;
import kz.bsbnb.usci.ws.modal.FullValidationResult;
import kz.bsbnb.usci.ws.modal.crosscheck.CrossCheckResponse;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class CrossCheckServiceImpl implements CrossCheckService{
    private final TokenValidationService tokenValidationService;
    private final WsDao wsDao;
    private final RespondentClient respondentClient;
    private final ProductClient productClient;
    private final CrossCheckServiceWs crossCheckService;

    public CrossCheckServiceImpl(TokenValidationService tokenValidationService, WsDao wsDao, RespondentClient respondentClient, ProductClient productClient, CrossCheckServiceWs crossCheckService) {
        this.tokenValidationService = tokenValidationService;
        this.wsDao = wsDao;
        this.respondentClient = respondentClient;
        this.productClient = productClient;
        this.crossCheckService = crossCheckService;
    }

    @Override
    public CrossCheckResponse getCrossCheckList(UserInfoRequest request) {
        CrossCheckResponse crossCheckResponse = new CrossCheckResponse();
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserToken(),request.getUser(),request.getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                crossCheckResponse.setResponseInfo(responseInfo);
                return crossCheckResponse;
            }
            Long userId = wsDao.getUserIdByName(request.getUser());
            Respondent respondent = respondentClient.getRespondentByUser(new User(userId, false));
            Product product = productClient.findProductByCode(request.getProductCode());
            LocalDate reportDate = SqlJdbcConverter.convertToLocalDate(request.getReportDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            List<CrossCheckWs> crossCheckWsList= crossCheckService.getCrossCheckWs(respondent.getId(),reportDate.format(formatter),product.getId());
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
            crossCheckResponse.setResponseInfo(responseInfo);
            crossCheckResponse.setCrossCheckList(crossCheckWsList);
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            crossCheckResponse.setResponseInfo(responseInfo);
            return crossCheckResponse;
        }
        return crossCheckResponse;
    }

    @Override
    public ResponseInfo callCrossCheck(UserInfoRequest request) {
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
            crossCheckService.crossCheckWs(userId,respondent.getId(),product.getId(),reportDate.format(formatter));
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
}
