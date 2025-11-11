package kz.bsbnb.usci.ws.modal.report;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

@JacksonXmlRootElement(
        localName = "RunReportResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "RunReportResponse",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "Информация об ответе (код, время, текст)")
public class RunReportResponse {
    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе (код, время, текст)", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
