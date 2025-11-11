package kz.bsbnb.usci.model.ws;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class OutputForm {
    @JacksonXmlProperty(localName = "nameForm", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "VREP_DEPOSIT_CHANGE", description = "Список сформированных выходных форм", requiredMode = Schema.RequiredMode.REQUIRED)
    public String nameForm;

    public String getNameForm() {
        return nameForm;
    }

    public void setNameForm(String nameForm) {
        this.nameForm = nameForm;
    }
}
