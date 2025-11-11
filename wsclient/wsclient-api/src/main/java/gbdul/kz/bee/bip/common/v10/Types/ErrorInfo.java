/**
 * ErrorInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.bee.bip.common.v10.Types;

public class ErrorInfo  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    private String errorCode;

    private String errorMessage;

    private String errorData;

    private java.util.Calendar errorDate;

    private ErrorInfo subError;

    private String sessionId;

    public ErrorInfo() {
    }

    public ErrorInfo(
           String errorCode,
           String errorMessage,
           String errorData,
           java.util.Calendar errorDate,
           ErrorInfo subError,
           String sessionId) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorData = errorData;
        this.errorDate = errorDate;
        this.subError = subError;
        this.sessionId = sessionId;
    }


    /**
     * Gets the errorCode value for this ErrorInfo.
     * 
     * @return errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this ErrorInfo.
     * 
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the errorMessage value for this ErrorInfo.
     * 
     * @return errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this ErrorInfo.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * Gets the errorData value for this ErrorInfo.
     * 
     * @return errorData
     */
    public String getErrorData() {
        return errorData;
    }


    /**
     * Sets the errorData value for this ErrorInfo.
     * 
     * @param errorData
     */
    public void setErrorData(String errorData) {
        this.errorData = errorData;
    }


    /**
     * Gets the errorDate value for this ErrorInfo.
     * 
     * @return errorDate
     */
    public java.util.Calendar getErrorDate() {
        return errorDate;
    }


    /**
     * Sets the errorDate value for this ErrorInfo.
     * 
     * @param errorDate
     */
    public void setErrorDate(java.util.Calendar errorDate) {
        this.errorDate = errorDate;
    }


    /**
     * Gets the subError value for this ErrorInfo.
     * 
     * @return subError
     */
    public ErrorInfo getSubError() {
        return subError;
    }


    /**
     * Sets the subError value for this ErrorInfo.
     * 
     * @param subError
     */
    public void setSubError(ErrorInfo subError) {
        this.subError = subError;
    }


    /**
     * Gets the sessionId value for this ErrorInfo.
     * 
     * @return sessionId
     */
    public String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this ErrorInfo.
     * 
     * @param sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ErrorInfo)) return false;
        ErrorInfo other = (ErrorInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.errorMessage==null && other.getErrorMessage()==null) || 
             (this.errorMessage!=null &&
              this.errorMessage.equals(other.getErrorMessage()))) &&
            ((this.errorData==null && other.getErrorData()==null) || 
             (this.errorData!=null &&
              this.errorData.equals(other.getErrorData()))) &&
            ((this.errorDate==null && other.getErrorDate()==null) || 
             (this.errorDate!=null &&
              this.errorDate.equals(other.getErrorDate()))) &&
            ((this.subError==null && other.getSubError()==null) || 
             (this.subError!=null &&
              this.subError.equals(other.getSubError()))) &&
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
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getErrorMessage() != null) {
            _hashCode += getErrorMessage().hashCode();
        }
        if (getErrorData() != null) {
            _hashCode += getErrorData().hashCode();
        }
        if (getErrorDate() != null) {
            _hashCode += getErrorDate().hashCode();
        }
        if (getSubError() != null) {
            _hashCode += getSubError().hashCode();
        }
        if (getSessionId() != null) {
            _hashCode += getSessionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErrorInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "ErrorInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subError");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "ErrorInfo"));
        elemField.setMinOccurs(0);
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


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
