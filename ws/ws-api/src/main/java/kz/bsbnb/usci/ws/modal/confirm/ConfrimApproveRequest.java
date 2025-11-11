package kz.bsbnb.usci.ws.modal.confirm;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.v3.oas.annotations.media.Schema;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import org.springframework.web.bind.annotation.RequestParam;

@JacksonXmlRootElement(
        localName = "ConfrimApproveRequest",
        namespace = "http://usci.bsbnb.kz/ws/schema"
)
@Schema(name = "ConfrimApproveRequest",  description =  "Основной XML-запрос, передаваемый в USCI. " +
        "В ответе возвращается информация (код, время, текст)")
public class ConfrimApproveRequest {
    @JacksonXmlProperty(localName = "idConfirm", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "777", description = "Идентификатор подтверждения отчётной даты", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long idConfirm;
    @JacksonXmlProperty(localName = "userInfo", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(example = "testuser", description = "Содержит информацию о пользователе, отчетная дата, код продукта , код банка", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserInfoRequest userInfo;
    @JacksonXmlProperty(localName = "documentHash", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(
            example = "ef85454e5445easfdfdeer54",
            description = "Хэш документа, сформированный по алгоритму SHA-256.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String documentHash;

    @JacksonXmlProperty(localName = "signature", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(
            example = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><data>\n" +
                    "  <HashAlgorithm>SHA-256</HashAlgorithm>\n" +
                    "  <Hash>e597bb7a5a17aed97d1d3f966ff09fb5</Hash>\n" +
                    "  <ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
                    "    <ds:SignedInfo>...</ds:SignedInfo>\n" +
                    "  </ds:Signature>\n" +
                    "</data>",
            description = "XML-подпись документа в формате XMLDSig, содержащая хэш-алгоритм, значение хэша и X.509 сертификат подписанта.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String signature;

    @JacksonXmlProperty(localName = "signType", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(
            example = "KISC/PKI",
            description = "Тип электронной подписи: KISC — НПА,PKI — НУЦ (национальный удостоверяющий центр).",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String signType;
    @JacksonXmlProperty(localName = "userNameSigning", namespace = "http://usci.bsbnb.kz/ws/schema")
    @Schema(
            example = "ИВАНОВ ИВАН",
            description = "ФИО пользователя, наложившего электронную подпись.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String userNameSigning;

    public String getUserNameSigning() {
        return userNameSigning;
    }

    public void setUserNameSigning(String userNameSigning) {
        this.userNameSigning = userNameSigning;
    }

    public Long getIdConfirm() {
        return idConfirm;
    }

    public void setIdConfirm(Long idConfirm) {
        this.idConfirm = idConfirm;
    }

    public UserInfoRequest getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoRequest userInfo) {
        this.userInfo = userInfo;
    }

    public String getDocumentHash() {
        return documentHash;
    }

    public void setDocumentHash(String documentHash) {
        this.documentHash = documentHash;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
