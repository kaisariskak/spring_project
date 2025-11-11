/**
 * SyncMessageInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.bee.bip.SyncChannel.v10.Types;

public class SyncMessageInfoResponse  implements java.io.Serializable {
    private String messageId;

    private String correlationId;

    private java.util.Calendar responseDate;

    private gbdul.kz.bee.bip.common.v10.Types.StatusInfo status;

    private String sessionId;

    public SyncMessageInfoResponse() {
    }

    public SyncMessageInfoResponse(
           String messageId,
           String correlationId,
           java.util.Calendar responseDate,
           gbdul.kz.bee.bip.common.v10.Types.StatusInfo status,
           String sessionId) {
           this.messageId = messageId;
           this.correlationId = correlationId;
           this.responseDate = responseDate;
           this.status = status;
           this.sessionId = sessionId;
    }


    /**
     * Gets the messageId value for this SyncMessageInfoResponse.
     * 
     * @return messageId
     */
    public String getMessageId() {
        return messageId;
    }


    /**
     * Sets the messageId value for this SyncMessageInfoResponse.
     * 
     * @param messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


    /**
     * Gets the correlationId value for this SyncMessageInfoResponse.
     * 
     * @return correlationId
     */
    public String getCorrelationId() {
        return correlationId;
    }


    /**
     * Sets the correlationId value for this SyncMessageInfoResponse.
     * 
     * @param correlationId
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }


    /**
     * Gets the responseDate value for this SyncMessageInfoResponse.
     * 
     * @return responseDate
     */
    public java.util.Calendar getResponseDate() {
        return responseDate;
    }


    /**
     * Sets the responseDate value for this SyncMessageInfoResponse.
     * 
     * @param responseDate
     */
    public void setResponseDate(java.util.Calendar responseDate) {
        this.responseDate = responseDate;
    }


    /**
     * Gets the status value for this SyncMessageInfoResponse.
     * 
     * @return status
     */
    public gbdul.kz.bee.bip.common.v10.Types.StatusInfo getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SyncMessageInfoResponse.
     * 
     * @param status
     */
    public void setStatus(gbdul.kz.bee.bip.common.v10.Types.StatusInfo status) {
        this.status = status;
    }


    /**
     * Gets the sessionId value for this SyncMessageInfoResponse.
     * 
     * @return sessionId
     */
    public String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this SyncMessageInfoResponse.
     * 
     * @param sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SyncMessageInfoResponse)) return false;
        SyncMessageInfoResponse other = (SyncMessageInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageId==null && other.getMessageId()==null) || 
             (this.messageId!=null &&
              this.messageId.equals(other.getMessageId()))) &&
            ((this.correlationId==null && other.getCorrelationId()==null) || 
             (this.correlationId!=null &&
              this.correlationId.equals(other.getCorrelationId()))) &&
            ((this.responseDate==null && other.getResponseDate()==null) || 
             (this.responseDate!=null &&
              this.responseDate.equals(other.getResponseDate()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.sessionId==null && other.getSessionId()==null) || 
             (this.sessionId!=null &&
              this.sessionId.equals(other.getSessionId())));
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
        if (getMessageId() != null) {
            _hashCode += getMessageId().hashCode();
        }
        if (getCorrelationId() != null) {
            _hashCode += getCorrelationId().hashCode();
        }
        if (getResponseDate() != null) {
            _hashCode += getResponseDate().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getSessionId() != null) {
            _hashCode += getSessionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SyncMessageInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types", "SyncMessageInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "correlationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "StatusInfo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sessionId"));
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
