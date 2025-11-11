package kz.bsbnb.usci.ws.modal.info;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

@JacksonXmlRootElement(
        localName = "responseInfo",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "ResponseInfo", description = "Информация о результате выполнения запроса")
public class ResponseInfo {

    @JacksonXmlProperty(localName = "responseTime", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2025-09-25T15:32:00+05:00", description = "Время формирования ответа в ISO-8601 формате", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String responseTime;

    @JacksonXmlProperty(localName = "responseCode", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "200", description = "Код ответа (например, HTTP-статус или бизнес-код)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer responseCode;

    @JacksonXmlProperty(localName = "responseText", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "Операция выполнена успешно", description = "Описание результата выполнения запроса", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String responseText;

    // --- Getters/Setters ---
    public String getResponseTime() { return responseTime; }
    public void setResponseTime(String responseTime) { this.responseTime = responseTime; }

    public Integer getResponseCode() { return responseCode; }
    public void setResponseCode(Integer responseCode) { this.responseCode = responseCode; }

    public String getResponseText() { return responseText; }
    public void setResponseText(String responseText) { this.responseText = responseText; }
}
