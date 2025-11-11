package kz.bsbnb.usci.ws.modal.confirm;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.model.ws.ConfirmWs;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

import java.util.List;

@JacksonXmlRootElement(
        localName = "ConfrimResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "ConfrimResponse",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "В ответе возвращается информация (код, время, текст), а также список подтверждений отчётной даты.")
public class ConfrimResponse {
    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе (код, время, текст)", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "outputFormList", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Список подтверждений отчётной даты", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ConfirmWs> confirmWsList;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public List<ConfirmWs> getConfirmWsList() {
        return confirmWsList;
    }

    public void setConfirmWsList(List<ConfirmWs> confirmWsList) {
        this.confirmWsList = confirmWsList;
    }
}
