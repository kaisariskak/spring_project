package kz.bsbnb.usci.core.pki_validator_new;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.gamma.cms.Pkcs7Data;
import kz.gov.pki.kalkan.asn1.*;
import kz.gov.pki.kalkan.asn1.cms.Attribute;
import kz.gov.pki.kalkan.asn1.cms.AttributeTable;
import kz.gov.pki.kalkan.asn1.x509.PolicyInformation;
import kz.gov.pki.kalkan.asn1.x509.X509Extensions;
import kz.gov.pki.kalkan.asn1.x509.X509Name;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.jce.provider.cms.*;
import kz.gov.pki.kalkan.ocsp.OCSPException;
import kz.gov.pki.kalkan.xmldsig.KncaXS;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Проверка ЭЦП НУЦ в соответствии с Правилами проверки подлинности электронной
 * цифровой подписи (https://adilet.zan.kz/rus/docs/V1500012864#z7)
 */
public class PkiValidator {

    private static final Pattern OID_PATTERN = Pattern.compile("^1\\.2\\.398\\.3\\.3\\.2(\\.[1-4])?$");

    private static final String BIN_OID = "2.5.4.11";
    private static final String IIN_OID = "2.5.4.5";

    /**
     * Проверка подписанного XML
     * <p>
     * В случае обнаружения ошибок выбрасывается PkiValidationException
     * <p>
     * Выполняются следующие проверки:
     * <ol>
     * <li>Проверка соответствия подписи и подписанных данных
     * <li>Проверка срока действия сертификата
     * <li>Проверка области использования сертификата (для авторизации или для
     * ЭЦП)
     * <li>Проверка политики регистрационного свидетельства (допустимы только
     * политики НУЦ для юридических и физических лиц)
     * <li>Проверка на отозванность сертификата с помощью OCSP запроса
     * <li>Проверка цепочки сертификатов, соответствие с корневым сертификатом
     * НУЦ
     * </ol>
     * Не выполняется проверка метки времени, т.к. у нас в ЭЦП метка времени не
     * проставляется.
     *
     * @param xmlString
     * @param usage
     * @throws PkiValidationException - если обнаружена ошибка при проверке
     * @return объект SignatureInfo, если подпись прошла проверку, то из
     * сертификата извлекаются данные о субъекте (ИИН, БИН)
     */
    public static /*SignatureInfo*/Map<String, Object>  validate(String binNo, String xmlString, String hash,KeyUsage usage) {
        String xml;
        SignerInformation signer = null;
        try {

            if (binNo == null) {
                throw new UsciException("BIN_NOT_FOUND_FOR_RESPONDENT");
            }
            if (xmlString == null || xmlString.isEmpty()) {
                throw new UsciException("SIGNATURE_IS_EMPTY");
            }
            setupProvider();
            xml = getSignature(xmlString);
            //checkHash(xml,hash);
            XMLSignature signature = extractSignature(xmlString);
            X509Certificate cert = signature.getKeyInfo().getX509Certificate();
            checkSignature(cert, signature);
            checkDates(cert);
            checkUsage(cert, usage);
            checkPolicies(cert);
            Algorithm algorithm = checkAlgorithm(cert);
            OcspRequest.check(cert, algorithm);


            Map<String, Object> params = new HashMap<>();
            params.put("info", cert.getSubjectX500Principal().getName());

            //Pkcs7Data pkcs = new Pkcs7Data(kz.gamma.util.encoders.Base64.decode(xml));
            DateFormat dateFormat= new SimpleDateFormat("yyy/MM/dd");
            Date signDate = new Date(); //retrieveSigningTimePks(pkcs);
            String strDate = dateFormat.format(signDate);
            Date dateSing = dateFormat.parse(strDate);
            LocalDateTime signTime = LocalDateTime.ofInstant(dateSing.toInstant(), ZoneId.systemDefault());
            params.put("signTime", signTime);


            /*Etot block poka vremenno dobavil */
            String ncaKey = algorithm.getNcaKey();
            Boolean  foundNca = ncaKey.contains("gost2015");
            if (foundNca) {
                checkChain(cert, algorithm);
            }


            /*signer.verify(cert, "KALKAN");

            Map<String, Object> params = new HashMap<>();
            params.put("info", cert.getSubjectDN().getName());
            Date signDate = retrieveSigningTime(signer);
            LocalDateTime signTime = LocalDateTime.ofInstant(signDate.toInstant(), ZoneId.systemDefault());
            params.put("signTime", signTime);*/



           return params;//extractSignatureInfo(cert);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PkiValidationException(PkiValidationMessage.UNEXPECTED_ERROR, ex);
        }
    }
    private static Date retrieveSigningTimePks(Pkcs7Data pkcs) throws Exception {
        byte[] attrValue = pkcs.getAttributeByOid("1.2.840.113549.1.9.5");

        kz.gamma.asn1.ASN1InputStream extensionStream = null;
        try {
            extensionStream = new kz.gamma.asn1.ASN1InputStream(attrValue);
            kz.gamma.asn1.DERUTCTime signingTime = (kz.gamma.asn1.DERUTCTime) extensionStream.readObject();
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

    private static void setupProvider() {
        Provider provider = new KalkanProvider();
        Security.addProvider(provider);
        KncaXS.loadXMLSecurity();
    }

    private static XMLSignature extractSignature(String xmlString) throws
            IOException, XMLSecurityException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
        Element rootEl = (Element) doc.getFirstChild();
        NodeList list = rootEl.getElementsByTagName("ds:Signature");
        if (list.getLength() < 1) {
            throw new PkiValidationException(PkiValidationMessage.SIGNATURE_NOT_FOUND);
        }
        if (list.getLength() > 1) {
            throw new PkiValidationException(PkiValidationMessage.MORE_THAN_ONE_SIGNATURE);
        }
        Node sigNode = list.item(0);
        Element sigElement = (Element) sigNode;
        XMLSignature signature = new XMLSignature(sigElement, "");

        return signature;
    }

    private static String getSignature (String signedXml){
        String sign = null;
        String batchXml= null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document doc = documentBuilder.parse(new ByteArrayInputStream(signedXml.getBytes("UTF-8")));

            Element sigElement = null;
            Element rootEl = (Element) doc.getFirstChild();

            NodeList list = rootEl.getElementsByTagName("ds:Signature");
            int length = list.getLength();
            for (int i = 0; i < length; i++) {
                Node sigNode = list.item(length - 1);
                sigElement = (Element) sigNode;
                if (sigElement == null) {
                    throw new UsciException("Ошибка при валидации ЭЦП: Bad signature: Element 'ds:Reference' is not found in XML document\"");
                }
                XMLSignature signature = new XMLSignature(sigElement, "");
                KeyInfo ki = signature.getKeyInfo();
                /*cert = ki.getX509Certificate();
                if (cert != null) {
                    checkSign = signature.checkSignatureValue(cert);
                    rootEl.removeChild(sigElement);
                }*/
                StringWriter os = new StringWriter();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer trans = tf.newTransformer();
                trans.transform(new DOMSource(doc), new StreamResult(os));
                os.close();
                batchXml = os.toString();
                Element x509node = (Element) sigElement.getElementsByTagName("ds:X509Certificate").item(0);
                sign = x509node.getTextContent();


                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                InputSource src = new InputSource();
                src.setCharacterStream(new StringReader(signedXml));
                Document doc1 = builder.parse(src);
                String data = doc1.getElementsByTagName("data").item(0).getTextContent();

                System.out.println(data);
            }
        } catch (Exception e) {
            throw new UsciException(String.format("Ошибка при валидации ЭЦП: %s", e.getMessage()));
        }

        return sign;
    }
    private static void checkHash(String signature, String hash) throws UsciException {
        try {
            CMSSignedData e = new CMSSignedData(Base64.getDecoder().decode(signature));

            boolean isAttachedContent = e.getSignedContent() != null;
            if (isAttachedContent) {
                e = new CMSSignedData(e.getEncoded());
            } else {
                CMSProcessableByteArray signers = new CMSProcessableByteArray(hash.getBytes(StandardCharsets.UTF_8));
                e = new CMSSignedData(signers, e.getEncoded());
            }

            byte[] signedContent = (byte[]) e.getSignedContent().getContent();
            String s = new String(signedContent);

            if (!s.equals(hash)) {
                throw new UsciException("SIGNATURE_IS_INCORRECT");
            }
        } catch (Exception e) {
            throw new UsciException("SIGNATURE_IS_INCORRECT");
        }

    }
    private static Date retrieveSigningTime(SignerInformation signer) throws Exception {
        AttributeTable attributes = signer.getSignedAttributes();
        Attribute at = attributes.get(new DERObjectIdentifier("1.2.840.113549.1.9.5"));
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
    private static void checkSignature(X509Certificate cert, XMLSignature signature)
            throws XMLSignatureException, RuntimeException {
        if (cert == null) {
            throw new PkiValidationException(PkiValidationMessage.CERTIFICATE_NOT_FOUND);
        }
        if (!signature.checkSignatureValue(cert)) {
            throw new PkiValidationException(PkiValidationMessage.INVALID_SIGNATURE);
        }
    }

    private static void checkDates(X509Certificate cert) throws RuntimeException {
        try {
            cert.checkValidity(new Date());
        } catch (CertificateExpiredException ex) {
            throw new PkiValidationException(PkiValidationMessage.CERTIFICATE_EXPIRED);
        } catch (CertificateNotYetValidException ex) {
            throw new PkiValidationException(PkiValidationMessage.CERTIFICATE_NOT_YET_VALID);
        }
    }

    /**
     * Проверка области использования ЭЦП регистрационного свидетельства.
     * <p>
     * Проверка заключается в проверке значения поля регистрационного
     * свидетельства «использование ключа» (KeyUsage). Значения «Цифровая
     * подпись» и «Неотрекаемость», содержащиеся в поле «использование ключа»,
     * означают что, это регистрационное свидетельство используется для ЭЦП.
     * Значения «Цифровая подпись» и «Шифрование ключей», содержащиеся в поле
     * «использование ключа», означают что, это регистрационное свидетельство
     * используется для аутентификации;
     *
     * @param cert
     * @param usage
     */
    private static void checkUsage(X509Certificate cert, KeyUsage usage) {
        boolean[] certUsage = cert.getKeyUsage();
        if (usage == KeyUsage.AUTH) {
            if (!(certUsage[0] && certUsage[2])) {
                throw new PkiValidationException(PkiValidationMessage.NOT_AN_AUTH_KEY);
            }
        } else {
            if (!(certUsage[0] && certUsage[1])) {
                throw new PkiValidationException(PkiValidationMessage.NOT_A_SIGN_KEY);
            }
        }
    }

    /**
     * Проверка номера политики регистрационного свидетельства и разрешенных
     * способах его использования.
     * <p>
     * <b>Согласно Правилам:</b>Политика проверяемого регистрационного
     * свидетельства содержит разрешенные и запрещенные способы использования
     * регистрационного свидетельства (например: регистрационное свидетельство
     * используется в информационной системе "Казначейство-клиент"), это
     * означает, что данное регистрационное свидетельство не может
     * использоваться в других информационных системах, за исключением
     * информационной системы "Казначейство-клиент";
     * <p>
     * Список всех ОИД можно посмотреть на https://root.gov.kz/oid/ Для НУЦ
     * определены четыре номера:
     * <ul>
     * <li>1.2.398.3.3.2.1 Политика применения регистрационных свидетельств
     * электронной цифровой подписи юридических лиц Республики Казахстан
     * <li>1.2.398.3.3.2.2 Политика применения регистрационных свидетельств
     * аутентификации юридических лиц Республики Казахстан
     * <li>1.2.398.3.3.2.3 Политика применения регистрационных свидетельств
     * электронной цифровой подписи физических лиц Республики Казахстан
     * <li>1.2.398.3.3.2.4 Политика применения регистрационных свидетельств
     * аутентификации физических лиц Республики Казахстан
     * </ul>
     * В последнем тестовом сертификате используется значение:<p>
     * 1.2.398.3.3.2 Политики применения регистрационных свидетельств
     * @param cert
     * @throws IOException
     */
    private static void checkPolicies(X509Certificate cert) throws IOException {
        byte[] extvalue = cert.getExtensionValue(X509Extensions.CertificatePolicies.getId());
        if (extvalue == null) {
            throw new PkiValidationException(PkiValidationMessage.OID_CHECK_FAILED);
        }
        //проверьте extvalue на null
        try ( ASN1InputStream stream = new ASN1InputStream(new ByteArrayInputStream(extvalue))) {
            DEROctetString oct = (DEROctetString) stream.readObject();
            try ( ASN1InputStream inStream = new ASN1InputStream(new ByteArrayInputStream(oct.getOctets()))) {
                ASN1Sequence aSN1Sequence = (ASN1Sequence) inStream.readObject();
                for (int i = 0; i < aSN1Sequence.size(); i++) {
                    PolicyInformation pi = new PolicyInformation((ASN1Sequence) aSN1Sequence.getObjectAt(i));
                    String oid = pi.getPolicyIdentifier().getId();
                    if (!OID_PATTERN.matcher(oid).matches()) {
                        throw new PkiValidationException(PkiValidationMessage.OID_MISMATCH, oid);
                    }
                }
            }
        }
    }

    private static X509Certificate loadCertificateFromResources(String certFile) throws GeneralSecurityException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509", KalkanProvider.PROVIDER_NAME);
        return (X509Certificate) factory.generateCertificate(PkiValidator.class.getResourceAsStream(certFile));
    }

    public static X509Certificate getNcaCertificate(Algorithm algorithm) throws GeneralSecurityException {
        return loadCertificateFromResources(algorithm.getNcaKey());
    }

    private static X509Certificate getRootCertificate(Algorithm algorithm) throws GeneralSecurityException {
        return loadCertificateFromResources(algorithm.getRootKey());
    }

    private static X509CRL loadCrlFromResources(String crlFile) throws GeneralSecurityException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509", KalkanProvider.PROVIDER_NAME);
        return (X509CRL) factory.generateCRL(PkiValidator.class.getResourceAsStream(crlFile));
    }

    private static X509CRL getCrl(Algorithm algorithm) throws GeneralSecurityException {
        return algorithm.getCrl() == null ? null : loadCrlFromResources(algorithm.getCrl());
    }

    private static X509CRL getDeltaCrl(Algorithm algorithm) throws GeneralSecurityException {
        return algorithm.getDeltaCrl() == null ? null : loadCrlFromResources(algorithm.getDeltaCrl());
    }

    private static void checkChain(X509Certificate cert, Algorithm algorithm) throws IOException, GeneralSecurityException {
        try {
            Provider provider = Security.getProvider(KalkanProvider.PROVIDER_NAME);
            X509Certificate ca = getRootCertificate(algorithm);
            X509Certificate nca = getNcaCertificate(algorithm);
            X509CRL crl = getCrl(algorithm);
            X509CRL deltaCrl = getDeltaCrl(algorithm);
            ArrayList<X509Extension> list = new ArrayList<>();
            list.add(ca);
            list.add(nca);
            list.add(cert);
            if (crl != null) {
                list.add(crl);
            }
            if (deltaCrl != null) {
                list.add(deltaCrl);
            }
            CollectionCertStoreParameters certStoreParameters = new CollectionCertStoreParameters(list);
            CertStore certStore = CertStore.getInstance("Collection", certStoreParameters, provider);
            CertPathBuilder builder = CertPathBuilder.getInstance("PKIX", provider);
            TrustAnchor anchor = new TrustAnchor(nca, null);
            Set<TrustAnchor> anchors = new HashSet<>();
            anchors.add(anchor);
            X509CertSelector selector = new X509CertSelector();
            selector.setSerialNumber(cert.getSerialNumber());
            selector.setIssuer(cert.getIssuerX500Principal());
            PKIXBuilderParameters builderParameters = new PKIXBuilderParameters(anchors, selector);
            builderParameters.setRevocationEnabled(crl != null);
            builderParameters.addCertStore(certStore);
            builderParameters.setSigProvider(provider.getName());
            builder.build(builderParameters);
        } catch (CertPathBuilderException cpbe) {
            throw new PkiValidationException(PkiValidationMessage.CHAIN_VALIDATION_FAILED, cpbe);
        }
    }

    private static SignatureInfo extractSignatureInfo(X509Certificate cert) {
        X509Name xname = new X509Name(cert.getSubjectDN().getName());
        String bin = getValueByOid(xname, BIN_OID);
        if (bin != null) {
            bin = bin.replaceAll("^BIN", "");
        }
        String iin = getValueByOid(xname, IIN_OID);
        if (iin != null) {
            iin = iin.replaceAll("^IIN", "");
        }
        return new SignatureInfo(bin, iin);
    }

    private static String getValueByOid(X509Name xname, String oid) {
        try {
            return xname.getValues(new DERObjectIdentifier(oid)).firstElement().toString();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private static Algorithm checkAlgorithm(X509Certificate cert) {
        Algorithm algorithm = Algorithm.getAlgorithm(cert.getSigAlgOID());
        if (algorithm == null) {
            throw new PkiValidationException(PkiValidationMessage.UNRECOGNIZED_ALGORITHM);
        }
        return algorithm;
    }
}
