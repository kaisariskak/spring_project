/**
 * SyncSendMessageRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.bee.bip.SyncChannel.v10.Types.Request;

public class SyncSendMessageRequest  implements java.io.Serializable {
    private gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfo requestInfo;

    private RequestData requestData;

    public SyncSendMessageRequest() {
    }

    public SyncSendMessageRequest(
           gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfo requestInfo,
           RequestData requestData) {
           this.requestInfo = requestInfo;
           this.requestData = requestData;
    }


    /**
     * Gets the requestInfo value for this SyncSendMessageRequest.
     * 
     * @return requestInfo
     */
    public gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfo getRequestInfo() {
        return requestInfo;
    }


    /**
     * Sets the requestInfo value for this SyncSendMessageRequest.
     * 
     * @param requestInfo
     */
    public void setRequestInfo(gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfo requestInfo) {
        this.requestInfo = requestInfo;
    }


    /**
     * Gets the requestData value for this SyncSendMessageRequest.
     * 
     * @return requestData
     */
    public RequestData getRequestData() {
        return requestData;
    }


    /**
     * Sets the requestData value for this SyncSendMessageRequest.
     * 
     * @param requestData
     */
    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SyncSendMessageRequest)) return false;
        SyncSendMessageRequest other = (SyncSendMessageRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestInfo==null && other.getRequestInfo()==null) || 
             (this.requestInfo!=null &&
              this.requestInfo.equals(other.getRequestInfo()))) &&
            ((this.requestData==null && other.getRequestData()==null) || 
             (this.requestData!=null &&
              this.requestData.equals(other.getRequestData())));
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
        if (getRequestInfo() != null) {
            _hashCode += getRequestInfo().hashCode();
        }
        if (getRequestData() != null) {
            _hashCode += getRequestData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SyncSendMessageRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Request", "SyncSendMessageRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types", "SyncMessageInfo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Request", "RequestData"));
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
