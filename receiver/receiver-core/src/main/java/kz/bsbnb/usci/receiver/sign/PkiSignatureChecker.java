package kz.bsbnb.usci.receiver.sign;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.receiver.model.json.BatchSignJson;
import kz.gov.pki.kalkan.asn1.*;
import kz.gov.pki.kalkan.asn1.cms.Attribute;
import kz.gov.pki.kalkan.asn1.cms.AttributeTable;
import kz.gov.pki.kalkan.asn1.x509.X509NameTokenizer;
import kz.gov.pki.kalkan.jce.X509Principal;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.jce.provider.cms.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author Aidar.Myrzahanov
 */
public class PkiSignatureChecker {

    private static final String ROOT_CA_NAME = "CN=ҰЛТТЫҚ КУӘЛАНДЫРУШЫ ОРТАЛЫҚ (GOST),C=KZ";
    private final String binNo;
    private final String ocspServiceUrl;

    Logger log = LoggerFactory.getLogger(PkiSignatureChecker.class);

    public PkiSignatureChecker(String binNo, String ocspServiceUrl) {
        this.binNo = binNo;
        this.ocspServiceUrl = ocspServiceUrl;
    }

    public void checkAndUpdate(BatchSignJson batch, String binParent) throws UsciException {
        Provider provider;
        if (Security.getProvider(KalkanProvider.PROVIDER_NAME) == null) {
            provider = new KalkanProvider();
            Security.addProvider(provider);
        }
        if (binNo == null) {
            throw new UsciException("BIN_NOT_FOUND_FOR_RESPONDENT");
        }
        if (batch.getSignature() == null || batch.getSignature().isEmpty()) {
            throw new UsciException("SIGNATURE_IS_EMPTY");
        }

        try {
            CMSSignedData e = new CMSSignedData(java.util.Base64.getDecoder().decode(batch.getSignature()));
            boolean isAttachedContent = e.getSignedContent() != null;
            if (isAttachedContent) {
                e = new CMSSignedData(e.getEncoded());
            } else {
                CMSProcessableByteArray signers = new CMSProcessableByteArray(batch.getHash().getBytes(StandardCharsets.UTF_8));
                e = new CMSSignedData(signers, e.getEncoded());
            }

            byte[] signedContent = (byte[]) e.getSignedContent().getContent();
            String s = new String(signedContent);
            checkHash(s, batch.getHash());

            SignerInformationStore signers1 = e.getSignerInfos();
            CertStore certs = e.getCertificatesAndCRLs("Collection", new KalkanProvider().getName());
            Iterator it = signers1.getSigners().iterator();
            X509Certificate cert = null;
            SignerInformation signer = null;
            while (it.hasNext()) {
                signer = (SignerInformation) it.next();
                SignerId signerConstraints = signer.getSID();
                Collection certCollection = certs.getCertificates(signerConstraints);
                for (Object aCertCollection : certCollection) {
                    cert = (X509Certificate) aCertCollection;

                    checkDates(cert);
                    checkIssuer(cert);
                    checkBin(cert,binParent);
                    checkStatus(cert);
                    signer.verify(cert, "KALKAN");
                }
            }
            batch.setInformation(cert.getSubjectDN().getName());
            Date signDate = retrieveSigningTime(signer);
            LocalDateTime signTime = LocalDateTime.ofInstant(signDate.toInstant(), ZoneId.systemDefault());
            batch.setSigningTime(signTime);
        } catch (UsciException sve) {
            throw sve;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsciException("UNEXPECTED_EXCEPTION_DURING_SIGNATURE_VALIDATION " + e.getMessage());
        }
    }

    private void checkHash(String hashFromSign, String signedValue) throws UsciException {
        try {
            if (!signedValue.equals(hashFromSign)) {
                throw new UsciException("SIGNATURE_IS_INCORRECT");
            }
        } catch (Exception e) {
            throw new UsciException("SIGNATURE_IS_INCORRECT");
        }

    }

    private void checkDates(X509Certificate certificate) throws UsciException {
        Date now = new Date();
        if (now.after(certificate.getNotAfter())) {
            throw new UsciException("SIGNATURE_IS_OVERDUE, certificate.getNotAfter()");
        }
        if (now.before(certificate.getNotBefore())) {
            throw new UsciException("SIGNATURE_IS_NOT_ACTIVE_YET, certificate.getNotBefore()");
        }
    }

    private void checkIssuer(X509Certificate certificate) throws UsciException {
        String issuerName = certificate.getIssuerX500Principal().getName();
        System.out.println(issuerName);
        if (!ROOT_CA_NAME.equals(issuerName)) {
            throw new UsciException("WRONG_ROOT_CA_NAME, issuerName");
        }
    }

    private void checkBin(X509Certificate certificate,String binParent) throws UsciException, IOException, CertificateParsingException {
        String bin = extractBinFromCertificate(certificate);
        if (bin == null) {
            throw new UsciException("SIGNATURE_WITHOUT_BIN");
        }
        if(binParent!=null) {
            if (!bin.equals(binParent)) {
                throw new UsciException("CHILD SIGNATURE DOES NOT MATCH WITH PARENT");
            }
        }
        else if (!bin.equals(binNo)) {
            throw new UsciException("SIGNATURE_BIN_DOESNT_MATCH, bin, binNo");
        }
    }

