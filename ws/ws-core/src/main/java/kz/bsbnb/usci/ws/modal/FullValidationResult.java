package kz.bsbnb.usci.ws.modal;

import kz.bsbnb.usci.model.respondent.Respondent;

public class FullValidationResult {
    private final boolean success;
    private final int errorCode;
    private final String errorMessage;
    private final Long userId;
    private final Respondent respondent;

    private FullValidationResult(boolean success, int errorCode, String errorMessage,
                                 Long userId, Respondent respondent) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userId = userId;
        this.respondent = respondent;
    }

    public static FullValidationResult success(Long userId, Respondent respondent) {
        return new FullValidationResult(true, 0, null, userId, respondent);
    }

    public static FullValidationResult fromTokenError(TokenValidationResult tokenResult) {
        return new FullValidationResult(false, tokenResult.getErrorCode(),
                tokenResult.getErrorMessage(), null, null);
    }

    public static FullValidationResult fromUserError(UserValidationResult userResult) {
        return new FullValidationResult(false, userResult.getErrorCode(),
                userResult.getErrorMessage(), null, null);
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

    public Long getUserId() {
        return userId;
    }

    public Respondent getRespondent() {
        return respondent;
    }
}

