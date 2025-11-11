package kz.bsbnb.usci.model.ws;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserReportInfo {
    @JacksonXmlProperty(localName = "user", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testuser", description = "Пользователь", requiredMode = Schema.RequiredMode.REQUIRED)
    private String user;
    @JacksonXmlProperty(localName = "respondent", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Bank", description = "Респондент", requiredMode = Schema.RequiredMode.REQUIRED)
    private String respondent;
    @JacksonXmlProperty(localName = "product", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "DEPOSIT", description = "Продукт", requiredMode = Schema.RequiredMode.REQUIRED)
    private String product;
    @JacksonXmlProperty(localName = "tableName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "VREP_DEPOSIT_CHANGE", description = "Витрина", requiredMode = Schema.RequiredMode.REQUIRED)
    private String tableName;
    @JacksonXmlProperty(localName = "reportDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата отчета", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate reportDate;
    @JacksonXmlProperty(localName = "beginDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2025-09-25T15:32:00+05:0", description = "Дата начала формирования", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime beginDate;
    @JacksonXmlProperty(localName = "endDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2025-09-25T14:32:00+05:0", description = "Дата завершения формирования", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime endDate;
    @JacksonXmlProperty(localName = "status", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Выполнено", description = "Статус", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;
    @JacksonXmlProperty(localName = "countQueue", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "100", description = "Количество отчетов в очереди", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long countQueue;
    @JacksonXmlProperty(localName = "file", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "xls", description = "Файл", requiredMode = Schema.RequiredMode.REQUIRED)
    private byte[] file;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRespondent() {
        return respondent;
    }

    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCountQueue() {
        return countQueue;
    }

    public void setCountQueue(Long countQueue) {
        this.countQueue = countQueue;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
