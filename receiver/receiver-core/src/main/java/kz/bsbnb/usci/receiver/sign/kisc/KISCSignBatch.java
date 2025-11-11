package kz.bsbnb.usci.receiver.sign.kisc;

//import com.sun.org.apache.xpath.internal.XPathAPI;

import com.sun.org.apache.xpath.internal.XPathAPI;
import kz.bsbnb.usci.receiver.pki_validator_new.PkiValidationException;
import kz.bsbnb.usci.receiver.pki_validator_new.PkiValidationMessage;
import kz.gamma.asn1.x509.X509Name;
import kz.gamma.jce.provider.GammaTechProvider;
import kz.gamma.tumarcsp.params.StoreObjectParam;
import kz.gamma.xmldsig.JCPXMLDSigInit;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.jce.provider.cms.CMSSignedData;
import kz.gov.pki.kalkan.jce.provider.cms.SignerInformationStore;
import kz.gov.pki.kalkan.xmldsig.KncaXS;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.XMLUtils;
//import org.apache.xpath.XPathAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.util.Base64;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

public class KISCSignBatch {

    private static final Logger logger = LoggerFactory.getLogger(KISCSignBatch.class);


    /**
     * Создаем экземпляр класса для работы с TumarCSP.
     * Данный метод загружает все ключи и сертификаты доступные в данный момент
     *     *
     */
    public static KeyStore loadKeyStore() throws NoSuchProviderException,
            KeyStoreException,
            IOException,
            NoSuchAlgorithmException,
            CertificateException {
        KeyStore store = KeyStore.getInstance("PKS", "GAMMA");
        store.load(null, null);
        return store;
    }

    /**
     * Создаем экземпляр класса для работы с TumarCSP.
     * Данный метод загружает ключи из выбранного профайла, при этом можно задать пароль на профайл
     *
     *
     */
    public static KeyStore loadKeyStore(String profileName, String pass) throws NoSuchProviderException,
            KeyStoreException,
            IOException,
            NoSuchAlgorithmException,
            CertificateException {
        KeyStore store = KeyStore.getInstance("GKS", "GAMMA");
        store.load(new ByteArrayInputStream(profileName.getBytes()), pass.toCharArray());
        return store;
    }

    /**
     * Функция создает экземпляр класса приватного ключа для подписи.
     * Если будет несколько сертификатов с одним именем то загрузить самый новый
     *
     */
    public static PrivateKey getPrivateKey(String DName, KeyStore store, String pass) throws KeyStoreException,
            NoSuchAlgorithmException,
            UnrecoverableKeyException {
        Enumeration en = store.aliases();
        Date tmpDate = null;
        String tmpSN = "";
        while (en.hasMoreElements()) {
            StoreObjectParam prm = (StoreObjectParam) en.nextElement();
            if ((new X509Name(DName)).equals(new X509Name(prm.dn))) {
                if (tmpDate == null) {
                    tmpDate = prm.timeCreate;
                    tmpSN = prm.sn;
                } else {
                    if (prm.timeCreate.after(tmpDate)) {
                        tmpDate = prm.timeCreate;
                        tmpSN = prm.sn;
                    }
                }
            }
        }
        return (PrivateKey) store.getKey(tmpSN, pass.toCharArray());
    }

    /**
     * Функция создает экземпляр класса сертификата.
     * Если будет несколько сертификатов с одним именем то загрузить самый новый
     *
     */
    public static Certificate getCertificate(String DName, KeyStore store, String pass) throws KeyStoreException {
        Enumeration en = store.aliases();
        Date tmpDate = null;
        String tmpSN = "";
        while (en.hasMoreElements()) {
            StoreObjectParam prm = (StoreObjectParam) en.nextElement();
            if ((new X509Name(DName)).equals(new X509Name(prm.dn))) {
                if (tmpDate == null) {
                    tmpDate = prm.timeCreate;
                    tmpSN = prm.sn;
                } else {
                    if (prm.timeCreate.after(tmpDate)) {
                        tmpDate = prm.timeCreate;
                        tmpSN = prm.sn;
                    }
                }
            }
        }
        return store.getCertificate(tmpSN);
    }

