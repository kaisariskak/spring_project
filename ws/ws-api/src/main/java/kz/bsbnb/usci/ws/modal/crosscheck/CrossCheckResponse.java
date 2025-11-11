package kz.bsbnb.usci.ws.modal.crosscheck;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.model.ws.CrossCheckMessageWs;
import kz.bsbnb.usci.model.ws.CrossCheckWs;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

import java.util.List;

@JacksonXmlRootElement(
        localName = "CrossCheckResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "CrossCheckResponse",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "Информация об ответе (код, время, текст) , Список сформированных МФК")
public class CrossCheckResponse {
    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе (код, время, текст)", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "crossCheckList", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Список сформированных МФК", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<CrossCheckWs> crossCheckList;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public List<CrossCheckWs> getCrossCheckList() {
        return crossCheckList;
    }

    public void setCrossCheckList(List<CrossCheckWs> crossCheckList) {
        this.crossCheckList = crossCheckList;
    }
}
