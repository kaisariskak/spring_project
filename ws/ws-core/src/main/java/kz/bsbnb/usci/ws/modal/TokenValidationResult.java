package kz.bsbnb.usci.ws.modal;

import com.nimbusds.oauth2.sdk.TokenIntrospectionSuccessResponse;

public  class TokenValidationResult {
    private final boolean success;
    private final int errorCode;
    private final String errorMessage;
    private final TokenIntrospectionSuccessResponse tokenDetails;

    private TokenValidationResult(boolean success, int errorCode, String errorMessage,
                                  TokenIntrospectionSuccessResponse tokenDetails) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.tokenDetails = tokenDetails;
    }

    public static TokenValidationResult success(TokenIntrospectionSuccessResponse tokenDetails) {
        return new TokenValidationResult(true, 0, null, tokenDetails);
    }

    public static TokenValidationResult error(int code, String message) {
        return new TokenValidationResult(false, code, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public TokenIntrospectionSuccessResponse getTokenDetails() {
        return tokenDetails;
    }
}
