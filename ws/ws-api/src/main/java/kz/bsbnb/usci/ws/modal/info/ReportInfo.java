package kz.bsbnb.usci.ws.modal.info;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(
        localName = "ReportInfo",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "ReportInfo",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "Содержит информацию о пользователе, отчетная дата, код продукта , код банка, наименование выходной формы")
public class ReportInfo {
    @JacksonXmlProperty(localName = "user", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testUser", description = "Имя пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String user;
    @JacksonXmlProperty(localName = "userToken", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6...", description = "JWT или OAuth2 токен авторизации", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userToken;
    @JacksonXmlProperty(localName = "respondentCode", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "000", description = "Код респондента", requiredMode = Schema.RequiredMode.REQUIRED)
    private String respondentCode;
    @JacksonXmlProperty(localName = "productCode", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "DEPOSIT", description = "Код продукта", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productCode;
    @JacksonXmlProperty(localName = "reportDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата отчёта (формат: dd.MM.yyyy)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reportDate;
    @JacksonXmlProperty(localName = "vitrina", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "VREP_DEPOSIT_CHANGE", description = "Наименование выходной формы", requiredMode = Schema.RequiredMode.REQUIRED)
    private String vitrina;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getRespondentCode() {
        return respondentCode;
    }

    public void setRespondentCode(String respondentCode) {
        this.respondentCode = respondentCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getVitrina() {
        return vitrina;
    }

    public void setVitrina(String vitrina) {
        this.vitrina = vitrina;
    }
}
