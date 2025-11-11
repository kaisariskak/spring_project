/**
 * DisappearStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Физическое лицо скрывается от дознания, следствия, суда и отбытия
 * наказания
 */
public class DisappearStatus  implements java.io.Serializable {
    /* Текущий статус - скрывается (Да/Нет) */
    private boolean disappear;

    /* Дата начала действия статуса */
    private java.util.Date disappearDate;

    /* Дата окончания действия статуса */
    private java.util.Date disappearEndDate;

    /* Номер дела */
    private String disappearNumber;

    /* Орган КПССУ присвоивший статус */
    private gbdul.nat.interactive.persistence.dictionaries.GpTerritorial gpTerritorial;

    public DisappearStatus() {
    }

    public DisappearStatus(
           boolean disappear,
           java.util.Date disappearDate,
           java.util.Date disappearEndDate,
           String disappearNumber,
           gbdul.nat.interactive.persistence.dictionaries.GpTerritorial gpTerritorial) {
           this.disappear = disappear;
           this.disappearDate = disappearDate;
           this.disappearEndDate = disappearEndDate;
           this.disappearNumber = disappearNumber;
           this.gpTerritorial = gpTerritorial;
    }


    /**
     * Gets the disappear value for this DisappearStatus.
     * 
     * @return disappear   * Текущий статус - скрывается (Да/Нет)
     */
    public boolean isDisappear() {
        return disappear;
    }


    /**
     * Sets the disappear value for this DisappearStatus.
     * 
     * @param disappear   * Текущий статус - скрывается (Да/Нет)
     */
    public void setDisappear(boolean disappear) {
        this.disappear = disappear;
    }


    /**
     * Gets the disappearDate value for this DisappearStatus.
     * 
     * @return disappearDate   * Дата начала действия статуса
     */
    public java.util.Date getDisappearDate() {
        return disappearDate;
    }


    /**
     * Sets the disappearDate value for this DisappearStatus.
     * 
     * @param disappearDate   * Дата начала действия статуса
     */
    public void setDisappearDate(java.util.Date disappearDate) {
        this.disappearDate = disappearDate;
    }


    /**
     * Gets the disappearEndDate value for this DisappearStatus.
     * 
     * @return disappearEndDate   * Дата окончания действия статуса
     */
    public java.util.Date getDisappearEndDate() {
        return disappearEndDate;
    }


    /**
     * Sets the disappearEndDate value for this DisappearStatus.
     * 
     * @param disappearEndDate   * Дата окончания действия статуса
     */
    public void setDisappearEndDate(java.util.Date disappearEndDate) {
        this.disappearEndDate = disappearEndDate;
    }


    /**
     * Gets the disappearNumber value for this DisappearStatus.
     * 
     * @return disappearNumber   * Номер дела
     */
    public String getDisappearNumber() {
        return disappearNumber;
    }


    /**
     * Sets the disappearNumber value for this DisappearStatus.
     * 
     * @param disappearNumber   * Номер дела
     */
    public void setDisappearNumber(String disappearNumber) {
        this.disappearNumber = disappearNumber;
    }


    /**
     * Gets the gpTerritorial value for this DisappearStatus.
     * 
     * @return gpTerritorial   * Орган КПССУ присвоивший статус
     */
    public gbdul.nat.interactive.persistence.dictionaries.GpTerritorial getGpTerritorial() {
        return gpTerritorial;
    }


    /**
     * Sets the gpTerritorial value for this DisappearStatus.
     * 
     * @param gpTerritorial   * Орган КПССУ присвоивший статус
     */
    public void setGpTerritorial(gbdul.nat.interactive.persistence.dictionaries.GpTerritorial gpTerritorial) {
        this.gpTerritorial = gpTerritorial;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DisappearStatus)) return false;
        DisappearStatus other = (DisappearStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.disappear == other.isDisappear() &&
            ((this.disappearDate==null && other.getDisappearDate()==null) || 
             (this.disappearDate!=null &&
              this.disappearDate.equals(other.getDisappearDate()))) &&
            ((this.disappearEndDate==null && other.getDisappearEndDate()==null) || 
             (this.disappearEndDate!=null &&
              this.disappearEndDate.equals(other.getDisappearEndDate()))) &&
            ((this.disappearNumber==null && other.getDisappearNumber()==null) || 
             (this.disappearNumber!=null &&
              this.disappearNumber.equals(other.getDisappearNumber()))) &&
            ((this.gpTerritorial==null && other.getGpTerritorial()==null) || 
             (this.gpTerritorial!=null &&
              this.gpTerritorial.equals(other.getGpTerritorial())));
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
        _hashCode += (isDisappear() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDisappearDate() != null) {
            _hashCode += getDisappearDate().hashCode();
        }
        if (getDisappearEndDate() != null) {
            _hashCode += getDisappearEndDate().hashCode();
        }
        if (getDisappearNumber() != null) {
            _hashCode += getDisappearNumber().hashCode();
        }
        if (getGpTerritorial() != null) {
            _hashCode += getGpTerritorial().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DisappearStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "DisappearStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disappear");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disappear"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disappearDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disappearDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disappearEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disappearEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disappearNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disappearNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gpTerritorial");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gpTerritorial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "GpTerritorial"));
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
