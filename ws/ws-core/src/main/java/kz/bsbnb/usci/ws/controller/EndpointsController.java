package kz.bsbnb.usci.ws.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bsbnb.usci.ws.modal.authtoken.AuthTokenRequest;
import kz.bsbnb.usci.ws.modal.authtoken.AuthTokenResponse;
import kz.bsbnb.usci.ws.modal.confirm.ConfrimApproveRequest;
import kz.bsbnb.usci.ws.modal.confirm.ConfrimResponse;
import kz.bsbnb.usci.ws.modal.crosscheck.CrossCheckResponse;
import kz.bsbnb.usci.ws.modal.info.ReportInfo;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import kz.bsbnb.usci.ws.modal.protocol.EntityErrorResponse;
import kz.bsbnb.usci.ws.modal.protocol.ProtocolRequest;
import kz.bsbnb.usci.ws.modal.protocol.ProtocolResponse;
import kz.bsbnb.usci.ws.modal.report.OutputFormResponse;
import kz.bsbnb.usci.ws.modal.report.RunReportResponse;
import kz.bsbnb.usci.ws.modal.report.UserReportResponse;
import kz.bsbnb.usci.ws.modal.uscientity.EntitiesRequest;
import kz.bsbnb.usci.ws.modal.uscientity.EntitiesResponse;
import kz.bsbnb.usci.ws.service.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/ws")
@Tag(name = "DEPREG Web Services", description = "API-сервисы для взаимодействия с системой Депозитного регистра")
public class EndpointsController {
    private final DataUploadSigningService dataUploadSigningService;
    private final AuthTokenService authTokenService;
    private final ProtocolService protocolService;
    private final ReportService reportService;
    private final CrossCheckService crossCheckService;
    private final ConfirmService confirmService;

