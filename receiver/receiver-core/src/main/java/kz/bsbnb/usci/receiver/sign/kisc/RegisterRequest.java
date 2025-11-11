package kz.bsbnb.usci.receiver.sign.kisc;

import javax.naming.NamingException;
import javax.naming.ldap.ExtendedRequest;
import javax.naming.ldap.ExtendedResponse;

/**
 * @author Indira.Tashim@bsbnb.kz
 */
public class RegisterRequest implements ExtendedRequest {

    private final byte[] cert;
    private final String service;

    /**
     * @param certificate - Сертификат
     * @param srv         - Сервис, к которому будут идти зпросы
     */
    public RegisterRequest(byte[] certificate, String srv) {
        this.cert = certificate;
        this.service = srv;
    }

    public String getID() {
        return service;
    }

    public byte[] getEncodedValue() {
        return cert;
    }

    /**
     * @param id
     * @param berValue
     * @param offset
     * @param length
     * @return
     * @throws NamingException
     */
    public ExtendedResponse createExtendedResponse(String id, byte[] berValue, int offset, int length) throws NamingException {
        return new RegisterResponse(id, berValue, offset, length);
    }
}
