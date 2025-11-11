/**
 * PersonExcludeStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Признак исключения/условного исключения ИИН из Национального реестра
 */
public class PersonExcludeStatus  implements java.io.Serializable {
    /* Причина исключения */
    private gbdul.nat.interactive.persistence.dictionaries.ExcludeReason excludeReason;

    /* Дата начала действия статуса */
    private java.util.Date excludeReasonDate;

    /* Дата окончания действия статуса */
    private java.util.Date excludeDate;

    /* Источник информации о присвоении статуса */
    private gbdul.nat.interactive.persistence.dictionaries.Participant excludeParticipant;

    public PersonExcludeStatus() {
    }

    public PersonExcludeStatus(
           gbdul.nat.interactive.persistence.dictionaries.ExcludeReason excludeReason,
           java.util.Date excludeReasonDate,
           java.util.Date excludeDate,
           gbdul.nat.interactive.persistence.dictionaries.Participant excludeParticipant) {
           this.excludeReason = excludeReason;
           this.excludeReasonDate = excludeReasonDate;
           this.excludeDate = excludeDate;
           this.excludeParticipant = excludeParticipant;
    }


    /**
     * Gets the excludeReason value for this PersonExcludeStatus.
     * 
     * @return excludeReason   * Причина исключения
     */
    public gbdul.nat.interactive.persistence.dictionaries.ExcludeReason getExcludeReason() {
        return excludeReason;
    }


    /**
     * Sets the excludeReason value for this PersonExcludeStatus.
     * 
     * @param excludeReason   * Причина исключения
     */
    public void setExcludeReason(gbdul.nat.interactive.persistence.dictionaries.ExcludeReason excludeReason) {
        this.excludeReason = excludeReason;
    }


    /**
     * Gets the excludeReasonDate value for this PersonExcludeStatus.
     * 
     * @return excludeReasonDate   * Дата начала действия статуса
     */
    public java.util.Date getExcludeReasonDate() {
        return excludeReasonDate;
    }


    /**
     * Sets the excludeReasonDate value for this PersonExcludeStatus.
     * 
     * @param excludeReasonDate   * Дата начала действия статуса
     */
    public void setExcludeReasonDate(java.util.Date excludeReasonDate) {
        this.excludeReasonDate = excludeReasonDate;
    }


    /**
     * Gets the excludeDate value for this PersonExcludeStatus.
     * 
     * @return excludeDate   * Дата окончания действия статуса
     */
    public java.util.Date getExcludeDate() {
        return excludeDate;
    }


    /**
     * Sets the excludeDate value for this PersonExcludeStatus.
     * 
     * @param excludeDate   * Дата окончания действия статуса
     */
    public void setExcludeDate(java.util.Date excludeDate) {
        this.excludeDate = excludeDate;
    }


    /**
     * Gets the excludeParticipant value for this PersonExcludeStatus.
     * 
     * @return excludeParticipant   * Источник информации о присвоении статуса
     */
    public gbdul.nat.interactive.persistence.dictionaries.Participant getExcludeParticipant() {
        return excludeParticipant;
    }


    /**
     * Sets the excludeParticipant value for this PersonExcludeStatus.
     * 
     * @param excludeParticipant   * Источник информации о присвоении статуса
     */
    public void setExcludeParticipant(gbdul.nat.interactive.persistence.dictionaries.Participant excludeParticipant) {
        this.excludeParticipant = excludeParticipant;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PersonExcludeStatus)) return false;
        PersonExcludeStatus other = (PersonExcludeStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.excludeReason==null && other.getExcludeReason()==null) || 
             (this.excludeReason!=null &&
              this.excludeReason.equals(other.getExcludeReason()))) &&
            ((this.excludeReasonDate==null && other.getExcludeReasonDate()==null) || 
             (this.excludeReasonDate!=null &&
              this.excludeReasonDate.equals(other.getExcludeReasonDate()))) &&
            ((this.excludeDate==null && other.getExcludeDate()==null) || 
             (this.excludeDate!=null &&
              this.excludeDate.equals(other.getExcludeDate()))) &&
            ((this.excludeParticipant==null && other.getExcludeParticipant()==null) || 
             (this.excludeParticipant!=null &&
              this.excludeParticipant.equals(other.getExcludeParticipant())));
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
        if (getExcludeReason() != null) {
            _hashCode += getExcludeReason().hashCode();
        }
        if (getExcludeReasonDate() != null) {
            _hashCode += getExcludeReasonDate().hashCode();
        }
        if (getExcludeDate() != null) {
            _hashCode += getExcludeDate().hashCode();
        }
        if (getExcludeParticipant() != null) {
            _hashCode += getExcludeParticipant().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PersonExcludeStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "PersonExcludeStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludeReason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excludeReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "ExcludeReason"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludeReasonDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excludeReasonDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excludeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludeParticipant");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excludeParticipant"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Participant"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