    public EndpointsController(DataUploadSigningService dataUploadSigningService, AuthTokenService authTokenService, ProtocolService protocolService, ReportService reportService, CrossCheckService crossCheckService, ConfirmService confirmService) {
        this.dataUploadSigningService = dataUploadSigningService;
        this.authTokenService = authTokenService;
        this.protocolService = protocolService;
        this.reportService = reportService;
        this.crossCheckService = crossCheckService;
        this.confirmService = confirmService;
    }
    @Operation(
            summary = "Получение токена авторизации",
            description = "Выполняет аутентификацию пользователя и возвращает токен для последующих запросов. " +
                    "Токен необходимо использовать во всех остальных запросах к API."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Токен успешно получен",
                    content = @Content(schema = @Schema(implementation = AuthTokenResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/auth-token", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<AuthTokenResponse> getAuthToken(@Valid @RequestBody AuthTokenRequest request) {
        return ResponseEntity.ok(authTokenService.getAuthToken(request));
    }

    @Operation(
            summary = "Загрузка данных с цифровой подписью",
            description = "Загружает данные сущностей в систему USCI с цифровой подписью. " +
                    "Используется для передачи основных данных (кредиты, депозиты и т.д.). " +
                    "Данные должны быть подписаны валидной ЭЦП."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно загружены",
                    content = @Content(schema = @Schema(implementation = EntitiesResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/data-upload-signing", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<EntitiesResponse> getUsciEntities(@Valid @RequestBody EntitiesRequest request) {
        return ResponseEntity.ok(dataUploadSigningService.getUsciEntities(request));
    }

    @Operation(
            summary = "Получение списка протоколов загрузки",
            description = "Возвращает список протоколов загрузки данных с информацией о статусе обработки, " +
                    "количестве загруженных сущностей, ошибках и других метаданных. " +
                    "Позволяет отслеживать результаты загрузки данных."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список протоколов успешно получен",
                    content = @Content(schema = @Schema(implementation = ProtocolResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/protocol/get-protocol-list", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ProtocolResponse> getProtocolList(@Valid @RequestBody ProtocolRequest request) {
        return ResponseEntity.ok(protocolService.getProtocolList(request));
    }

    @Operation(
            summary = "Получение списка ошибок сущностей",
            description = "Возвращает детальную информацию об ошибках, возникших при обработке сущностей. " +
                    "Включает текст ошибки, проблемную сущность и системную информацию. " +
                    "Используется для диагностики и исправления данных."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список ошибок успешно получен",
                    content = @Content(schema = @Schema(implementation = EntityErrorResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/protocol/get-entity-error-list", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<EntityErrorResponse> getEntityErrorList(@Valid @RequestBody UserInfoRequest request) {
        return ResponseEntity.ok(protocolService.getEntityErrorResponseList(request));
    }

    @Operation(
            summary = "Запуск формирования отчета",
            description = "Инициирует процесс формирования выходной формы (витрины данных) в системе USCI. " +
                    "Отчет формируется асинхронно. Для получения результата используйте метод get-user-report. " +
                    "Укажите код продукта, дату отчета и название витрины."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос на формирование отчета принят",
                    content = @Content(schema = @Schema(implementation = RunReportResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/report/run-report", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResponseInfo> callRunReport(@Valid @RequestBody ReportInfo request) {
        return ResponseEntity.ok(reportService.callRunReport(request));
    }

    @Operation(
            summary = "Получение списка доступных выходных форм",
            description = "Возвращает список всех доступных выходных форм (витрин данных) для указанного продукта. " +
                    "Используйте полученные названия форм при запуске отчетов через run-report."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список форм успешно получен",
                    content = @Content(schema = @Schema(implementation = OutputFormResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/report/get-outputform-list", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<OutputFormResponse> getOutputFormList(@Valid @RequestBody UserInfoRequest request) {
        return ResponseEntity.ok(reportService.getOutputFormList(request));
    }

    @Operation(
            summary = "Получение сформированного отчета",
            description = "Возвращает ранее сформированный отчет (выходную форму) в виде файла. " +
                    "Включает статус формирования, время создания и сам файл в base64. " +
                    "Перед вызовом убедитесь, что отчет был запущен через run-report и его формирование завершено."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет успешно получен",
                    content = @Content(schema = @Schema(implementation = UserReportResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/report/get-user-report", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<UserReportResponse> getUserReport(@Valid @RequestBody ReportInfo request) {
        return ResponseEntity.ok(reportService.getUserReport(request));
    }

    @Operation(
            summary = "Получение сформированного МФК",
            description = "Возвращает ранее сформированный МФК за указанную отчётную дату."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "МФК успешно получен",
                    content = @Content(schema = @Schema(implementation = CrossCheckResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/report/getCrossCheck", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CrossCheckResponse> getCrossCheck(@Valid @RequestBody UserInfoRequest request) {
        return ResponseEntity.ok(crossCheckService.getCrossCheckList(request));
    }

    @Operation(
            summary = "Запуск МФК",
            description = "Запуск МФК на отчётную дату."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запуск МФК успешно",
                    content = @Content(schema = @Schema(implementation = ResponseInfo.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/report/runCrosscheck", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResponseInfo> runCrosscheck(@Valid @RequestBody UserInfoRequest request) {
        return ResponseEntity.ok(crossCheckService.callCrossCheck(request));
    }
    @Operation(
            summary = "Подтверждение",
            description = "Подтверждение отчетной даты."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Подтверждение успешно",
                    content = @Content(schema = @Schema(implementation = ResponseInfo.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/confirm/confrimApprove", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResponseInfo> confrimApprove(@Valid @RequestBody ConfrimApproveRequest request) {
        return ResponseEntity.ok(confirmService.confrimApprove(request));
    }
    @Operation(
            summary = "Список отчётов для подтверждения",
            description = "Список отчётов для подтверждения на отчётную дату"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "успешно",
                    content = @Content(schema = @Schema(implementation = ConfrimResponse.class))),
            @ApiResponse(responseCode = "666", description = "Ошибка запроса",
                    content = @Content(mediaType = "application/xml", schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/confirm/getConfirmList",  produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ConfrimResponse> getConfirmList(@Valid @RequestBody UserInfoRequest request) {
        return ResponseEntity.ok(confirmService.getConfirmList(request));
    }
}
