package kz.bsbnb.usci.receiver.pki_validator_new;

import java.util.HashMap;
import java.util.Map;

public enum Algorithm {
    GOST("1.2.398.3.10.1.1.1.2",
            "/keys/nca_gost.cer",
            "/keys/root_gost.crt",
            "/keys/nca_gost_test.crl",
            "/keys/nca_d_gost_test.crl",
            "http://www.w3.org/2001/04/xmldsig-more#gost34310-gost34311",
            "http://www.w3.org/2001/04/xmldsig-more#gost34311"
    ),
    GOST2015("1.2.398.3.10.1.1.2.3.2",
            "/keys/nca_gost_2022.cer",
            "/keys/root_gost_2022.cer",
            null,
            null,
            "urn:ietf:params:xml:ns:pkigovkz:xmlsec:algorithms:gostr34102015-gostr34112015-512",
            "urn:ietf:params:xml:ns:pkigovkz:xmlsec:algorithms:gostr34112015-512"
    ),
    RSA("1.2.840.113549.1.1.11",
            "/keys/nca_rsa.cer",
            "/keys/root_rsa.crt",
            "/keys/nca_rsa_test.crl",
            "/keys/nca_d_rsa_test.crl",
            "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256",
            "http://www.w3.org/2001/04/xmlenc#sha256"
    );

    private final String oid;
    private final String ncaKey;
    private final String rootKey;
    private final String crl;
    private final String deltaCrl;
    private final String signMethod;
    private final String digestMethod;

    /*  
    signMethod = 
        } else {
            signMethod = ;
            digestMethod = 
    
    String signMethod = ";
     */
    private Algorithm(String oid, String ncaKey, String rootKey, String crl, String deltaCrl, String signMethod, String digestMethod) {
        this.oid = oid;
        this.ncaKey = ncaKey;
        this.rootKey = rootKey;
        this.crl = crl;
        this.deltaCrl = deltaCrl;
        this.signMethod = signMethod;
        this.digestMethod = digestMethod;
    }

    public String getOid() {
        return oid;
    }

    public String getNcaKey() {
        return ncaKey;
    }

    public String getRootKey() {
        return rootKey;
    }

    public String getCrl() {
        return crl;
    }

    public String getDeltaCrl() {
        return deltaCrl;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public String getDigestMethod() {
        return digestMethod;
    }

    private static final Map<String, Algorithm> algorithmByOid = new HashMap<>();

    static {
        for (Algorithm value : Algorithm.values()) {
            algorithmByOid.put(value.getOid(), value);
        }
    }

    public static Algorithm getAlgorithm(String oid) {
        return algorithmByOid.get(oid);
    }

}
