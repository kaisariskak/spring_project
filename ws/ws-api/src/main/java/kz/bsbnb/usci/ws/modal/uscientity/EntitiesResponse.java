package kz.bsbnb.usci.ws.modal.uscientity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

@JacksonXmlRootElement(
        localName = "EntitiesResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "EntitiesResponse", description = "Ответ на передачу данных в DEPREG")
public class EntitiesResponse {

    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе (код, время, текст)", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "generatedBatchName", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "BATCH_WS", description = "Имя сформированного пакета данных", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String generatedBatchName;

    // --- Getters/Setters ---
    public ResponseInfo getResponseInfo() { return responseInfo; }
    public void setResponseInfo(ResponseInfo responseInfo) { this.responseInfo = responseInfo; }

    public String getGeneratedBatchName() { return generatedBatchName; }
    public void setGeneratedBatchName(String generatedBatchName) { this.generatedBatchName = generatedBatchName; }
}