    private String extractBinFromCertificate(X509Certificate certificate) throws IOException, UsciException, CertificateParsingException {
            return extractBinFromCertificate_New(certificate);
    }

    private String extractBinFromCertificate_New(X509Certificate userCertificate) throws IOException, UsciException, CertificateParsingException {

        if (userCertificate.getSubjectAlternativeNames() == null) {
            System.out.println("subjectalternatives PUSTOI");
            String subjectDN = userCertificate.getSubjectDN().toString();
            System.out.println(subjectDN);
            String OU;
            String dnpart = "OU=";
            String BIN = null;
            X509NameTokenizer xt = new X509NameTokenizer(subjectDN);
            while (xt.hasMoreTokens()) {
                OU = xt.nextToken();
                if ((OU.length() > dnpart.length()) && OU.substring(0, dnpart.length()).equalsIgnoreCase(dnpart)) {
                    BIN = OU.substring(dnpart.length()+3);
                }
            }

            System.out.println(BIN);
            return BIN;
        } else {

            String[] data = null;
            ASN1InputStream extensionStream = null;

            try {

                byte[] extensionBytes = userCertificate.getExtensionValue("2.5.29.17");
                if (extensionBytes == null) {
                    throw new UsciException("SUBJECT_ALTERNATIVE_NAME_FIELD_IS_EMPTY");
                }

                extensionStream = new ASN1InputStream(extensionBytes);
                DEROctetString octetString = (DEROctetString) extensionStream.readObject();
                extensionStream.close();
                extensionStream = new ASN1InputStream(octetString.getOctets());
                DERSequence sequence = (DERSequence) extensionStream.readObject();
                extensionStream.close();
                Enumeration subjectAltNames = sequence.getObjects();
                int counter = 0;
                String str1 = null;
                String str2 = null;

                while (subjectAltNames.hasMoreElements()) {
                    DERTaggedObject nextElement = (DERTaggedObject) subjectAltNames.nextElement();
                    X509Principal x509Principal = new X509Principal(nextElement.getObject().getEncoded());
                    if (counter == 1) {
                        String extensionData = x509Principal.toString();
                        Map<String, String> hashmap = convertBIN_IIN(extensionData.trim());
                        str1 = hashmap.get("SERIALNUMBER"); // BIN or IIN
                    } else if (counter == 2) {
                        String extensionData = x509Principal.toString();
                        Map<String, String> hashmap = convertBIN_IIN(extensionData.trim());
                        str2 = hashmap.get("SERIALNUMBER"); // IIN
                    }
                    counter++;
                }

                if (str2 != null && str1 != null) { // est' i bin i iin
                    data = new String[2];
                    data[0] = str2;
                    data[1] = str1;
                } else if (str1 != null) { // tol'ko iin
                    data = new String[1];
                    data[0] = str1;
                }

            } finally {
                if (extensionStream != null) {
                    try {
                        extensionStream.close();
                    } catch (IOException e) {
                    }
                }
            }

            if (data == null) return null;
            if (data.length == 2) return data[1]; // BIN
            if (data.length == 1) return data[0]; // IIN

            return null;
        }
    }

    private Map<String, String> convertBIN_IIN(String extensionData) {

        Map<String, String> hashmap = new HashMap<>();
        String[] aliasStr = extensionData.split(",");

        for (int i = 0; i < aliasStr.length; i++) {
            int index_of_equal = aliasStr[i].lastIndexOf("=");
            String str1 = aliasStr[i].substring(index_of_equal + 1);
            String str2 = aliasStr[i].substring(0, index_of_equal);
            hashmap.put(str2, str1);
        }

        return hashmap;

    }

    private void checkStatus(X509Certificate certificate) throws UsciException {
        if (ocspServiceUrl == null || " ".equals(ocspServiceUrl)) {
            return;
        }
        PkiOcspRequest request = new PkiOcspRequest(ocspServiceUrl);
        request.check(certificate);
    }

    private Date retrieveSigningTime(SignerInformation signer) throws Exception {
        AttributeTable attributes = signer.getSignedAttributes();
        Attribute at = attributes.get(new kz.gov.pki.kalkan.asn1.DERObjectIdentifier("1.2.840.113549.1.9.5"));
        DEREncodable obj = at.getAttrValues().getObjectAt(0);
        byte[] attrValue = obj.getDERObject().getEncoded();

        ASN1InputStream extensionStream = null;
        try {
            extensionStream = new ASN1InputStream(attrValue);
            DERUTCTime signingTime = (DERUTCTime) extensionStream.readObject();
            return signingTime.getAdjustedDate();
        } finally {
            if (extensionStream != null) {
                try {
                    extensionStream.close();
                } catch (IOException ioe) {
                    //log.log(Level.SEVERE, "", ioe);
                }
            }
        }
    }

}
