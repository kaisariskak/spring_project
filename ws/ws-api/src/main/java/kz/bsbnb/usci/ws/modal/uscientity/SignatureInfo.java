package kz.bsbnb.usci.ws.modal.uscientity;

import java.util.Map;

public class SignatureInfo {
    private final String bin;
    private final String iin;
    private final String principal;
    private  final String sing;


    public SignatureInfo(String bin, String iin, String principal, String sing) {
        this.bin = bin;
        this.iin = iin;
        this.principal=principal;
        this.sing=sing;
    }
    public static SignatureInfo fromMap(Map<String, Object> map) {
        String bin = (String) map.get("bin");
        String iin = (String) map.get("iin");
        String principal = (String) map.get("principal");
        String sing = (String) map.get("sing");

        return new SignatureInfo(bin, iin, principal, sing);
    }

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
