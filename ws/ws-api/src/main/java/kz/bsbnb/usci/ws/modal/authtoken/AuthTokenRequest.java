package kz.bsbnb.usci.ws.modal.authtoken;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.ws.modal.info.RequestInfo;

@JacksonXmlRootElement(
       localName = "AuthTokenRequest",
       namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "AuthTokenRequest", description = "Запрос для получения токена авторизации")
public class AuthTokenRequest {
    /*@JacksonXmlProperty(localName = "requestInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Техническая информация о запросе", requiredMode = Schema.RequiredMode.REQUIRED)
    protected RequestInfo requestInfo;*/
    @JacksonXmlProperty(localName = "user", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testUser", description = "Имя пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String user;
    @JacksonXmlProperty(localName = "userPass", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "secretPass123", description = "Пароль пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userPass;

   /* public RequestInfo getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }*/

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
