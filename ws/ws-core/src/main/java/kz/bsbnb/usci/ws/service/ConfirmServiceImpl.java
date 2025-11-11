package kz.bsbnb.usci.ws.service;

import kz.bsbnb.usci.core.client.ConfirmClient;
import kz.bsbnb.usci.core.client.RespondentClient;
import kz.bsbnb.usci.eav.client.ProductClient;
import kz.bsbnb.usci.model.adm.User;
import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.respondent.Respondent;
import kz.bsbnb.usci.model.ws.ConfirmWs;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.ws.dao.WsDao;
import kz.bsbnb.usci.ws.modal.FullValidationResult;
import kz.bsbnb.usci.ws.modal.confirm.ConfrimApproveRequest;
import kz.bsbnb.usci.ws.modal.confirm.ConfrimResponse;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class ConfirmServiceImpl implements  ConfirmService{
    private final TokenValidationService tokenValidationService;
    private final WsDao wsDao;
    private final RespondentClient respondentClient;
    private final ProductClient productClient;
    private final ConfirmClient confirmClient;

    public ConfirmServiceImpl(TokenValidationService tokenValidationService, WsDao wsDao, RespondentClient respondentClient, ProductClient productClient, ConfirmClient confirmClient) {
        this.tokenValidationService = tokenValidationService;
        this.wsDao = wsDao;
        this.respondentClient = respondentClient;
        this.productClient = productClient;
        this.confirmClient = confirmClient;
    }

    @Override
    public ConfrimResponse getConfirmList(UserInfoRequest request) {
        ConfrimResponse confrimResponse = new ConfrimResponse();
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserToken(),
                    request.getUser(),
                    request.getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                confrimResponse.setResponseInfo(responseInfo);
                return confrimResponse;
            }
            Long userId = wsDao.getUserIdByName(request.getUser());
            Respondent respondent = respondentClient.getRespondentByUser(new User(userId, false));
            LocalDate reportDate = SqlJdbcConverter.convertToLocalDate(request.getReportDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            List<ConfirmWs> confirmWsList =  confirmClient.getConfirmWs(userId,respondent.getId(),reportDate.format(formatter));
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
            confrimResponse.setResponseInfo(responseInfo);
            confrimResponse.setConfirmWsList(confirmWsList);
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            confrimResponse.setResponseInfo(responseInfo);
            return confrimResponse;
        }
        return confrimResponse;
    }

    @Override
    public ResponseInfo confrimApprove(ConfrimApproveRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();

        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserInfo().getUserToken(),
                    request.getUserInfo().getUser(),
                    request.getUserInfo().getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                return responseInfo;
            }
            Long userId = wsDao.getUserIdByName(request.getUserInfo().getUser());
            confirmClient.approve(userId,request.getIdConfirm(),request.getDocumentHash(),request.getSignature(),
                                  request.getUserNameSigning(),request.getSignType());

            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
        }
        return responseInfo;
    }
}
