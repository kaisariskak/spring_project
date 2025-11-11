package kz.bsbnb.usci.ws.modal;

import kz.bsbnb.usci.model.respondent.Respondent;

public class UserValidationResult {
    private final boolean success;
    private final int errorCode;
    private final String errorMessage;
    private final Long userId;
    private final Respondent respondent;

    private UserValidationResult(boolean success, int errorCode, String errorMessage,
                                 Long userId, Respondent respondent) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userId = userId;
        this.respondent = respondent;
    }

    public static UserValidationResult success(Long userId, Respondent respondent) {
        return new UserValidationResult(true, 0, null, userId, respondent);
    }

    public static UserValidationResult error(int code, String message) {
        return new UserValidationResult(false, code, message, null, null);
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
