package kz.bsbnb.usci.ws.modal.authtoken;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

@JacksonXmlRootElement(
        localName = "AuthTokenResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "AuthTokenResponse", description = "Ответ на запрос получения токена авторизации")
public class AuthTokenResponse {
    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "userToken", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI...", description = "JWT или OAuth2 токен")
    private String userToken;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
