/**
 * OrganizationShortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.tamur.gbdul.egp.gbdulinfobybin;


/**
 * Основной тип, описывающий учредителя-юрлицо, головную организацию
 */
public class OrganizationShortType  implements java.io.Serializable {
    /* БИН */
    private String BIN;

    /* Страна инкорпорации для Неризидента РК */
    private DirectoryType country;

    /* Регистрационный номер в стране инкорпорации для нерезидента
     * РК */
    private String registrationNumber;

    /* Дата последней перерегистрации */
    private java.util.Date registrationLastDate;

    /* Наименование организации на русском языке */
    private String organizationNameRu;

    /* Наименование организации на государственном языке */
    private String organizationNameKz;

    public OrganizationShortType() {
    }

    public OrganizationShortType(
           String BIN,
           DirectoryType country,
           String registrationNumber,
           java.util.Date registrationLastDate,
           String organizationNameRu,
           String organizationNameKz) {
           this.BIN = BIN;
           this.country = country;
           this.registrationNumber = registrationNumber;
           this.registrationLastDate = registrationLastDate;
           this.organizationNameRu = organizationNameRu;
           this.organizationNameKz = organizationNameKz;
    }


    /**
     * Gets the BIN value for this OrganizationShortType.
     * 
     * @return BIN   * БИН
     */
    public String getBIN() {
        return BIN;
    }


    /**
     * Sets the BIN value for this OrganizationShortType.
     * 
     * @param BIN   * БИН
     */
    public void setBIN(String BIN) {
        this.BIN = BIN;
    }


    /**
     * Gets the country value for this OrganizationShortType.
     * 
     * @return country   * Страна инкорпорации для Неризидента РК
     */
    public DirectoryType getCountry() {
        return country;
    }


    /**
     * Sets the country value for this OrganizationShortType.
     * 
     * @param country   * Страна инкорпорации для Неризидента РК
     */
    public void setCountry(DirectoryType country) {
        this.country = country;
    }


    /**
     * Gets the registrationNumber value for this OrganizationShortType.
     * 
     * @return registrationNumber   * Регистрационный номер в стране инкорпорации для нерезидента
     * РК
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }


    /**
     * Sets the registrationNumber value for this OrganizationShortType.
     * 
     * @param registrationNumber   * Регистрационный номер в стране инкорпорации для нерезидента
     * РК
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }


    /**
     * Gets the registrationLastDate value for this OrganizationShortType.
     * 
     * @return registrationLastDate   * Дата последней перерегистрации
     */
    public java.util.Date getRegistrationLastDate() {
        return registrationLastDate;
    }


    /**
     * Sets the registrationLastDate value for this OrganizationShortType.
     * 
     * @param registrationLastDate   * Дата последней перерегистрации
     */
    public void setRegistrationLastDate(java.util.Date registrationLastDate) {
        this.registrationLastDate = registrationLastDate;
    }


    /**
     * Gets the organizationNameRu value for this OrganizationShortType.
     * 
     * @return organizationNameRu   * Наименование организации на русском языке
     */
    public String getOrganizationNameRu() {
        return organizationNameRu;
    }


    /**
     * Sets the organizationNameRu value for this OrganizationShortType.
     * 
     * @param organizationNameRu   * Наименование организации на русском языке
     */
    public void setOrganizationNameRu(String organizationNameRu) {
        this.organizationNameRu = organizationNameRu;
    }


    /**
     * Gets the organizationNameKz value for this OrganizationShortType.
     * 
     * @return organizationNameKz   * Наименование организации на государственном языке
     */
    public String getOrganizationNameKz() {
        return organizationNameKz;
    }


    /**
     * Sets the organizationNameKz value for this OrganizationShortType.
     * 
     * @param organizationNameKz   * Наименование организации на государственном языке
     */
    public void setOrganizationNameKz(String organizationNameKz) {
        this.organizationNameKz = organizationNameKz;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof OrganizationShortType)) return false;
        OrganizationShortType other = (OrganizationShortType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BIN==null && other.getBIN()==null) || 
             (this.BIN!=null &&
              this.BIN.equals(other.getBIN()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.registrationNumber==null && other.getRegistrationNumber()==null) || 
             (this.registrationNumber!=null &&
              this.registrationNumber.equals(other.getRegistrationNumber()))) &&
            ((this.registrationLastDate==null && other.getRegistrationLastDate()==null) || 
             (this.registrationLastDate!=null &&
              this.registrationLastDate.equals(other.getRegistrationLastDate()))) &&
            ((this.organizationNameRu==null && other.getOrganizationNameRu()==null) || 
             (this.organizationNameRu!=null &&
              this.organizationNameRu.equals(other.getOrganizationNameRu()))) &&
            ((this.organizationNameKz==null && other.getOrganizationNameKz()==null) || 
             (this.organizationNameKz!=null &&
              this.organizationNameKz.equals(other.getOrganizationNameKz())));
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
        if (getBIN() != null) {
            _hashCode += getBIN().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getRegistrationNumber() != null) {
            _hashCode += getRegistrationNumber().hashCode();
        }
        if (getRegistrationLastDate() != null) {
            _hashCode += getRegistrationLastDate().hashCode();
        }
        if (getOrganizationNameRu() != null) {
            _hashCode += getOrganizationNameRu().hashCode();
        }
        if (getOrganizationNameKz() != null) {
            _hashCode += getOrganizationNameKz().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrganizationShortType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "OrganizationShortType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegistrationNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationLastDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegistrationLastDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organizationNameRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrganizationNameRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organizationNameKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrganizationNameKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
