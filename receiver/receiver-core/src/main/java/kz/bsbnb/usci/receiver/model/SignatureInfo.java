package kz.bsbnb.usci.receiver.model;

public class SignatureInfo {
    private  String bin;
    private  String iin;
    private  String principal;
    private  String sing;


    public SignatureInfo(String bin, String iin, String principal, String sing) {
        this.bin = bin;
        this.iin = iin;
        this.principal=principal;
        this.sing=sing;
    }
    public SignatureInfo() {}

    public String getBin() {
        return bin;
    }

    public String getIin() {
        return iin;
    }
    public String getPrincipal() {
        return principal;
    }

    public String getSing() {
        return sing;
    }
}
