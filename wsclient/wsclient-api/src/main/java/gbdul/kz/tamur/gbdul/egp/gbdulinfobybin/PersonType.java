/**
 * PersonType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.tamur.gbdul.egp.gbdulinfobybin;


/**
 * Тип описывающий персону
 */
public class PersonType  implements java.io.Serializable {
    /* Страна постоянного местожительства */
    private gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.DirectoryType country;

    private String IIN;

    /* Налоговый номер в стране инкорпорации для иностранных лиц */
    private String taxNumber;

    /* Фамилия */
    private String surName;

    /* Имя */
    private String name;

    /* Отчество */
    private String middleName;

    public PersonType() {
    }

    public PersonType(
           gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.DirectoryType country,
           String IIN,
           String taxNumber,
           String surName,
           String name,
           String middleName) {
           this.country = country;
           this.IIN = IIN;
           this.taxNumber = taxNumber;
           this.surName = surName;
           this.name = name;
           this.middleName = middleName;
    }


    /**
     * Gets the country value for this PersonType.
     * 
     * @return country   * Страна постоянного местожительства
     */
    public gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.DirectoryType getCountry() {
        return country;
    }


    /**
     * Sets the country value for this PersonType.
     * 
     * @param country   * Страна постоянного местожительства
     */
    public void setCountry(gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.DirectoryType country) {
        this.country = country;
    }


    /**
     * Gets the IIN value for this PersonType.
     * 
     * @return IIN
     */
    public String getIIN() {
        return IIN;
    }


    /**
     * Sets the IIN value for this PersonType.
     * 
     * @param IIN
     */
    public void setIIN(String IIN) {
        this.IIN = IIN;
    }


    /**
     * Gets the taxNumber value for this PersonType.
     * 
     * @return taxNumber   * Налоговый номер в стране инкорпорации для иностранных лиц
     */
    public String getTaxNumber() {
        return taxNumber;
    }


    /**
     * Sets the taxNumber value for this PersonType.
     * 
     * @param taxNumber   * Налоговый номер в стране инкорпорации для иностранных лиц
     */
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }


    /**
     * Gets the surName value for this PersonType.
     * 
     * @return surName   * Фамилия
     */
    public String getSurName() {
        return surName;
    }


    /**
     * Sets the surName value for this PersonType.
     * 
     * @param surName   * Фамилия
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }


    /**
     * Gets the name value for this PersonType.
     * 
     * @return name   * Имя
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this PersonType.
     * 
     * @param name   * Имя
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the middleName value for this PersonType.
     * 
     * @return middleName   * Отчество
     */
    public String getMiddleName() {
        return middleName;
    }


    /**
     * Sets the middleName value for this PersonType.
     * 
     * @param middleName   * Отчество
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PersonType)) return false;
        PersonType other = (PersonType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.IIN==null && other.getIIN()==null) || 
             (this.IIN!=null &&
              this.IIN.equals(other.getIIN()))) &&
            ((this.taxNumber==null && other.getTaxNumber()==null) || 
             (this.taxNumber!=null &&
              this.taxNumber.equals(other.getTaxNumber()))) &&
            ((this.surName==null && other.getSurName()==null) || 
             (this.surName!=null &&
              this.surName.equals(other.getSurName()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.middleName==null && other.getMiddleName()==null) || 
             (this.middleName!=null &&
              this.middleName.equals(other.getMiddleName())));
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
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getIIN() != null) {
            _hashCode += getIIN().hashCode();
        }
        if (getTaxNumber() != null) {
            _hashCode += getTaxNumber().hashCode();
        }
        if (getSurName() != null) {
            _hashCode += getSurName().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getMiddleName() != null) {
            _hashCode += getMiddleName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PersonType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "PersonType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TaxNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SurName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middleName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MiddleName"));
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
