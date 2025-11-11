/**
 * MissingStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Статус физического лица 'Пропавший без вести'
 */
public class MissingStatus  implements java.io.Serializable {
    /* Пропавший без вести (Да/Нет) */
    private boolean missing;

    /* Дата начала действия статуса */
    private java.util.Date missingDate;

    /* Дата окончания действия статуса */
    private java.util.Date missingEndDate;

    /* Номер дела */
    private String missingNumber;

    /* Орган КПССУ присвоивший статус */
    private gbdul.nat.interactive.persistence.dictionaries.GpTerritorial gpTerritorial;

    public MissingStatus() {
    }

    public MissingStatus(
           boolean missing,
           java.util.Date missingDate,
           java.util.Date missingEndDate,
           String missingNumber,
           gbdul.nat.interactive.persistence.dictionaries.GpTerritorial gpTerritorial) {
           this.missing = missing;
           this.missingDate = missingDate;
           this.missingEndDate = missingEndDate;
           this.missingNumber = missingNumber;
           this.gpTerritorial = gpTerritorial;
    }


    /**
     * Gets the missing value for this MissingStatus.
     * 
     * @return missing   * Пропавший без вести (Да/Нет)
     */
    public boolean isMissing() {
        return missing;
    }


    /**
     * Sets the missing value for this MissingStatus.
     * 
     * @param missing   * Пропавший без вести (Да/Нет)
     */
    public void setMissing(boolean missing) {
        this.missing = missing;
    }


    /**
     * Gets the missingDate value for this MissingStatus.
     * 
     * @return missingDate   * Дата начала действия статуса
     */
    public java.util.Date getMissingDate() {
        return missingDate;
    }


    /**
     * Sets the missingDate value for this MissingStatus.
     * 
     * @param missingDate   * Дата начала действия статуса
     */
    public void setMissingDate(java.util.Date missingDate) {
        this.missingDate = missingDate;
    }


    /**
     * Gets the missingEndDate value for this MissingStatus.
     * 
     * @return missingEndDate   * Дата окончания действия статуса
     */
    public java.util.Date getMissingEndDate() {
        return missingEndDate;
    }


    /**
     * Sets the missingEndDate value for this MissingStatus.
     * 
     * @param missingEndDate   * Дата окончания действия статуса
     */
    public void setMissingEndDate(java.util.Date missingEndDate) {
        this.missingEndDate = missingEndDate;
    }


    /**
     * Gets the missingNumber value for this MissingStatus.
     * 
     * @return missingNumber   * Номер дела
     */
    public String getMissingNumber() {
        return missingNumber;
    }


    /**
     * Sets the missingNumber value for this MissingStatus.
     * 
     * @param missingNumber   * Номер дела
     */
    public void setMissingNumber(String missingNumber) {
        this.missingNumber = missingNumber;
    }


    /**
     * Gets the gpTerritorial value for this MissingStatus.
     * 
     * @return gpTerritorial   * Орган КПССУ присвоивший статус
     */
    public gbdul.nat.interactive.persistence.dictionaries.GpTerritorial getGpTerritorial() {
        return gpTerritorial;
    }


    /**
     * Sets the gpTerritorial value for this MissingStatus.
     * 
     * @param gpTerritorial   * Орган КПССУ присвоивший статус
     */
    public void setGpTerritorial(gbdul.nat.interactive.persistence.dictionaries.GpTerritorial gpTerritorial) {
        this.gpTerritorial = gpTerritorial;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof MissingStatus)) return false;
        MissingStatus other = (MissingStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.missing == other.isMissing() &&
            ((this.missingDate==null && other.getMissingDate()==null) || 
             (this.missingDate!=null &&
              this.missingDate.equals(other.getMissingDate()))) &&
            ((this.missingEndDate==null && other.getMissingEndDate()==null) || 
             (this.missingEndDate!=null &&
              this.missingEndDate.equals(other.getMissingEndDate()))) &&
            ((this.missingNumber==null && other.getMissingNumber()==null) || 
             (this.missingNumber!=null &&
              this.missingNumber.equals(other.getMissingNumber()))) &&
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
        _hashCode += (isMissing() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMissingDate() != null) {
            _hashCode += getMissingDate().hashCode();
        }
        if (getMissingEndDate() != null) {
            _hashCode += getMissingEndDate().hashCode();
        }
        if (getMissingNumber() != null) {
            _hashCode += getMissingNumber().hashCode();
        }
        if (getGpTerritorial() != null) {
            _hashCode += getGpTerritorial().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MissingStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "MissingStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missing");
        elemField.setXmlName(new javax.xml.namespace.QName("", "missing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "missingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "missingEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "missingNumber"));
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