    /**
     * Функция создает цепочку сертификатов.
     * Если будет несколько сертификатов с одним именем то загрузить самый новый
     *
     * @param DName
     * @param store
     * @param pass
     */
    public static Certificate[] getCertificateChain(String DName, KeyStore store, String pass) throws KeyStoreException {
        Enumeration en = store.aliases();
        Date tmpDate = null;
        String tmpSN = "";
        while (en.hasMoreElements()) {
            StoreObjectParam prm = (StoreObjectParam) en.nextElement();
            if ((new X509Name(DName)).equals(new X509Name(prm.dn))) {
                if (tmpDate == null) {
                    tmpDate = prm.timeCreate;
                    tmpSN = prm.sn;
                } else {
                    if (prm.timeCreate.after(tmpDate)) {
                        tmpDate = prm.timeCreate;
                        tmpSN = prm.sn;
                    }
                }
            }
        }
        return store.getCertificateChain(tmpSN);
    }

    /**
     * Метод формирования подписи xml документа
     */
    public static Document signXML(Document doc, Certificate cert, PrivateKey privKey)
            throws Exception {
        String signMethod = "http://www.w3.org/2001/04/xmldsig-more#gost34310-gost34311";
        String digestMethod = "http://www.w3.org/2001/04/xmldsig-more#gost34311";
        XMLSignature sig = new XMLSignature(doc, "", signMethod);
        String res = "";
        if (doc.getFirstChild() != null) {
            doc.getFirstChild().appendChild(sig.getElement());
            Transforms transforms = new Transforms(doc);
            transforms.addTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature");
            transforms.addTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments");
            sig.addDocument("", transforms, digestMethod);
            sig.addKeyInfo((X509Certificate) cert);
            sig.sign(privKey);
            StringWriter os = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
            os.flush();
            res = os.toString();
            os.close();
        }
        return parseXml(res);
    }

    /**
     * Метод проверки подписи xml документа
     *
     *
     */
    public static boolean validateXML(Document doc)
            throws Exception {
        // Если уже один раз был объявлен данный метод, то его повторно не нужно объявлять
        //JCPXMLDSigInit.init();
        Element nscontext = XMLUtils.createDSctx(doc, "ds", "http://www.w3.org/2000/09/xmldsig#");
        Element sigElement = (Element) XPathAPI.selectSingleNode(doc, "//ds:Signature[1]", nscontext);
        XMLSignature signature = new XMLSignature(sigElement, "");
        KeyInfo ki = signature.getKeyInfo();
        X509Certificate certKey = ki.getX509Certificate();
        boolean result = false;
        logger.info("::KISC CER={}, IssuerDN={}, IssuerX500Principal ={}", certKey.getSubjectDN(), certKey.getIssuerDN(), certKey.getIssuerX500Principal());
        if (certKey != null) {
            try {
                String iinBin[] = CSP_Tumar.getBinIinFromCertificate(certKey);
                result = signature.checkSignatureValue(certKey);
                String oscpUrl = "http://91.195.226.34:62255";//http://ca.kisc.kz:62255";
                CSP_Tumar.checkCert(certKey, oscpUrl);
            } catch (Exception exp) {
                logger.error("", exp);
            }
        } else {
            PublicKey pk = ki.getPublicKey();
            if (pk != null)
                result = signature.checkSignatureValue(pk);
            else
                throw new Exception("Нет информации об открытом ключе. Проверка невозможна.");
        }
        return result;
    }


