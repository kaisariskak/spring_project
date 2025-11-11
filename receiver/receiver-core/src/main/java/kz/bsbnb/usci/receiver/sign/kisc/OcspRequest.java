package kz.bsbnb.usci.receiver.sign.kisc;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.gamma.asn1.*;
import kz.gamma.asn1.ocsp.*;
import kz.gamma.asn1.x509.AlgorithmIdentifier;
import kz.gamma.asn1.x509.GeneralName;
import kz.gamma.asn1.x509.X509Extension;
import kz.gamma.asn1.x509.X509Extensions;
import kz.gamma.functions.InstanceFunctions;
import kz.gamma.functions.OCSPFunctions;
import kz.gamma.functions.SignatureFunctions;
import kz.gamma.x509.extension.AuthorityKeyIdentifierStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.ldap.ExtendedResponse;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Hashtable;
import java.util.Vector;

public class OcspRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OcspRequest.class);

    private static final OCSPFunctions ocspFunctions = new OCSPFunctions();
    private static final InstanceFunctions instanceFunctions = new InstanceFunctions();
    private static final SignatureFunctions signatureFunctions = new SignatureFunctions();
    /**
     * Метод формирующий идентификатор ЦС
     */
    public static byte[] getAuthorityKeyId(X509Certificate cert)
            throws IOException {
        byte[] extValue = cert.getExtensionValue("2.5.29.35");
        AuthorityKeyIdentifierStructure keyId = new AuthorityKeyIdentifierStructure(extValue);
        return keyId.getKeyIdentifier();
    }

    /**
     * Метод формирующий CertID из сертификата
     */
    public static CertID buildCertId(X509Certificate cert)
            throws IOException {
        AlgorithmIdentifier algId = new AlgorithmIdentifier(new DERObjectIdentifier("1.3.6.1.4.1.6801.1.1.1"), DERNull.INSTANCE);
        String issuerName = cert.getIssuerX500Principal().getName();
        byte[] issuerNameData = issuerName.getBytes(StandardCharsets.US_ASCII);
        ASN1OctetString issuerNameHash = new DEROctetString(issuerNameData);
        byte[] issuerKeyData = getAuthorityKeyId(cert);
        ASN1OctetString issuerKeyHash = new DEROctetString(issuerKeyData);
        DERInteger serialNumber = new DERInteger(cert.getSerialNumber());
        return new CertID(algId, issuerNameHash, issuerKeyHash, serialNumber);
    }

    /**
     * Метод формирующий расширение для формирования OCSP
     */
    public static X509Extensions createNonceExtensions() {
        BigInteger nonce = BigInteger.valueOf(System.currentTimeMillis());
        Vector<DERObjectIdentifier> oids = new Vector<>();
        Vector<X509Extension> values = new Vector<>();
        oids.add(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
        values.add(new X509Extension(false, new DEROctetString(nonce.toByteArray())));
        return new X509Extensions(oids, values);
    }

    /**
     * Метод формирующий OCSP запрос
     */
    public static OCSPRequest generateRequest(X509Certificate cert, String requestor)
            throws Exception {
        CertID certId;
        certId = buildCertId(cert);
        X509Extensions altNameExtensions = null;
        Request req = new Request(certId, altNameExtensions);
        ASN1Sequence seq = new DERSequence(new ASN1Encodable[]{req});
        GeneralName requestorName = new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String(requestor.getBytes("ASCII")));
        X509Extensions nonceExtensions = createNonceExtensions();
        TBSRequest tbs = new TBSRequest(requestorName, seq, nonceExtensions);
        return new OCSPRequest(tbs, null);
    }

    /**
     * Метод отсылающий OCSP запрос на сервер
     */
    public static byte[] sendRequest(byte[] req, String url, String OIDService) {
        byte[] resp = null;
        LdapContext ctx = null;
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "SIMPLE");
            env.put(Context.PROVIDER_URL, url);
            env.put(Context.SECURITY_PRINCIPAL, "");
            env.put(Context.SECURITY_CREDENTIALS, "");
            ctx = new InitialLdapContext(env, null);

            RegisterRequest request1 = new RegisterRequest(req, OIDService);
            //ExtendedRequest extendedRequest = new StartTlsRequest();
            ExtendedResponse response = ctx.extendedOperation(request1);
            resp = response.getEncodedValue();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resp;
    }


    //Получение статуса проверяемого сертификата
    public static int getOCSPStatus2(byte[] req) {
        int status = -1;
        try {
            ASN1InputStream respStream = new ASN1InputStream(req);
            DERObject respObject = respStream.readObject();
            ASN1Sequence respSeq = (ASN1Sequence) respObject;
            OCSPResponse resp = new OCSPResponse(respSeq);
            resp.getResponseBytes().getResponseType().getId();

            ASN1InputStream asn1InputStream = new ASN1InputStream(resp.getResponseBytes().getResponse().getOctets());
            BasicOCSPResponse basicOCSPResponse = BasicOCSPResponse.getInstance(asn1InputStream.readObject());
            SingleResponse singleResponse = SingleResponse.getInstance(basicOCSPResponse.getTbsResponseData().getResponses().getObjectAt(0));
            CertStatus certStatus = singleResponse.getCertStatus();
            status = certStatus.getTagNo();
            if (status == 1) {
                RevokedInfo revokedInfo = RevokedInfo.getInstance(certStatus.getStatus());
                LOGGER.error(":: CERT REVOKE RESON={}", revokedInfo.getRevocationReason().getValue().intValue());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    /*
     * url - "http://betaca.kisc.kz:62255";
     *
     */
    public static int getOCSPStatus(X509Certificate cer, String url) throws Exception {
        try {
            // Формируем OCSP запрос
            //check(cer);
            OCSPRequest req = generateRequest(cer, "EsspRequestor");
            // Отправляем запрос на сервер и получаем ответ
            byte[] resp = sendRequest(url, req.getDEREncoded());
            // Получаем статус OCSP ответа
            return getOCSPStatus2(resp);
        } catch (Exception e) {
            LOGGER.error(":: getOCSPStatus cer={}", cer.getSubjectX500Principal(), e);
            throw e;
        }
    }
    public static void check(X509Certificate cer) throws UsciException {
        try {
            byte[] cert_der = cer.getEncoded();
            OCSPRequest request = ocspFunctions.createRequest(cert_der, InstanceFunctions.BYTES_DER, "web-portal_NBRK", null);
            byte[] data = request.getEncoded();
            byte[] response = sendRequest(data);
            int responseStatus = ocspFunctions.getOCSPStatus(response);
            if (responseStatus != OCSPResponseStatus.SUCCESSFUL) {
                throw new UsciException("OCSP_REQUEST_FAILED, responseStatus");
            }
            int ocspStatus = getOCSPStatus(response);
            if (ocspStatus != 0) {
                throw new UsciException("OCSP_STATUS_CHECK_FAILED, ocspStatus");
            }
        } catch (Exception e) {
            throw new UsciException("OCSP_REQUEST_FAILED, e.getMessage()");
        }
    }
    private static byte[] sendRequest(byte[] request) throws Exception {
        DataOutputStream printout = null;
        DataInputStream dataInputStream = null;
        try {
            URLConnection conn = new URL("http://91.195.226.34:62255").openConnection();
            conn.setRequestProperty("content-type", "application/pkixcmp");
            conn.setDoOutput(true);
            printout = new DataOutputStream(conn.getOutputStream());
            printout.write(request);
            printout.flush();
            int responseSize = conn.getContentLength();
            dataInputStream = new DataInputStream(conn.getInputStream());
            byte[] response = new byte[responseSize];
            int totalRead = 0;
            while (totalRead < responseSize) {
                int bytesRead = dataInputStream.read(response, totalRead, responseSize - totalRead);
                if (bytesRead < 0) {
                    break;
                }
                totalRead += bytesRead;
            }
            return response;
        } finally {
            try {
                if (printout != null) {
                    printout.close();
                }
            } catch (IOException e) {
                throw e;
            }
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }
    private static Integer getOCSPStatus(byte[] response) throws Exception {
        ASN1InputStream respStream = new ASN1InputStream(response);
        DERObject respObject = respStream.readObject();
        ASN1Sequence respSeq = (ASN1Sequence) respObject;
        OCSPResponse resp = new OCSPResponse(respSeq);
        ASN1OctetString octetString = resp.getResponseBytes().getResponse();
        ASN1InputStream basicOcspResponseStream = new ASN1InputStream(octetString.getOctets());
        DERObject derObject = basicOcspResponseStream.readObject();
        BasicOCSPResponse basicOCSPResponse = BasicOCSPResponse.getInstance(derObject);
        X509Certificate x509cert = instanceFunctions.getX509CertificateInstance(
                basicOCSPResponse.getCerts().getObjectAt(0).getDERObject().getDEREncoded());
        if (!signatureFunctions.verifySign(basicOCSPResponse.getTbsResponseData().getEncoded(),
                basicOCSPResponse.getSignature().getBytes(), x509cert.getPublicKey())) {
            throw new UsciException("OCSP_REQUEST_FAILED, unverified");
        }
        DERSequence responses = (DERSequence) basicOCSPResponse.getTbsResponseData().getResponses();
        SingleResponse singleResponse = SingleResponse.getInstance(responses.getObjectAt(0));
        CertStatus certStatus = singleResponse.getCertStatus();
        return certStatus.getTagNo();
    }
    /*---------------------------------------------------*/
    public static byte[] sendLdapRequest(byte[] req, String url) {
        byte[] resp = null;
        LdapContext ctx = null;
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.SECURITY_AUTHENTICATION, "SIMPLE");
            env.put(Context.PROVIDER_URL, url);
            env.put(Context.SECURITY_PRINCIPAL, "");
            env.put(Context.SECURITY_CREDENTIALS, "");
            ctx = new InitialLdapContext(env, null);

            RegisterRequest request1 = new RegisterRequest(req, "1.3.6.1.4.1.6801.11.1.1");
            ExtendedResponse response = ctx.extendedOperation(request1);
            resp = response.getEncodedValue();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resp;
    }

    /**
     * @param serviceURL URL службы, например, http://192.168.10.30:62295
     * @param request    Запрос в ASN
     * @return Ответ службы
     */
    public static byte[] sendRequest(String serviceURL, byte[] request) throws Exception {
        byte[] response = null;
        DataOutputStream printout = null;
        DataInputStream dataInputStream = null;
        try {
            URLConnection conn = new URL(serviceURL).openConnection();
            conn.setRequestProperty("content-type", "application/pkixcmp");
            conn.setDoOutput(true);
            printout = new DataOutputStream(conn.getOutputStream());
            printout.write(request);
            printout.flush();
            int responseSize = conn.getContentLength();
            dataInputStream = new DataInputStream(conn.getInputStream());
            response = new byte[responseSize];
            int totalRead = 0;
            while (totalRead < responseSize) {
                int bytesRead = dataInputStream.read(response, totalRead, responseSize - totalRead);
                if (bytesRead < 0) {
                    break;
                }
                totalRead += bytesRead;
            }
        } catch (Exception e) {
            throw new UsciException(e.getMessage());
        } finally {
            if (printout != null) {
                printout.close();
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
        }
        return response;
    }


}
