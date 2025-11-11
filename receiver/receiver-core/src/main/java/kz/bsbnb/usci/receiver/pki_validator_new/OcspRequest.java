package kz.bsbnb.usci.receiver.pki_validator_new;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Hashtable;
import kz.gov.pki.kalkan.asn1.ASN1InputStream;
import kz.gov.pki.kalkan.asn1.DERObject;
import kz.gov.pki.kalkan.asn1.DEROctetString;
import kz.gov.pki.kalkan.asn1.ocsp.OCSPObjectIdentifiers;
import kz.gov.pki.kalkan.asn1.x509.X509Extension;
import kz.gov.pki.kalkan.asn1.x509.X509Extensions;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.ocsp.*;
import kz.gov.pki.kalkan.util.encoders.Base64;

public class OcspRequest {

    //private static final String OCSP_URL = "http://test.pki.gov.kz/ocsp/";
    private static String OCSP_URL = "http://test.pki.gov.kz/ocsp/";  // dly novogo EDS
    static byte[] nonce;

    public static void check(X509Certificate cert, Algorithm algorithm)
            throws OCSPException, GeneralSecurityException, IOException {
        Security.addProvider(new KalkanProvider());
        X509Certificate cacert = PkiValidator.getNcaCertificate(algorithm);
        byte[] ocspReq = getOcspPackage(cert.getSerialNumber(), cacert, CertificateID.HASH_SHA1);
        String b64Req = new String(Base64.encode(ocspReq));
        String ncaKey = algorithm.getNcaKey();
        Boolean  foundNca = ncaKey.contains("gost2015");
        /*if (foundNca) {
            OCSP_URL = "http://test.pki.gov.kz/ocsp/";
        }
       else {
           OCSP_URL = "http://ocsp.pki.gov.kz";
       }*/
        OCSP_URL = "http://ocsp.pki.gov.kz";
        URL url = new URL((OCSP_URL + "/" + b64Req));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        makeOcspResponse(con);
        con.disconnect();
    }

    private static void makeOcspResponse(HttpURLConnection con)
            throws OCSPException, GeneralSecurityException, IOException {
        OCSPResp response = getResponse(con);
        if (response.getStatus() != 0) {
            throw new PkiValidationException(PkiValidationMessage.OCSP_REQUEST_FAILED, "Unsuccessful request. Status: " + response.getStatus());
        }
        BasicOCSPResp brep = (BasicOCSPResp) response.getResponseObject();
        byte[] respNonceExt = brep
                .getExtensionValue(OCSPObjectIdentifiers.id_pkix_ocsp_nonce
                        .getId());
        if (respNonceExt != null) {
            ASN1InputStream asn1In = new ASN1InputStream(respNonceExt);
            DERObject derObj = asn1In.readObject();
            asn1In.close();
            byte[] extV = DEROctetString.getInstance(derObj).getOctets();
            asn1In = new ASN1InputStream(extV);
            derObj = asn1In.readObject();
            asn1In.close();
            if (!Arrays.equals(nonce, DEROctetString.getInstance(derObj).getOctets())) {
                throw new PkiValidationException(PkiValidationMessage.OCSP_REQUEST_MISMATCH);
            }
        }
        Object status = brep.getResponses()[0].getCertStatus();

        if (status == null) {
            return;
        }
        if (status instanceof RevokedStatus) {
            String message = "OCSP Response is REVOKED.";
            RevokedStatus revokedStatus = (RevokedStatus) status;
            if (revokedStatus.hasRevocationReason()) {
                message += " Time: " + revokedStatus.getRevocationTime();
                message += " Reason: " + revokedStatus.getRevocationReason();
            }
            throw new PkiValidationException(PkiValidationMessage.OCSP_REQUEST_STATUS_REVOKED, message);
        }
        if (status instanceof UnknownStatus) {
            throw new PkiValidationException(PkiValidationMessage.OCSP_REQUEST_STATUS_UNKNOWN);
        }
        throw new PkiValidationException(PkiValidationMessage.OCSP_REQUEST_FAILED, status);
    }

    private static OCSPResp getResponse(HttpURLConnection con) throws IOException {
        OCSPResp response;
        try ( InputStream in = con.getInputStream()) {
            response = new OCSPResp(in);
        }
        return response;
    }

    private static byte[] getOcspPackage(BigInteger serialNr, Certificate cacert, String hashAlg)
            throws OCSPException, IOException {
        OCSPReqGenerator gen = new OCSPReqGenerator();
        CertificateID certId = new CertificateID(hashAlg, (X509Certificate) cacert, serialNr, KalkanProvider.PROVIDER_NAME);
        gen.addRequest(certId);
        gen.setRequestExtensions(generateExtensions());
        return gen.generate().getEncoded();
    }

    private static X509Extensions generateExtensions() {
        SecureRandom sr = new SecureRandom();
        nonce = new byte[8];
        sr.nextBytes(nonce);
        Hashtable exts = new Hashtable();
        X509Extension nonceext = new X509Extension(false,
                new DEROctetString(new DEROctetString(nonce)));
        // добавляем необязательный nonce, случайное число произвольной длины 
        exts.put(OCSPObjectIdentifiers.id_pkix_ocsp_nonce, nonceext);
        return new X509Extensions(exts);
    }
}
