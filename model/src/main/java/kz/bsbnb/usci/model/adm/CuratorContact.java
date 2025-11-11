package kz.bsbnb.usci.model.adm;

import java.math.BigInteger;

public class CuratorContact {
    private BigInteger curatorContactId;
    private Long ccOrdNumber;
    private String productNameRu;
    private String productNameKk;
    private String productCode;
    private String curatorNameRu;
    private String curatorNameKk;
    private String email;
    private String phone;
    private String department;


    public BigInteger getCuratorContactId() {
        return curatorContactId;
    }

    public void setCuratorContactId(BigInteger curatorContactId) {
        this.curatorContactId = curatorContactId;
    }

    public Long getCcOrdNumber() {
        return ccOrdNumber;
    }

    public void setCcOrdNumber(Long ccOrdNumber) {
        this.ccOrdNumber = ccOrdNumber;
    }

    public String getProductNameRu() {
        return productNameRu;
    }

    public void setProductNameRu(String productNameRu) {
        this.productNameRu = productNameRu;
    }

    public String getProductNameKk() {
        return productNameKk;
    }

    public void setProductNameKk(String productNameKk) {
        this.productNameKk = productNameKk;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCuratorNameRu() {
        return curatorNameRu;
    }

    public void setCuratorNameRu(String curatorNameRu) {
        this.curatorNameRu = curatorNameRu;
    }

    public String getCuratorNameKk() {
        return curatorNameKk;
    }

    public void setCuratorNameKk(String curatorNameKk) {
        this.curatorNameKk = curatorNameKk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

