package kz.bsbnb.usci.ws.modal.report;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.model.ws.OutputForm;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

import java.util.List;

@JacksonXmlRootElement(
        localName = "OutputFormResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "OutputFormResponse",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "Информация об ответе (код, время, текст) , Список выходных форм")
public class OutputFormResponse {
    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе (код, время, текст)", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "outputFormList", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Список выходных форм", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<OutputForm> outputFormList;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public List<OutputForm> getOutputFormList() {
        return outputFormList;
    }

    public void setOutputFormList(List<OutputForm> outputFormList) {
        this.outputFormList = outputFormList;
    }
}
