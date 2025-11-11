package kz.bsbnb.usci.core.pki_validator_new;

public class PkiValidationException extends RuntimeException {

    private final PkiValidationMessage validationMessage;
    private Object ocspStatus;
    private String additionalInfo;

    public PkiValidationException(PkiValidationMessage validationMessage) {
        super(validationMessage.toString());
        this.validationMessage = validationMessage;
    }

    public PkiValidationException(PkiValidationMessage validationMessage, String additionalInfo) {
        this(validationMessage);
        this.additionalInfo = additionalInfo;
    }

    public PkiValidationException(PkiValidationMessage validationMessage, Exception inner) {
        super(validationMessage.toString(), inner);
        this.validationMessage = validationMessage;
    }

    public PkiValidationException(PkiValidationMessage validationMessage, Object ocspStatus) {
        this(validationMessage);
        this.ocspStatus = ocspStatus;
    }

    public PkiValidationMessage getValidationMessage() {
        return validationMessage;
    }

    public Object getOcspStatus() {
        return ocspStatus;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

}
