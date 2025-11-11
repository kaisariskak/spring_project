package kz.bsbnb.usci.core.pki_validator_new;

public class SignatureInfo {

    private final String bin;
    private final String iin;

    public SignatureInfo(String bin, String iin) {
        this.bin = bin;
        this.iin = iin;
    }

    public String getBin() {
        return bin;
    }

    public String getIin() {
        return iin;
    }

}
