package kz.bsbnb.usci.model.ws;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CrossCheckWs {
    @JacksonXmlProperty(localName = "dateBegin", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата начало контроля (формат: dd.MM.yyyy)", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime dateBegin;
    @JacksonXmlProperty(localName = "dateEnd", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата завершения контроля (формат: dd.MM.yyyy)", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime dateEnd;
    @JacksonXmlProperty(localName = "reportDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата отчёта (формат: dd.MM.yyyy)", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate reportDate;
    @JacksonXmlProperty(localName = "statusname", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Выполнен", description = "Статус", requiredMode = Schema.RequiredMode.REQUIRED)
    private String statusname;
    @JacksonXmlProperty(localName = "username", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testuser", description = "Пользователь", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @JacksonXmlProperty(localName = "creditorname", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Тестовая организация", description = "Наименование организации", requiredMode = Schema.RequiredMode.REQUIRED)
    private String creditorname;
    @JacksonXmlProperty(localName = "countQueue", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "1", description = "Количество отчётов в очереди", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long countQueue;
    @JacksonXmlProperty(localName = "crossCheckMessageWsList", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "...", description = "Данные по контролю", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<CrossCheckMessageWs> crossCheckMessageWsList;

    public List<CrossCheckMessageWs> getCrossCheckMessageWsList() {
        return crossCheckMessageWsList;
    }

    public void setCrossCheckMessageWsList(List<CrossCheckMessageWs> crossCheckMessageWsList) {
        this.crossCheckMessageWsList = crossCheckMessageWsList;
    }

    public LocalDateTime getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDateTime dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreditorname() {
        return creditorname;
    }

    public void setCreditorname(String creditorname) {
        this.creditorname = creditorname;
    }

    public Long getCountQueue() {
        return countQueue;
    }

    public void setCountQueue(Long countQueue) {
        this.countQueue = countQueue;
    }
}
