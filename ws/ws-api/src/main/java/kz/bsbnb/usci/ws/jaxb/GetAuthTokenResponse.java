
package kz.bsbnb.usci.ws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@JacksonXmlRootElement(localName = "GetAuthTokenResponse", namespace = "http://usci.bsbnb.kz/ws/schema")
@Schema(name = "GetAuthTokenResponse", description = "Ответ на запрос получения токена")
public class GetAuthTokenResponse {

    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "userToken", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI...", description = "JWT или OAuth2 токен")
    private String userToken;

    // getters/setters
    public ResponseInfo getResponseInfo() { return responseInfo; }
    public void setResponseInfo(ResponseInfo responseInfo) { this.responseInfo = responseInfo; }
    public String getUserToken() { return userToken; }
    public void setUserToken(String userToken) { this.userToken = userToken; }
}
