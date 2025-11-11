package kz.bsbnb.usci.ws.service;

import kz.bsbnb.usci.core.client.RespondentClient;
import kz.bsbnb.usci.eav.client.ProductClient;
import kz.bsbnb.usci.model.adm.User;
import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.respondent.Respondent;
import kz.bsbnb.usci.model.ws.EntityError;
import kz.bsbnb.usci.model.ws.Protocol;
import kz.bsbnb.usci.receiver.client.BatchClient;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.ws.dao.WsDao;
import kz.bsbnb.usci.ws.modal.FullValidationResult;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import kz.bsbnb.usci.ws.modal.protocol.EntityErrorResponse;
import kz.bsbnb.usci.ws.modal.protocol.ProtocolRequest;
import kz.bsbnb.usci.ws.modal.protocol.ProtocolResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProtocolServiceImpl implements ProtocolService{
    private final TokenValidationService tokenValidationService;
    private final BatchClient batchClient;
    private final WsDao wsDao;
    private final RespondentClient respondentClient;
    private final ProductClient productClient;

    public ProtocolServiceImpl(TokenValidationService tokenValidationService, BatchClient batchClient, WsDao wsDao, RespondentClient respondentClient, ProductClient productClient) {
        this.tokenValidationService = tokenValidationService;
        this.batchClient = batchClient;
        this.wsDao = wsDao;
        this.respondentClient = respondentClient;
        this.productClient = productClient;
    }

    @Override
    public ProtocolResponse getProtocolList(ProtocolRequest request) {
        ProtocolResponse getProtocolResponse = new ProtocolResponse();
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserToken(),request.getUser(),request.getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                getProtocolResponse.setResponseInfo(responseInfo);
                return getProtocolResponse;
            }
            Long userId = wsDao.getUserIdByName(request.getUser());
            Respondent respondent = respondentClient.getRespondentByUser(new User(userId, false));
            Product product = productClient.findProductByCode(request.getProductCode());
            LocalDate reportDate = SqlJdbcConverter.convertToLocalDate(request.getReportDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            List<Protocol>  protocolList =  batchClient.getBatchListWs(respondent.getId(),product.getId(),userId,reportDate.format(formatter));
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
            getProtocolResponse.setResponseInfo(responseInfo);
            getProtocolResponse.setProtocolList(protocolList);
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            getProtocolResponse.setResponseInfo(responseInfo);
            return getProtocolResponse;
        }
        return getProtocolResponse;
    }

    @Override
    public EntityErrorResponse getEntityErrorResponseList(UserInfoRequest request) {
        EntityErrorResponse getEntityErrorResponse = new EntityErrorResponse();
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserToken(),
                                                                                       request.getUser(),
                                                                                       request.getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                getEntityErrorResponse.setResponseInfo(responseInfo);
                return getEntityErrorResponse;
            }
            Long userId = wsDao.getUserIdByName(request.getUser());
            Respondent respondent = respondentClient.getRespondentByUser(new User(userId, false));
            Product product = productClient.findProductByCode(request.getProductCode());
            LocalDate reportDate = SqlJdbcConverter.convertToLocalDate(request.getReportDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            List<EntityError>  entityErrorList =  batchClient.getEntityErrorList(respondent.getId(),
                                                                                 product.getId(),
                                                                                 userId,
                                                                                 reportDate.format(formatter));
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
            getEntityErrorResponse.setResponseInfo(responseInfo);
            getEntityErrorResponse.setEntityErrorList(entityErrorList);
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            getEntityErrorResponse.setResponseInfo(responseInfo);
            return getEntityErrorResponse;
        }
        return getEntityErrorResponse;
    }
}
