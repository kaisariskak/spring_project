package kz.bsbnb.usci.receiver.sign.kisc;


import org.w3c.dom.Document;

public class SignVerificationInfo {

    private Document document;

    private String principal;
    private String signatureError;
    private byte[] certificate;

    private boolean valid;
    private boolean revoked;
    private boolean expired;

    private String iin;
    private String bin;

    public SignVerificationInfo() {}

    public SignVerificationInfo(Document document, String principal, String signatureError, byte[] certificate, boolean valid, boolean revoked, boolean expired, String iin, String bin) {
        this.document = document;
        this.principal = principal;
        this.signatureError = signatureError;
        this.certificate = certificate;
        this.valid = valid;
        this.revoked = revoked;
        this.expired = expired;
        this.iin = iin;
        this.bin = bin;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSignatureError() {
        return signatureError;
    }

    public void setSignatureError(String signatureError) {
        this.signatureError = signatureError;
    }

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }
}
