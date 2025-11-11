package kz.bsbnb.usci.ws.exception;

import kz.bsbnb.usci.ws.modal.uscientity.EntitiesResponse;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<EntitiesResponse> handleApi(ApiException ex) {
        ResponseInfo info = new ResponseInfo();

        // Время: если в модели LocalDateTime, оставляем так:
        info.setResponseTime(LocalDateTime.now().toString());

        Integer code = ex.getCode();
        String  codeText = ex.getCodeText();

        if (code == null) {
            // Пытаемся вытащить цифры из строкового кода (REQ-001 -> 1), иначе 999
            code = tryParseIntOrDefault(codeText, 999);
            info.setResponseText("[" + codeText + "] " + ex.getMessage());
        } else {
            info.setResponseText(ex.getMessage());
        }
        info.setResponseCode(code);

        EntitiesResponse body = new EntitiesResponse();
        body.setResponseInfo(info);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_XML)
                .body(body);
    }

    private int tryParseIntOrDefault(String s, int def) {
        if (s == null) return def;
        try {
            // Вытащим только цифры (REQ-001 -> "001")
            String digits = s.replaceAll("\\D", "");
            return digits.isEmpty() ? def : Integer.parseInt(digits);
        } catch (Exception e) {
            return def;
        }
    }
}

