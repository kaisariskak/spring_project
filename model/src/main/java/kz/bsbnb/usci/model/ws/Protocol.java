package kz.bsbnb.usci.model.ws;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Protocol {
    @JacksonXmlProperty(localName = "id", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "1", description = "идентификатор", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;
    @JacksonXmlProperty(localName = "reportDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата отчета", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate reportDate;
    @JacksonXmlProperty(localName = "receiverDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2025-09-25T15:32:00+05:00", description = "Дата получения", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime receiverDate;
    @JacksonXmlProperty(localName = "processBeginDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2025-09-25T15:32:00+05:00", description = "Дата начала обработки", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime processBeginDate;
    @JacksonXmlProperty(localName = "processEndDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2025-09-25T15:32:00+05:00", description = "Дата завершения", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime processEndDate;
    @JacksonXmlProperty(localName = "fileName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "batch_file_ws.zip", description = "Имя файла", requiredMode = Schema.RequiredMode.REQUIRED)
    private String fileName;
    @JacksonXmlProperty(localName = "status", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Успешно обработанные , Обработанные с ошибками, На одобрении", description = "Статус", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;
    @JacksonXmlProperty(localName = "respondentName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Наименование организации", description = "Статус", requiredMode = Schema.RequiredMode.REQUIRED)
    private String respondentName;
    @JacksonXmlProperty(localName = "productName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "DEPOSIT", description = "Продукт", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productName;
    @JacksonXmlProperty(localName = "totalEntityCount", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "1000", description = "Всего", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long totalEntityCount = 0L;
    @JacksonXmlProperty(localName = "actualEntityCount", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "1000", description = "Обработанные", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long actualEntityCount = 0L;
    @JacksonXmlProperty(localName = "successEntityCount", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "1000", description = "Успешно обработанные", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long successEntityCount = 0L;
    @JacksonXmlProperty(localName = "errorEntityCount", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "1000", description = "Обработанные с ошибками", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long errorEntityCount = 0L;
    @JacksonXmlProperty(localName = "maintenanceEntityCount", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "1000", description = "На одобрении", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long maintenanceEntityCount = 0L;

    public Protocol() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public LocalDateTime getReceiverDate() {
        return receiverDate;
    }

    public void setReceiverDate(LocalDateTime receiverDate) {
        this.receiverDate = receiverDate;
    }

    public LocalDateTime getProcessBeginDate() {
        return processBeginDate;
    }

    public void setProcessBeginDate(LocalDateTime processBeginDate) {
        this.processBeginDate = processBeginDate;
    }

    public LocalDateTime getProcessEndDate() {
        return processEndDate;
    }

    public void setProcessEndDate(LocalDateTime processEndDate) {
        this.processEndDate = processEndDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespondentName() {
        return respondentName;
    }

    public void setRespondentName(String respondentName) {
        this.respondentName = respondentName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getTotalEntityCount() {
        return totalEntityCount;
    }

    public void setTotalEntityCount(Long totalEntityCount) {
        this.totalEntityCount = totalEntityCount;
    }

    public Long getActualEntityCount() {
        return actualEntityCount;
    }

    public void setActualEntityCount(Long actualEntityCount) {
        this.actualEntityCount = actualEntityCount;
    }

    public Long getSuccessEntityCount() {
        return successEntityCount;
    }

    public void setSuccessEntityCount(Long successEntityCount) {
        this.successEntityCount = successEntityCount;
    }

    public Long getErrorEntityCount() {
        return errorEntityCount;
    }

    public void setErrorEntityCount(Long errorEntityCount) {
        this.errorEntityCount = errorEntityCount;
    }

    public Long getMaintenanceEntityCount() {
        return maintenanceEntityCount;
    }

    public void setMaintenanceEntityCount(Long maintenanceEntityCount) {
        this.maintenanceEntityCount = maintenanceEntityCount;
    }
}
