package kz.bsbnb.usci.model.batch;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Approval implements Serializable {
    private Long id;
    private Long classId;
    private Long respondentTypeId;
    private LocalDate reportDate;
    private LocalDateTime deadLine;

    public Approval() {}

    public Approval(Long id, Long classId, Long respondentTypeId, LocalDate reportDate, LocalDateTime deadLine) {
        this.id = id;
        this.classId = classId;
        this.respondentTypeId = respondentTypeId;
        this.reportDate = reportDate;
        this.deadLine = deadLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getRespondentTypeId() {
        return respondentTypeId;
    }

    public void setRespondentTypeId(Long respondentTypeId) {
        this.respondentTypeId = respondentTypeId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }
}
