package kz.bsbnb.usci.ws.exception;

public class ApiException extends Exception{
    private final Integer code;     // числовой код (если есть)
    private final String codeText;  // строковый код (если есть)

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.codeText = null;
    }

    public ApiException(String codeText, String message) {
        super(message);
        this.code = null;
        this.codeText = codeText;
    }

    public Integer getCode()     { return code; }
    public String  getCodeText() { return codeText; }
}
