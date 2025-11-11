package kz.bsbnb.usci.model.ws;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConfirmWs {
    @JacksonXmlProperty(localName = "idConfirm", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "777", description = "Идентификатор подтверждения отчётной даты", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idConfirm;
    @JacksonXmlProperty(localName = "respondentName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Тестовая организация", description = "Наименование организации", requiredMode = Schema.RequiredMode.REQUIRED)
    private String respondentName;
    @JacksonXmlProperty(localName = "statusname", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Не подтвержден ", description = "Статус подтверждения", requiredMode = Schema.RequiredMode.REQUIRED)
    private String statusName;
    @JacksonXmlProperty(localName = "reportDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата отчёта (формат: yyyy-MM-dd)", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate reportDate;
    @JacksonXmlProperty(localName = "userName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testuser", description = "Пользователь", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;
    @JacksonXmlProperty(localName = "firstBatchLoadTime", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "10.10.2025 15:51:32", description = "Дата первого приема информации", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime firstBatchLoadTime;
    @JacksonXmlProperty(localName = "lastBatchLoadTime", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "29.10.2025 15:06:32", description = "Дата последнего приема информации", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime lastBatchLoadTime;
    @JacksonXmlProperty(localName = "crossCheckStatusName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Выполнен", description = "Статус МФК", requiredMode = Schema.RequiredMode.REQUIRED)
    private String crossCheckStatusName;
    @JacksonXmlProperty(localName = "productName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "DEPOSIT", description = "Продукт", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productName;

    public ConfirmWs() {  }

    public Long getIdConfirm() {
        return idConfirm;
    }

    public void setIdConfirm(Long idConfirm) {
        this.idConfirm = idConfirm;
    }

    public String getRespondentName() {
        return respondentName;
    }

    public void setRespondentName(String respondentName) {
        this.respondentName = respondentName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getFirstBatchLoadTime() {
        return firstBatchLoadTime;
    }

    public void setFirstBatchLoadTime(LocalDateTime firstBatchLoadTime) {
        this.firstBatchLoadTime = firstBatchLoadTime;
    }

    public LocalDateTime getLastBatchLoadTime() {
        return lastBatchLoadTime;
    }

    public void setLastBatchLoadTime(LocalDateTime lastBatchLoadTime) {
        this.lastBatchLoadTime = lastBatchLoadTime;
    }

    public String getCrossCheckStatusName() {
        return crossCheckStatusName;
    }

    public void setCrossCheckStatusName(String crossCheckStatusName) {
        this.crossCheckStatusName = crossCheckStatusName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