    /**
     * Метод проверки подписи xml документа
     *
     */
    public static SignVerificationInfo validateXML(String fileBase64, String oscpUrl)
            throws Exception {
        // Если уже один раз был объявлен данный метод, то его повторно не нужно объявлять

        byte[] bytesFile = Base64.getDecoder().decode(fileBase64);
        String xmlString = new String(bytesFile,"utf-8");
        setupProvider();
        JCPXMLDSigInit.init();
        //setupProvider();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));

        SignVerificationInfo signVerificationInfo = null;
        signVerificationInfo = new SignVerificationInfo();
        signVerificationInfo.setDocument(doc);

        /*Element nscontext = XMLUtils.createDSctx(doc, "ds", "http://www.w3.org/2000/09/xmldsig#");
        Element sigElement = (Element) XPathAPI.selectSingleNode(doc, "//Signature[1]", nscontext);
        XMLSignature signature = new XMLSignature(sigElement, "");*/

        XMLSignature signature = extractSignature(xmlString);

        KeyInfo ki = signature.getKeyInfo();
        X509Certificate certKey = ki.getX509Certificate();
        if (certKey != null) {
            logger.info("::KISC CER={}, IssuerDN={}, IssuerX500Principal ={}", certKey.getSubjectDN(), certKey.getIssuerDN(), certKey.getIssuerX500Principal());
            //signVerificationInfo.setSignatureValid(signature.checkSignatureValue(certKey));
            signVerificationInfo.setValid(true);
            signVerificationInfo.setPrincipal(certKey.getSubjectDN().getName());
            signVerificationInfo.setCertificate(SerializationUtils.serialize(certKey));

            if (new Date().after(certKey.getNotAfter())) { //Проверка даты по на валидность
                signVerificationInfo.setExpired(true);
            }

            try {
                if (!CSP_Tumar.checkCert(certKey, oscpUrl))
                    signVerificationInfo.setRevoked(true);
            } catch (Exception exp) {
                signVerificationInfo.setRevoked(true);
                signVerificationInfo.setSignatureError(exp.getMessage());
            }
        } else {
            PublicKey pk = ki.getPublicKey();
            if (pk != null) {
                signVerificationInfo.setValid(signature.checkSignatureValue(pk));
            } else {
                logger.info("::KISC_ERROR CER={}, IssuerDN={}, IssuerX500Principal ={} public KEY IS NULL", certKey.getSubjectDN(), certKey.getIssuerDN(), certKey.getIssuerX500Principal());
                signVerificationInfo.setValid(false);
            }
        }

        String[] biniin = getBinIinFromCertificate(xmlString);
        if (biniin.length == 1) {
            signVerificationInfo.setIin(biniin[0]);
        } else {
            signVerificationInfo.setBin(biniin[0]);
            signVerificationInfo.setBin(biniin[1]);
        }
        return signVerificationInfo;
    }


    private static XMLSignature extractSignature(String xmlString) throws
            IOException, XMLSecurityException, ParserConfigurationException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
        Element rootEl = (Element) doc.getFirstChild();
        NodeList list = rootEl.getElementsByTagName("Signature");
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

    private static void setupProvider() {
        /*if (Security.getProvider(GammaTechProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new GammaTechProvider());
        }*/
        if (Security.getProvider(GammaTechProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new GammaTechProvider());
        }
        Security.addProvider(new GammaTechProvider());
        Provider provider = new KalkanProvider();
        Security.addProvider(provider);
        KncaXS.loadXMLSecurity();;
    }
    public static String[] getBinIinFromCertificate(String xmlString) throws Exception {
        // Если уже один раз был объявлен данный метод, то его повторно не нужно объявлять
        JCPXMLDSigInit.init();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document doc = documentBuilder.parse(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));

        Element nscontext = XMLUtils.createDSctx(doc, "ds", "http://www.w3.org/2000/09/xmldsig#");
        Element sigElement = (Element) XPathAPI.selectSingleNode(doc, "//ds:Signature[1]", nscontext);
        XMLSignature signature = new XMLSignature(sigElement, "");
        KeyInfo ki = signature.getKeyInfo();
        X509Certificate certKey = ki.getX509Certificate();
        if (certKey != null) {
            logger.info(":: GET_BIN_CERT cert={}", certKey.getSubjectDN());
            return CSP_Tumar.getBinIinFromCertificate(certKey);
        } else {
            throw new Exception("Certificate is null!!!");
        }
    }


    /**
     *
     */
    public static Document parseXml(String xml)
            throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        InputSource source = null;
        source = new InputSource(new StringReader(xml));
        return builder.parse(source);
    }

    /**
     * @param reader
     * @return     *
     */
    public static String readBigString(BufferedReader reader)
            throws IOException {
        StringBuffer buf = new StringBuffer();
        for (String curr = reader.readLine(); curr != null; curr = reader.readLine())
            buf.append((new StringBuilder(String.valueOf(curr))).append("\n").toString());

        return buf.toString();
    }

    public static String stripXml(String xml) {
        String result = xml.trim();
        return result;
    }

    /**
     * @param fileName
     * @param decode
     * @return
     * @throws Exception     *
     */
    public static Document parse(String fileName, boolean decode)
            throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Path path = Paths.get(fileName);
        if (decode) {
            String input = stripXml(readBigString(new BufferedReader(new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8))));

            input = input
                    .replaceAll("&amp;", "&")
                    .replaceAll("&quot;", "\"")
                    .replaceAll("&quote;", "\"")
                    .replaceAll("&gt;", ">")
                    .replaceAll("&lt;", "<");

            logger.info("Input: " + input);
            return builder.parse(new InputSource(new StringReader(input)));
        }
        return builder.parse(new InputSource(Files.newInputStream(path)));
    }

}
