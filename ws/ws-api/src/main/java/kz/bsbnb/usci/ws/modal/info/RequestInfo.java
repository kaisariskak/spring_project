package kz.bsbnb.usci.ws.modal.info;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@JacksonXmlRootElement(
        localName = "requestInfo",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "RequestInfo", description = "Служебная информация о запросе")
public class RequestInfo {

    @JacksonXmlProperty(localName = "requestId", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "REQ-20250925-0001", description = "Уникальный идентификатор запроса", requiredMode = Schema.RequiredMode.REQUIRED)
    private String requestId;

    @JacksonXmlProperty(localName = "requestTime", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "2025-09-25T15:30:00+05:00", description = "Время формирования запроса в ISO-8601 формате", requiredMode = Schema.RequiredMode.REQUIRED)
    private String requestTime;

    // --- Getters/Setters ---
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getRequestTime() { return requestTime; }
    public void setRequestTime(String requestTime) { this.requestTime = requestTime; }
}
