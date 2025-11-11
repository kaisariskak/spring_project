package kz.bsbnb.usci.ws.modal.protocol;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.model.ws.EntityError;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

import java.util.List;

@JacksonXmlRootElement(
        localName = "EntityErrorResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "EntityErrorRequest",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "Информация об ответе (код, время, текст) , Детализация пакетного файла ")
public class EntityErrorResponse {
    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе (код, время, текст)", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "protocolList", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Детализация пакетного файла", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<EntityError> entityErrorList;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public List<EntityError> getEntityErrorList() {
        return entityErrorList;
    }

    public void setEntityErrorList(List<EntityError> entityErrorList) {
        this.entityErrorList = entityErrorList;
    }
}
