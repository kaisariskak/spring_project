package kz.bsbnb.usci.receiver.sign.kisc;

import javax.naming.ldap.ExtendedResponse;

/**
 * @author Indira.Tashim@bsbnb.kz
 */
public class RegisterResponse implements ExtendedResponse {

    private final byte[] data;

    public RegisterResponse(String id, byte[] berValue, int offset, int length) {
        data = subArray(berValue, offset, length);
    }

    public String getID() {
        return "1.3.6.1.4.1.6801.11.1.2";
    }

    public byte[] getEncodedValue() {
        return data;
    }

    private byte[] subArray(byte[] data, int offset, int length) {
        if (data == null) return new byte[0];
        if (offset < 0) return new byte[]{};
        if (offset > data.length) return new byte[]{};
        if (offset + length > data.length) length = data.length - offset;
        if (length <= 0) return new byte[]{};
        byte[] retVal = new byte[length];
        System.arraycopy(data, offset, retVal, 0, length);
        return retVal;
    }
}

