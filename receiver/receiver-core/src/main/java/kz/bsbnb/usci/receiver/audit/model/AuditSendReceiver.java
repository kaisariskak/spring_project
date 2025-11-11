package kz.bsbnb.usci.receiver.audit.model;

public class AuditSendReceiver {
    public String uuid;
    public String code;
    public String errorText;
    public Long idUser;
    public String comments;


    public AuditSendReceiver() {
    }

    public AuditSendReceiver(String uuid, String code, String errorText, Long idUser, String comments) {
        this.uuid = uuid;
        this.code = code;
        this.errorText = errorText;
        this.idUser = idUser;
        this.comments=comments;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
