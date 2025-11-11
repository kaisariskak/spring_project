/**
 * RepatriationStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Статус оралмана
 */
public class RepatriationStatus  implements java.io.Serializable {
    /* Признак, является ли физическое лицо оралманом (да/нет) */
    private boolean repatriate;

    /* Дата начала действия статуса */
    private java.util.Date beginDate;

    /* Дата окончания действия статуса */
    private java.util.Date endDate;

    public RepatriationStatus() {
    }

    public RepatriationStatus(
           boolean repatriate,
           java.util.Date beginDate,
           java.util.Date endDate) {
           this.repatriate = repatriate;
           this.beginDate = beginDate;
           this.endDate = endDate;
    }


    /**
     * Gets the repatriate value for this RepatriationStatus.
     * 
     * @return repatriate   * Признак, является ли физическое лицо оралманом (да/нет)
     */
    public boolean isRepatriate() {
        return repatriate;
    }


    /**
     * Sets the repatriate value for this RepatriationStatus.
     * 
     * @param repatriate   * Признак, является ли физическое лицо оралманом (да/нет)
     */
    public void setRepatriate(boolean repatriate) {
        this.repatriate = repatriate;
    }


    /**
     * Gets the beginDate value for this RepatriationStatus.
     * 
     * @return beginDate   * Дата начала действия статуса
     */
    public java.util.Date getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this RepatriationStatus.
     * 
     * @param beginDate   * Дата начала действия статуса
     */
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the endDate value for this RepatriationStatus.
     * 
     * @return endDate   * Дата окончания действия статуса
     */
    public java.util.Date getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this RepatriationStatus.
     * 
     * @param endDate   * Дата окончания действия статуса
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RepatriationStatus)) return false;
        RepatriationStatus other = (RepatriationStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.repatriate == other.isRepatriate() &&
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate())));
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
        _hashCode += (isRepatriate() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getBeginDate() != null) {
            _hashCode += getBeginDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RepatriationStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "RepatriationStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repatriate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repatriate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
