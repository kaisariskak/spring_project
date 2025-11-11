/**
 * BirthPlace.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Адрес места рождения
 */
public class BirthPlace  implements java.io.Serializable {
    /* Страна */
    private gbdul.nat.interactive.persistence.dictionaries.Country country;

    /* Область. Поле может путь пустым в случае непустого значения
     * поля
     *                                 foreignData */
    private gbdul.nat.interactive.persistence.dictionaries.District district;

    /* Район. Поле может путь пустым в случае непустого значения поля
     * foreignData */
    private gbdul.nat.interactive.persistence.dictionaries.Region region;

    /* Данные о адресе за пределами Казахстана */
    private gbdul.nat.interactive.persistence.person.ForeignData foreignData;

    /* Населенный пункт */
    private String city;

    /* Код адреса рождения в формате Адресного регистра */
    private String birthTeCodeAR;

    public BirthPlace() {
    }

    public BirthPlace(
           gbdul.nat.interactive.persistence.dictionaries.Country country,
           gbdul.nat.interactive.persistence.dictionaries.District district,
           gbdul.nat.interactive.persistence.dictionaries.Region region,
           gbdul.nat.interactive.persistence.person.ForeignData foreignData,
           String city,
           String birthTeCodeAR) {
           this.country = country;
           this.district = district;
           this.region = region;
           this.foreignData = foreignData;
           this.city = city;
           this.birthTeCodeAR = birthTeCodeAR;
    }


    /**
     * Gets the country value for this BirthPlace.
     * 
     * @return country   * Страна
     */
    public gbdul.nat.interactive.persistence.dictionaries.Country getCountry() {
        return country;
    }


    /**
     * Sets the country value for this BirthPlace.
     * 
     * @param country   * Страна
     */
    public void setCountry(gbdul.nat.interactive.persistence.dictionaries.Country country) {
        this.country = country;
    }


    /**
     * Gets the district value for this BirthPlace.
     * 
     * @return district   * Область. Поле может путь пустым в случае непустого значения
     * поля
     *                                 foreignData
     */
    public gbdul.nat.interactive.persistence.dictionaries.District getDistrict() {
        return district;
    }


    /**
     * Sets the district value for this BirthPlace.
     * 
     * @param district   * Область. Поле может путь пустым в случае непустого значения
     * поля
     *                                 foreignData
     */
    public void setDistrict(gbdul.nat.interactive.persistence.dictionaries.District district) {
        this.district = district;
    }


    /**
     * Gets the region value for this BirthPlace.
     * 
     * @return region   * Район. Поле может путь пустым в случае непустого значения поля
     * foreignData
     */
    public gbdul.nat.interactive.persistence.dictionaries.Region getRegion() {
        return region;
    }


    /**
     * Sets the region value for this BirthPlace.
     * 
     * @param region   * Район. Поле может путь пустым в случае непустого значения поля
     * foreignData
     */
    public void setRegion(gbdul.nat.interactive.persistence.dictionaries.Region region) {
        this.region = region;
    }


    /**
     * Gets the foreignData value for this BirthPlace.
     * 
     * @return foreignData   * Данные о адресе за пределами Казахстана
     */
    public gbdul.nat.interactive.persistence.person.ForeignData getForeignData() {
        return foreignData;
    }


    /**
     * Sets the foreignData value for this BirthPlace.
     * 
     * @param foreignData   * Данные о адресе за пределами Казахстана
     */
    public void setForeignData(gbdul.nat.interactive.persistence.person.ForeignData foreignData) {
        this.foreignData = foreignData;
    }


    /**
     * Gets the city value for this BirthPlace.
     * 
     * @return city   * Населенный пункт
     */
    public String getCity() {
        return city;
    }


    /**
     * Sets the city value for this BirthPlace.
     * 
     * @param city   * Населенный пункт
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Gets the birthTeCodeAR value for this BirthPlace.
     * 
     * @return birthTeCodeAR   * Код адреса рождения в формате Адресного регистра
     */
    public String getBirthTeCodeAR() {
        return birthTeCodeAR;
    }


    /**
     * Sets the birthTeCodeAR value for this BirthPlace.
     * 
     * @param birthTeCodeAR   * Код адреса рождения в формате Адресного регистра
     */
    public void setBirthTeCodeAR(String birthTeCodeAR) {
        this.birthTeCodeAR = birthTeCodeAR;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BirthPlace)) return false;
        BirthPlace other = (BirthPlace) obj;
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
            ((this.district==null && other.getDistrict()==null) || 
             (this.district!=null &&
              this.district.equals(other.getDistrict()))) &&
            ((this.region==null && other.getRegion()==null) || 
             (this.region!=null &&
              this.region.equals(other.getRegion()))) &&
            ((this.foreignData==null && other.getForeignData()==null) || 
             (this.foreignData!=null &&
              this.foreignData.equals(other.getForeignData()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.birthTeCodeAR==null && other.getBirthTeCodeAR()==null) || 
             (this.birthTeCodeAR!=null &&
              this.birthTeCodeAR.equals(other.getBirthTeCodeAR())));
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
        if (getDistrict() != null) {
            _hashCode += getDistrict().hashCode();
        }
        if (getRegion() != null) {
            _hashCode += getRegion().hashCode();
        }
        if (getForeignData() != null) {
            _hashCode += getForeignData().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getBirthTeCodeAR() != null) {
            _hashCode += getBirthTeCodeAR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BirthPlace.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "BirthPlace"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Country"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("district");
        elemField.setXmlName(new javax.xml.namespace.QName("", "district"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "District"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("region");
        elemField.setXmlName(new javax.xml.namespace.QName("", "region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Region"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreignData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foreignData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "ForeignData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthTeCodeAR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthTeCodeAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
