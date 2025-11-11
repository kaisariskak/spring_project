package kz.bsbnb.usci.ws.modal.report;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.model.ws.UserReportInfo;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;

import java.util.List;

@JacksonXmlRootElement(
        localName = "UserReportResponse",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "UserReportResponse",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "Информация об ответе (код, время, текст) , Список сформированных выходных форм")
public class UserReportResponse {
    @JacksonXmlProperty(localName = "responseInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Информация об ответе (код, время, текст)", requiredMode = Schema.RequiredMode.REQUIRED)
    private ResponseInfo responseInfo;

    @JacksonXmlProperty(localName = "userReportInfoList", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(description = "Список сформированных выходных форм", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<UserReportInfo> userReportInfoList;

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public List<UserReportInfo> getUserReportInfoList() {
        return userReportInfoList;
    }

    public void setUserReportInfoList(List<UserReportInfo> userReportInfoList) {
        this.userReportInfoList = userReportInfoList;
    }
}
