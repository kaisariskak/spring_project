package kz.bsbnb.usci.model.ws;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntityError {
    @JacksonXmlProperty(localName = "fileName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "test.zip", description = "batch файл", requiredMode = Schema.RequiredMode.REQUIRED)
    private String  fileName;
    @JacksonXmlProperty(localName = "reportDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата отчёта (формат: dd.MM.yyyy)", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate reportDate;
    @JacksonXmlProperty(localName = "systemDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2025-09-25T15:32:00+05:00", description = "Дата получения", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime systemDate;
    @JacksonXmlProperty(localName = "errorMessage", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Ошибка БД", description = "Текст с ошибками", requiredMode = Schema.RequiredMode.REQUIRED)
    private String errorMessage;
    @JacksonXmlProperty(localName = "entityText", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2222", description = "Описание сущности", requiredMode = Schema.RequiredMode.REQUIRED)
    private String entityText;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public LocalDateTime getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(LocalDateTime systemDate) {
        this.systemDate = systemDate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getEntityText() {
        return entityText;
    }

    public void setEntityText(String entityText) {
        this.entityText = entityText;
    }
}
