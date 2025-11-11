package kz.bsbnb.usci.ws.modal.protocol;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.model.ws.Protocol;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

import java.util.List;

@JacksonXmlRootElement(
        localName = "ProtocolResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "ProtocolResponse", description = "Протокол загрузки данных")
public class ProtocolResponse {
    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе (код, время, текст)", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "protocolList", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация о файлах", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Protocol> protocolList;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public List<Protocol> getProtocolList() {
        return protocolList;
    }

    public void setProtocolList(List<Protocol> protocolList) {
        this.protocolList = protocolList;
    }
}
