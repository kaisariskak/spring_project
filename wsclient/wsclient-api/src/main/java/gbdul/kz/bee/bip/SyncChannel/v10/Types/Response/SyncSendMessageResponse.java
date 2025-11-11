/**
 * SyncSendMessageResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.bee.bip.SyncChannel.v10.Types.Response;

public class SyncSendMessageResponse  implements java.io.Serializable {
    private gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfoResponse responseInfo;

    private ResponseData responseData;

    private String requestId;

    public SyncSendMessageResponse() {
    }

    public SyncSendMessageResponse(
           gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfoResponse responseInfo,
           ResponseData responseData,
           String requestId) {
           this.responseInfo = responseInfo;
           this.responseData = responseData;
           this.requestId = requestId;
    }


    /**
     * Gets the responseInfo value for this SyncSendMessageResponse.
     * 
     * @return responseInfo
     */
    public gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfoResponse getResponseInfo() {
        return responseInfo;
    }


    /**
     * Sets the responseInfo value for this SyncSendMessageResponse.
     * 
     * @param responseInfo
     */
    public void setResponseInfo(gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfoResponse responseInfo) {
        this.responseInfo = responseInfo;
    }


    /**
     * Gets the responseData value for this SyncSendMessageResponse.
     * 
     * @return responseData
     */
    public ResponseData getResponseData() {
        return responseData;
    }


    /**
     * Sets the responseData value for this SyncSendMessageResponse.
     * 
     * @param responseData
     */
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }


    /**
     * Gets the requestId value for this SyncSendMessageResponse.
     * 
     * @return requestId
     */
    public String getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this SyncSendMessageResponse.
     * 
     * @param requestId
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SyncSendMessageResponse)) return false;
        SyncSendMessageResponse other = (SyncSendMessageResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.responseInfo==null && other.getResponseInfo()==null) || 
             (this.responseInfo!=null &&
              this.responseInfo.equals(other.getResponseInfo()))) &&
            ((this.responseData==null && other.getResponseData()==null) || 
             (this.responseData!=null &&
              this.responseData.equals(other.getResponseData()))) &&
            ((this.requestId==null && other.getRequestId()==null) || 
             (this.requestId!=null &&
              this.requestId.equals(other.getRequestId())));
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
        if (getResponseInfo() != null) {
            _hashCode += getResponseInfo().hashCode();
        }
        if (getResponseData() != null) {
            _hashCode += getResponseData().hashCode();
        }
        if (getRequestId() != null) {
            _hashCode += getRequestId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SyncSendMessageResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Response", "SyncSendMessageResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responseInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types", "SyncMessageInfoResponse"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responseData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Response", "ResponseData"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
