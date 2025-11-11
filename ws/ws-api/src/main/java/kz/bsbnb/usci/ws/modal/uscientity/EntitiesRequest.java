package kz.bsbnb.usci.ws.modal.uscientity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(
        localName = "EntitiesRequest",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "UsciEntitiesRequest",  description =  "Основной XML-запрос, передаваемый в DEPREG. " +
                                 "Содержит информацию о пользователе, отчётный манифест и тело данных в виде CDATA-блока.")
public class EntitiesRequest {
    /*@JacksonXmlProperty(localName = "requestInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Метаданные запроса (ID и время)", requiredMode = Schema.RequiredMode.REQUIRED)
    private RequestInfo requestInfo;*/

    @JacksonXmlProperty(localName = "user", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testUser", description = "Имя пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String user;

    @JacksonXmlProperty(localName = "userToken", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6...", description = "JWT или OAuth2 токен авторизации", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userToken;

    @JacksonXmlProperty(localName = "userBin", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "0000000000", description = "БИН/ИИН пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userBin;

    @JacksonXmlProperty(localName = "manifest", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация о продукте и дате отчета", requiredMode = Schema.RequiredMode.REQUIRED)
    private Manifest manifest;

    // Вложенные данные в XML (CDATA)
    @JacksonXmlProperty(localName = "data", namespace = "http://usci.bsbnb.kz/ws/schema")
    @JacksonXmlCData
    @Schema(example = "<entities><credit><amount>1000000</amount></credit></entities>",
            description = "Основные данные в виде XML (обернуты в CDATA)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String data;

    @JacksonXmlProperty(localName = "signature", namespace = "http://usci.bsbnb.kz/ws/schema")
    @JacksonXmlCData
    @Schema(example = "<ds:Signature>...</ds:Signature>",
            description = "Цифровая XML-подпись документа (CDATA)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String signature;

    // --- Getters/Setters ---

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getUserToken() { return userToken; }
    public void setUserToken(String userToken) { this.userToken = userToken; }

    public String getUserBin() { return userBin; }
    public void setUserBin(String userBin) { this.userBin = userBin; }

    public Manifest getManifest() { return manifest; }
    public void setManifest(Manifest manifest) { this.manifest = manifest; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
}
