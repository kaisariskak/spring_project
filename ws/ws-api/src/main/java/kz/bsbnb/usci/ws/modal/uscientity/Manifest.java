package kz.bsbnb.usci.ws.modal.uscientity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;

@JacksonXmlRootElement(
        localName = "manifest",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "Manifest", description = "Описание отчётного пакета (продукт, дата, респондент)")
public class Manifest {

    @JacksonXmlProperty(localName = "productCode", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "DEPOSIT", description = "Код продукта/отчёта", requiredMode = Schema.RequiredMode.REQUIRED)
    private String productCode;

    @JacksonXmlProperty(localName = "reportDate", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "01.01.2025", description = "Дата отчёта (формат: dd.MM.yyyy)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reportDate;

    @JacksonXmlProperty(localName = "respondentCode", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "980611301092", description = "БИН/ИИН респондента (организации/пользователя)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String respondentCode;

    // --- Getters/Setters ---
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getReportDate() { return reportDate; }
    public void setReportDate(String reportDate) { this.reportDate = reportDate; }

    public String getRespondentCode() { return respondentCode; }
    public void setRespondentCode(String respondentCode) { this.respondentCode = respondentCode; }
}
