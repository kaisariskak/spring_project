package kz.bsbnb.usci.model.ws;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class CrossCheckMessageWs {
    @JacksonXmlProperty(localName = "innerValue", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "5000", description = "Значение в АИС ДЕПРЕГ", requiredMode = Schema.RequiredMode.REQUIRED)
    private String innerValue;
    @JacksonXmlProperty(localName = "outerValue", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "5000", description = "Значение во внешнем источнике", requiredMode = Schema.RequiredMode.REQUIRED)
    private String outerValue;
    @JacksonXmlProperty(localName = "diff", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "0", description = "Расхождение", requiredMode = Schema.RequiredMode.REQUIRED)
    private String diff;
    @JacksonXmlProperty(localName = "description", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "77777", description = "Показатель", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    public CrossCheckMessageWs() {}

    public String getInnerValue() {
        return innerValue;
    }

    public void setInnerValue(String innerValue) {
        this.innerValue = innerValue;
    }

    public String getOuterValue() {
        return outerValue;
    }

    public void setOuterValue(String outerValue) {
        this.outerValue = outerValue;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
