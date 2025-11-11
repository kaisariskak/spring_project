/**
 * RequestDataType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.tamur.gbdul.egp.gbdulinfobybin;


/**
 * Формат запроса
 */
public class RequestDataType  implements java.io.Serializable {
    /* БИН владельца ИС ГО, отправившей запрос */
    private String requestorBIN;

    /* БИН организации, для которой запрашивается справка */
    private String BIN;

    public RequestDataType() {
    }

    public RequestDataType(
           String requestorBIN,
           String BIN) {
           this.requestorBIN = requestorBIN;
           this.BIN = BIN;
    }


    /**
     * Gets the requestorBIN value for this RequestDataType.
     * 
     * @return requestorBIN   * БИН владельца ИС ГО, отправившей запрос
     */
    public String getRequestorBIN() {
        return requestorBIN;
    }


    /**
     * Sets the requestorBIN value for this RequestDataType.
     * 
     * @param requestorBIN   * БИН владельца ИС ГО, отправившей запрос
     */
    public void setRequestorBIN(String requestorBIN) {
        this.requestorBIN = requestorBIN;
    }


    /**
     * Gets the BIN value for this RequestDataType.
     * 
     * @return BIN   * БИН организации, для которой запрашивается справка
     */
    public String getBIN() {
        return BIN;
    }


    /**
     * Sets the BIN value for this RequestDataType.
     * 
     * @param BIN   * БИН организации, для которой запрашивается справка
     */
    public void setBIN(String BIN) {
        this.BIN = BIN;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RequestDataType)) return false;
        RequestDataType other = (RequestDataType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestorBIN==null && other.getRequestorBIN()==null) || 
             (this.requestorBIN!=null &&
              this.requestorBIN.equals(other.getRequestorBIN()))) &&
            ((this.BIN==null && other.getBIN()==null) || 
             (this.BIN!=null &&
              this.BIN.equals(other.getBIN())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getRequestorBIN() != null) {
            _hashCode += getRequestorBIN().hashCode();
        }
        if (getBIN() != null) {
            _hashCode += getBIN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestDataType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "RequestDataType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestorBIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestorBIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
