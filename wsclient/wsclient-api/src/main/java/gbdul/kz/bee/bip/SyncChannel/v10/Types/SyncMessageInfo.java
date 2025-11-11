/**
 * SyncMessageInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.bee.bip.SyncChannel.v10.Types;

public class SyncMessageInfo  implements java.io.Serializable {
    private String messageId;

    private String correlationId;

    private String serviceId;

    private java.util.Calendar messageDate;

    private String userId;

    private String routeId;

    private gbdul.kz.bee.bip.common.v10.Types.SenderInfo sender;

    private gbdul.kz.bee.bip.common.v10.Types.Property[] properties;

    private String sessionId;

    public SyncMessageInfo() {
    }

    public SyncMessageInfo(
           String messageId,
           String correlationId,
           String serviceId,
           java.util.Calendar messageDate,
           String userId,
           String routeId,
           gbdul.kz.bee.bip.common.v10.Types.SenderInfo sender,
           gbdul.kz.bee.bip.common.v10.Types.Property[] properties,
           String sessionId) {
           this.messageId = messageId;
           this.correlationId = correlationId;
           this.serviceId = serviceId;
           this.messageDate = messageDate;
           this.userId = userId;
           this.routeId = routeId;
           this.sender = sender;
           this.properties = properties;
           this.sessionId = sessionId;
    }


    /**
     * Gets the messageId value for this SyncMessageInfo.
     * 
     * @return messageId
     */
    public String getMessageId() {
        return messageId;
    }


    /**
     * Sets the messageId value for this SyncMessageInfo.
     * 
     * @param messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


    /**
     * Gets the correlationId value for this SyncMessageInfo.
     * 
     * @return correlationId
     */
    public String getCorrelationId() {
        return correlationId;
    }


    /**
     * Sets the correlationId value for this SyncMessageInfo.
     * 
     * @param correlationId
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }


    /**
     * Gets the serviceId value for this SyncMessageInfo.
     * 
     * @return serviceId
     */
    public String getServiceId() {
        return serviceId;
    }


    /**
     * Sets the serviceId value for this SyncMessageInfo.
     * 
     * @param serviceId
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }


    /**
     * Gets the messageDate value for this SyncMessageInfo.
     * 
     * @return messageDate
     */
    public java.util.Calendar getMessageDate() {
        return messageDate;
    }


    /**
     * Sets the messageDate value for this SyncMessageInfo.
     * 
     * @param messageDate
     */
    public void setMessageDate(java.util.Calendar messageDate) {
        this.messageDate = messageDate;
    }


    /**
     * Gets the userId value for this SyncMessageInfo.
     * 
     * @return userId
     */
    public String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this SyncMessageInfo.
     * 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }


    /**
     * Gets the routeId value for this SyncMessageInfo.
     * 
     * @return routeId
     */
    public String getRouteId() {
        return routeId;
    }


    /**
     * Sets the routeId value for this SyncMessageInfo.
     * 
     * @param routeId
     */
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    /**
     * Gets the sender value for this SyncMessageInfo.
     * 
     * @return sender
     */
    public gbdul.kz.bee.bip.common.v10.Types.SenderInfo getSender() {
        return sender;
    }


    /**
     * Sets the sender value for this SyncMessageInfo.
     * 
     * @param sender
     */
    public void setSender(gbdul.kz.bee.bip.common.v10.Types.SenderInfo sender) {
        this.sender = sender;
    }


    /**
     * Gets the properties value for this SyncMessageInfo.
     * 
     * @return properties
     */
    public gbdul.kz.bee.bip.common.v10.Types.Property[] getProperties() {
        return properties;
    }


    /**
     * Sets the properties value for this SyncMessageInfo.
     * 
     * @param properties
     */
    public void setProperties(gbdul.kz.bee.bip.common.v10.Types.Property[] properties) {
        this.properties = properties;
    }

    public gbdul.kz.bee.bip.common.v10.Types.Property getProperties(int i) {
        return this.properties[i];
    }

    public void setProperties(int i, gbdul.kz.bee.bip.common.v10.Types.Property _value) {
        this.properties[i] = _value;
    }


    /**
     * Gets the sessionId value for this SyncMessageInfo.
     * 
     * @return sessionId
     */
    public String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this SyncMessageInfo.
     * 
     * @param sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SyncMessageInfo)) return false;
        SyncMessageInfo other = (SyncMessageInfo) obj;
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
            ((this.serviceId==null && other.getServiceId()==null) || 
             (this.serviceId!=null &&
              this.serviceId.equals(other.getServiceId()))) &&
            ((this.messageDate==null && other.getMessageDate()==null) || 
             (this.messageDate!=null &&
              this.messageDate.equals(other.getMessageDate()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.routeId==null && other.getRouteId()==null) || 
             (this.routeId!=null &&
              this.routeId.equals(other.getRouteId()))) &&
            ((this.sender==null && other.getSender()==null) || 
             (this.sender!=null &&
              this.sender.equals(other.getSender()))) &&
            ((this.properties==null && other.getProperties()==null) || 
             (this.properties!=null &&
              java.util.Arrays.equals(this.properties, other.getProperties()))) &&
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
        if (getServiceId() != null) {
            _hashCode += getServiceId().hashCode();
        }
        if (getMessageDate() != null) {
            _hashCode += getMessageDate().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getRouteId() != null) {
            _hashCode += getRouteId().hashCode();
        }
        if (getSender() != null) {
            _hashCode += getSender().hashCode();
        }
        if (getProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProperties());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSessionId() != null) {
            _hashCode += getSessionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SyncMessageInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types", "SyncMessageInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("serviceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "routeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "SenderInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("properties");
        elemField.setXmlName(new javax.xml.namespace.QName("", "properties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "Property"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
