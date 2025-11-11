package kz.bsbnb.usci.core.service;

import kz.bsbnb.usci.model.exception.UsciException;
import kz.gov.pki.kalkan.asn1.ASN1InputStream;
import kz.gov.pki.kalkan.asn1.DERObject;
import kz.gov.pki.kalkan.asn1.DEROctetString;
import kz.gov.pki.kalkan.asn1.ocsp.OCSPObjectIdentifiers;
import kz.gov.pki.kalkan.asn1.x509.X509Extension;
import kz.gov.pki.kalkan.asn1.x509.X509Extensions;
import kz.gov.pki.kalkan.jce.provider.KalkanProvider;
import kz.gov.pki.kalkan.ocsp.*;
import kz.gov.pki.kalkan.util.encoders.Base64;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Hashtable;

class PkiOcspRequest {

    private final String serviceUrl;
    private static byte[] nonce;

    PkiOcspRequest(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public void check(X509Certificate cer) throws UsciException {
        try {
            InputStream cacertIs = getClass().getClassLoader().getResourceAsStream("nca_gost.cer");
            X509Certificate cacert = (X509Certificate) CertificateFactory.getInstance("X.509",
                    KalkanProvider.PROVIDER_NAME).generateCertificate(cacertIs);
            byte[] ocspReq = getOcspPackage(cer.getSerialNumber(), cacert, CertificateID.HASH_SHA1);
            OCSPResp ocspResp = sendRequest(ocspReq);
            if (ocspResp.getStatus() != 0) {
                throw new UsciException("OCSP_REQUEST_FAILED : " + ocspResp.getStatus());
            }
            Object ocspStatus = getOCSPStatus(ocspResp);
            if (ocspStatus != null) {
                if (ocspStatus instanceof RevokedStatus) {
                    throw new UsciException("OCSP_STATUS_CHECK_FAILED: REVOKED");
                }
                if (ocspStatus instanceof UnknownStatus) {
                    throw new UsciException("OCSP_STATUS_CHECK_FAILED: Response is UNKNOWN");
                }
            }
        } catch (Exception e) {
            throw new UsciException("OCSP_REQUEST_FAILED: " + e.getMessage());
        }
    }



    /**
     *
     * @param request Запрос в ASN
     *
     * @return Ответ службы
     *
     */
    private OCSPResp sendRequest(byte[] request) throws Exception {
        try {
            URL url;
            HttpURLConnection con;
            OutputStream os;
            String b64Req = new String(Base64.encode(request));
            String getUrl = serviceUrl + "/" + b64Req;
            // сервис понимает и POST и GET, можно выбрать что-то одно
            if (getUrl.length() <= 2) {
                url = new URL(getUrl);
                con = (HttpURLConnection) url.openConnection();
            } else {
                url = new URL(serviceUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/ocsp-request");
                os = con.getOutputStream();
                os.write(request);
                os.close();
            }
            InputStream in = con.getInputStream();
            OCSPResp response = new OCSPResp(in);
            in.close();
            con.disconnect();
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    private byte[] getOcspPackage(BigInteger serialNr, Certificate cacert, String hashAlg) throws Exception {
        OCSPReqGenerator gen = new OCSPReqGenerator();
        CertificateID certId = new CertificateID(hashAlg,
                (X509Certificate) cacert, serialNr,
                KalkanProvider.PROVIDER_NAME);
        gen.addRequest(certId);
        gen.setRequestExtensions(generateExtensions());
        OCSPReq req;
        req = gen.generate();
        return req.getEncoded();
    }

    private X509Extensions generateExtensions() {
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

    /**
     * Получение статуса проверяемого сертификата из ответа OCSP. Также
     * осуществляется проверка подписи OCSP ответа
     *
     * @param response Подписанный ответ в DER кодировке (подписанная квитанция)
     * @return Статус OCSP ответа
     * @throws Exception
     */
    private Object getOCSPStatus(OCSPResp response) throws Exception {
        BasicOCSPResp brep = (BasicOCSPResp) response.getResponseObject();
        byte[] respNonceExt = brep.getExtensionValue(OCSPObjectIdentifiers.id_pkix_ocsp_nonce.getId());
        if (respNonceExt != null) {
            ASN1InputStream asn1In = new ASN1InputStream(respNonceExt);
            DERObject derObj = asn1In.readObject();
            asn1In.close();
            byte[] extV = DEROctetString.getInstance(derObj).getOctets();
            asn1In = new ASN1InputStream(extV);
            derObj = asn1In.readObject();
            asn1In.close();
        }
        X509Certificate ocspcert = brep.getCerts(KalkanProvider.PROVIDER_NAME)[0];
        if (!brep.verify(ocspcert.getPublicKey(), KalkanProvider.PROVIDER_NAME)) {
            throw new UsciException("OCSP_REQUEST_FAILED, unverified");
        }
        SingleResp[] singleResps = brep.getResponses();
        SingleResp singleResp = singleResps[0];
        Object status = singleResp.getCertStatus();

        return status;
    }
}
