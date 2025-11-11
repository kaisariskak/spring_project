package kz.bsbnb.usci.ws.modal.protocol;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;

@JacksonXmlRootElement(
        localName = "EntityErrorRequest",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "EntityErrorRequest",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "Содержит информацию о пользователе, отчетная дата, код продукта , код банка и Идентификатор batch файла ")
public class EntityErrorRequest {
    @JacksonXmlProperty(localName = "batchId", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "8888", description = "Идентификатор batch файла", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long  batchId;

    @JacksonXmlProperty(localName = "userInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testuser", description = "Содержит информацию о пользователе, отчетная дата, код продукта , код банка", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserInfoRequest userInfo;

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public UserInfoRequest getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoRequest userInfo) {
        this.userInfo = userInfo;
    }
}
