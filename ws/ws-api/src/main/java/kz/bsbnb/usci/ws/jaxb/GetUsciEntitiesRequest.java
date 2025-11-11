
package kz.bsbnb.usci.ws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(localName = "GetUsciEntitiesRequest", namespace = "http://usci.bsbnb.kz/ws/schema")
@Schema(name = "GetUsciEntitiesRequest", description = "Запрос на отправку данных в USCI")
public class GetUsciEntitiesRequest {

    @JacksonXmlProperty(localName = "requestInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    private RequestInfo requestInfo;

    @JacksonXmlProperty(localName = "user", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testUser", description = "Имя пользователя")
    private String user;

    @JacksonXmlProperty(localName = "userToken", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI...", description = "JWT или OAuth2 токен")
    private String userToken;

    @JacksonXmlProperty(localName = "userBin", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "980611301092", description = "БИН/ИИН пользователя")
    private String userBin;

    @JacksonXmlProperty(localName = "manifest", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Манифест: описание продукта и даты")
    private Manifest manifest;

    @JacksonXmlProperty(localName = "data", namespace = "http://usci.bsbnb.kz/ws/schema")
    @JacksonXmlCData
    @Schema(example = "<entities><credit><amount>1000000</amount></credit></entities>", description = "Основные данные (XML в CDATA)")
    private String data;

    // getters/setters
}



