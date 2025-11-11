/**
 * PersonCapableStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Сведения о дееспособности. Также содержит сведения о осуждении
 * по статьям 192,
 *                         216, 217 УК
 */
public class PersonCapableStatus  implements java.io.Serializable {
    /* Статус дееспособности */
    private gbdul.nat.interactive.persistence.dictionaries.CapableStatus capableStatus;

    /* Дата начала действия статуса */
    private java.util.Date capableDate;

    /* Дата окончания действия статуса */
    private java.util.Date capableEndDate;

    /* Номер дела в суде */
    private String capableNumber;

    /* Суд присвоивший статус */
    private gbdul.nat.interactive.persistence.dictionaries.Court court;

    public PersonCapableStatus() {
    }

    public PersonCapableStatus(
           gbdul.nat.interactive.persistence.dictionaries.CapableStatus capableStatus,
           java.util.Date capableDate,
           java.util.Date capableEndDate,
           String capableNumber,
           gbdul.nat.interactive.persistence.dictionaries.Court court) {
           this.capableStatus = capableStatus;
           this.capableDate = capableDate;
           this.capableEndDate = capableEndDate;
           this.capableNumber = capableNumber;
           this.court = court;
    }


    /**
     * Gets the capableStatus value for this PersonCapableStatus.
     * 
     * @return capableStatus   * Статус дееспособности
     */
    public gbdul.nat.interactive.persistence.dictionaries.CapableStatus getCapableStatus() {
        return capableStatus;
    }


    /**
     * Sets the capableStatus value for this PersonCapableStatus.
     * 
     * @param capableStatus   * Статус дееспособности
     */
    public void setCapableStatus(gbdul.nat.interactive.persistence.dictionaries.CapableStatus capableStatus) {
        this.capableStatus = capableStatus;
    }


    /**
     * Gets the capableDate value for this PersonCapableStatus.
     * 
     * @return capableDate   * Дата начала действия статуса
     */
    public java.util.Date getCapableDate() {
        return capableDate;
    }


    /**
     * Sets the capableDate value for this PersonCapableStatus.
     * 
     * @param capableDate   * Дата начала действия статуса
     */
    public void setCapableDate(java.util.Date capableDate) {
        this.capableDate = capableDate;
    }


    /**
     * Gets the capableEndDate value for this PersonCapableStatus.
     * 
     * @return capableEndDate   * Дата окончания действия статуса
     */
    public java.util.Date getCapableEndDate() {
        return capableEndDate;
    }


    /**
     * Sets the capableEndDate value for this PersonCapableStatus.
     * 
     * @param capableEndDate   * Дата окончания действия статуса
     */
    public void setCapableEndDate(java.util.Date capableEndDate) {
        this.capableEndDate = capableEndDate;
    }


    /**
     * Gets the capableNumber value for this PersonCapableStatus.
     * 
     * @return capableNumber   * Номер дела в суде
     */
    public String getCapableNumber() {
        return capableNumber;
    }


    /**
     * Sets the capableNumber value for this PersonCapableStatus.
     * 
     * @param capableNumber   * Номер дела в суде
     */
    public void setCapableNumber(String capableNumber) {
        this.capableNumber = capableNumber;
    }


    /**
     * Gets the court value for this PersonCapableStatus.
     * 
     * @return court   * Суд присвоивший статус
     */
    public gbdul.nat.interactive.persistence.dictionaries.Court getCourt() {
        return court;
    }


    /**
     * Sets the court value for this PersonCapableStatus.
     * 
     * @param court   * Суд присвоивший статус
     */
    public void setCourt(gbdul.nat.interactive.persistence.dictionaries.Court court) {
        this.court = court;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PersonCapableStatus)) return false;
        PersonCapableStatus other = (PersonCapableStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.capableStatus==null && other.getCapableStatus()==null) || 
             (this.capableStatus!=null &&
              this.capableStatus.equals(other.getCapableStatus()))) &&
            ((this.capableDate==null && other.getCapableDate()==null) || 
             (this.capableDate!=null &&
              this.capableDate.equals(other.getCapableDate()))) &&
            ((this.capableEndDate==null && other.getCapableEndDate()==null) || 
             (this.capableEndDate!=null &&
              this.capableEndDate.equals(other.getCapableEndDate()))) &&
            ((this.capableNumber==null && other.getCapableNumber()==null) || 
             (this.capableNumber!=null &&
              this.capableNumber.equals(other.getCapableNumber()))) &&
            ((this.court==null && other.getCourt()==null) || 
             (this.court!=null &&
              this.court.equals(other.getCourt())));
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
        if (getCapableStatus() != null) {
            _hashCode += getCapableStatus().hashCode();
        }
        if (getCapableDate() != null) {
            _hashCode += getCapableDate().hashCode();
        }
        if (getCapableEndDate() != null) {
            _hashCode += getCapableEndDate().hashCode();
        }
        if (getCapableNumber() != null) {
            _hashCode += getCapableNumber().hashCode();
        }
        if (getCourt() != null) {
            _hashCode += getCourt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PersonCapableStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "PersonCapableStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capableStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capableStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "CapableStatus"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capableDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capableDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capableEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capableEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capableNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capableNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("court");
        elemField.setXmlName(new javax.xml.namespace.QName("", "court"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Court"));
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
