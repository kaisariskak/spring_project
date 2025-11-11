package kz.bsbnb.usci.receiver.sign.kisc;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.gamma.asn1.ASN1InputStream;
import kz.gamma.asn1.DEROctetString;
import kz.gamma.asn1.DERSequence;
import kz.gamma.asn1.DERTaggedObject;
import kz.gamma.asn1.ocsp.OCSPResponseStatus;
import kz.gamma.cms.CMSException;
import kz.gamma.cms.Pkcs7Data;
import kz.gamma.jce.X509Principal;
import kz.gamma.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.*;
import java.util.*;

public class CSP_Tumar {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSP_Tumar.class);

    public static X509Certificate getCertificateFromString(String pCert)
            throws CertStoreException, NoSuchAlgorithmException, NoSuchProviderException, CMSException {
        LOGGER.trace(":: CSP_Tumar :: getCertificateFromString");
        byte[] bytes;
        X509Certificate cert;
        bytes = Base64.decode(pCert);
        Pkcs7Data pkcs7 = new Pkcs7Data(bytes);
        cert = pkcs7.getCertificateOfSigner();
        LOGGER.trace(":: CSP_Tumar :: return the certificate");
        LOGGER.debug(":: CSP_Tumar :: cert {}", cert);
        return cert;
    }

    public static X509Certificate convertToX509Certificate(String pem) {
        CertificateFactory certFactory;
        X509Certificate cert = null;
        String cerHead = "-----BEGIN CERTIFICATE-----\n";
        String cerEnd = "\n-----END CERTIFICATE-----";
        String certStr = cerHead.concat(pem).concat(cerEnd);

        try {
            certFactory = CertificateFactory.getInstance("X.509");
            InputStream in = new ByteArrayInputStream(certStr.getBytes());
            cert = (X509Certificate) certFactory.generateCertificate(in);
        } catch (CertificateException e) {
            throw new UsciException(e.getMessage());
        }
        return cert;
    }

    public static String[] getBinIinFromCertificate(X509Certificate userCertificate) {
        LOGGER.trace(":: CSP_Tumar :: getBinIinFromCertificate");
        String[] data = null;
        ASN1InputStream extensionStream = null;

        try {
            byte[] extensionBytes =
                    userCertificate.getExtensionValue("2.5.29.17");
            LOGGER.info(":: CSP_Tumar :: extensionBytes: {}", Arrays.toString(extensionBytes));

            if (extensionBytes != null) {

                extensionStream = new ASN1InputStream(extensionBytes);
                DEROctetString octetString = (DEROctetString) extensionStream.readObject();
                extensionStream.close();
                extensionStream = new ASN1InputStream(octetString.getOctets());
                DERSequence sequence = (DERSequence) extensionStream.readObject();
                extensionStream.close();

                Enumeration subjectAltNames = sequence.getObjects();
                String bin = null;
                String iin = null;
                List<String[]> aliasList = new ArrayList<String[]>();

                while (subjectAltNames.hasMoreElements()) {
                    DERTaggedObject nextElement = (DERTaggedObject) subjectAltNames.nextElement();
                    X509Principal x509Principal = new X509Principal(nextElement.getObject().getEncoded());
                    aliasList.add(x509Principal.toString().split(","));
                }

                LOGGER.info(":: CSP_Tumar :: alias size = {}", aliasList.size());

                if (aliasList.size() == 3) {
                    bin = getSerialNumber(aliasList.get(1));
                    iin = getSerialNumber(aliasList.get(2));
                } else {
                    iin = getSerialNumber(aliasList.get(1));
                }

                if (bin != null && iin != null) { // est' i bin i iin
                    LOGGER.debug(":: CSP_Tumar :: bin={}", bin);
                    LOGGER.debug(":: CSP_Tumar :: iin={}", iin);
                    data = new String[2];
                    data[1] = bin;
                    data[0] = iin;
                } else if (iin != null) { // tol'ko iin
                    LOGGER.debug(":: CSP_Tumar :: iin={}", iin);
                    data = new String[1];
                    data[0] = iin;
                }

            }
        } catch (Exception e) {
            LOGGER.error(":: CSP_Tumar :: CERT=" + userCertificate.getSubjectX500Principal(), e);
        } finally {

            if (extensionStream != null) {
                try {
                    extensionStream.close();
                } catch (IOException e) {
                    LOGGER.error(":: CERT=" + userCertificate.getSubjectX500Principal(), e);
                }
            }

        }
        return data;
    }

    private static String getSerialNumber(String[] str) {
        String result = null;
        for (String s : str) {
            String[] strValues = s.split("=");
            if (strValues[0].equals("SERIALNUMBER")) {
                result = strValues[1];
                break;
            }
        }
        return result;
    }

    public static boolean checkCert(X509Certificate cert, String oscpUrl) throws UsciException {
        try {
            cert.checkValidity(new Date());
        } catch (CertificateExpiredException ce) {
            throw new UsciException(ce.getMessage());
        } catch (CertificateNotYetValidException cnyve) {
            throw new UsciException(cnyve.getMessage());
        } catch (Exception e) {
            throw new UsciException(e.getMessage());
        }

        try {
            int ocspResult = OcspRequest.getOCSPStatus(cert, oscpUrl);

            LOGGER.info("RESULT STATUS OSCP: {}, cert={}", ocspResult, cert.getIssuerX500Principal());

            if (ocspResult == OCSPResponseStatus.SUCCESSFUL) {
                return true;
            }

            throw new UsciException("CertificateEXCEPTION");
        } catch (Exception e) {
            LOGGER.error("CertificateEXCEPTION cert=" + cert.getSubjectDN(), e);
            throw new UsciException(e.getMessage());
        }
    }
}

