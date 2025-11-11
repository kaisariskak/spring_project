package kz.bsbnb.usci.wsclient.model.gbdul;

import kz.bsbnb.usci.model.persistence.Persistable;

public class GBDULEntity extends Persistable {
    private String bin;
    private String fullName;
    private String privateEnterpriseTypeCode;
    private String privateEnterpriseTypeNameRu;
    private String privateEnterpriseTypeNameKz;
    private String requestStatus;

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrivateEnterpriseTypeCode() {
        return privateEnterpriseTypeCode;
    }

    public void setPrivateEnterpriseTypeCode(String privateEnterpriseTypeCode) {
        this.privateEnterpriseTypeCode = privateEnterpriseTypeCode;
    }

    public String getPrivateEnterpriseTypeNameRu() {
        return privateEnterpriseTypeNameRu;
    }

    public void setPrivateEnterpriseTypeNameRu(String privateEnterpriseTypeNameRu) {
        this.privateEnterpriseTypeNameRu = privateEnterpriseTypeNameRu;
    }

    public String getPrivateEnterpriseTypeNameKz() {
        return privateEnterpriseTypeNameKz;
    }

    public void setPrivateEnterpriseTypeNameKz(String privateEnterpriseTypeNameKz) {
        this.privateEnterpriseTypeNameKz = privateEnterpriseTypeNameKz;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

}
