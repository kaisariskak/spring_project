/**
 * RegAddress.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Адрес прописки (регистрации)
 */
public class RegAddress  implements java.io.Serializable {
    /* Страна */
    private gbdul.nat.interactive.persistence.dictionaries.Country country;

    /* Область */
    private gbdul.nat.interactive.persistence.dictionaries.District district;

    /* Район */
    private gbdul.nat.interactive.persistence.dictionaries.Region region;

    /* Данные о адресе за пределами Казахстана */
    private gbdul.nat.interactive.persistence.person.ForeignData foreignData;

    /* Населенный пункт */
    private String city;

    /* Улица */
    private String street;

    /* Дом */
    private String building;

    /* Корпус */
    private String corpus;

    /* Квартира */
    private String flat;

    /* Дата регистрации по адресу прописки */
    private java.util.Date beginDate;

    /* Дата снятия с регистрации по адресу прописки */
    private java.util.Date endDate;

    /* Признак регистрации (зарегистрирован/снят с регистрации) */
    private gbdul.nat.interactive.persistence.dictionaries.AddressStatus status;

    /* Причина снятия с регистрации */
    private gbdul.nat.interactive.persistence.dictionaries.AddressInvalidity invalidity;

    /* Код адреса в формате Адресного регистра */
    private String arCode;

    public RegAddress() {
    }

    public RegAddress(
           gbdul.nat.interactive.persistence.dictionaries.Country country,
           gbdul.nat.interactive.persistence.dictionaries.District district,
           gbdul.nat.interactive.persistence.dictionaries.Region region,
           gbdul.nat.interactive.persistence.person.ForeignData foreignData,
           String city,
           String street,
           String building,
           String corpus,
           String flat,
           java.util.Date beginDate,
           java.util.Date endDate,
           gbdul.nat.interactive.persistence.dictionaries.AddressStatus status,
           gbdul.nat.interactive.persistence.dictionaries.AddressInvalidity invalidity,
           String arCode) {
           this.country = country;
           this.district = district;
           this.region = region;
           this.foreignData = foreignData;
           this.city = city;
           this.street = street;
           this.building = building;
           this.corpus = corpus;
           this.flat = flat;
           this.beginDate = beginDate;
           this.endDate = endDate;
           this.status = status;
           this.invalidity = invalidity;
           this.arCode = arCode;
    }


    /**
     * Gets the country value for this RegAddress.
     * 
     * @return country   * Страна
     */
    public gbdul.nat.interactive.persistence.dictionaries.Country getCountry() {
        return country;
    }


    /**
     * Sets the country value for this RegAddress.
     * 
     * @param country   * Страна
     */
    public void setCountry(gbdul.nat.interactive.persistence.dictionaries.Country country) {
        this.country = country;
    }


    /**
     * Gets the district value for this RegAddress.
     * 
     * @return district   * Область
     */
    public gbdul.nat.interactive.persistence.dictionaries.District getDistrict() {
        return district;
    }


    /**
     * Sets the district value for this RegAddress.
     * 
     * @param district   * Область
     */
    public void setDistrict(gbdul.nat.interactive.persistence.dictionaries.District district) {
        this.district = district;
    }


    /**
     * Gets the region value for this RegAddress.
     * 
     * @return region   * Район
     */
    public gbdul.nat.interactive.persistence.dictionaries.Region getRegion() {
        return region;
    }


    /**
     * Sets the region value for this RegAddress.
     * 
     * @param region   * Район
     */
    public void setRegion(gbdul.nat.interactive.persistence.dictionaries.Region region) {
        this.region = region;
    }


    /**
     * Gets the foreignData value for this RegAddress.
     * 
     * @return foreignData   * Данные о адресе за пределами Казахстана
     */
    public gbdul.nat.interactive.persistence.person.ForeignData getForeignData() {
        return foreignData;
    }


    /**
     * Sets the foreignData value for this RegAddress.
     * 
     * @param foreignData   * Данные о адресе за пределами Казахстана
     */
    public void setForeignData(gbdul.nat.interactive.persistence.person.ForeignData foreignData) {
        this.foreignData = foreignData;
    }


    /**
     * Gets the city value for this RegAddress.
     * 
     * @return city   * Населенный пункт
     */
    public String getCity() {
        return city;
    }


    /**
     * Sets the city value for this RegAddress.
     * 
     * @param city   * Населенный пункт
     */
    public void setCity(String city) {
        this.city = city;
    }


    /**
     * Gets the street value for this RegAddress.
     * 
     * @return street   * Улица
     */
    public String getStreet() {
        return street;
    }


    /**
     * Sets the street value for this RegAddress.
     * 
     * @param street   * Улица
     */
    public void setStreet(String street) {
        this.street = street;
    }


    /**
     * Gets the building value for this RegAddress.
     * 
     * @return building   * Дом
     */
    public String getBuilding() {
        return building;
    }


    /**
     * Sets the building value for this RegAddress.
     * 
     * @param building   * Дом
     */
    public void setBuilding(String building) {
        this.building = building;
    }


    /**
     * Gets the corpus value for this RegAddress.
     * 
     * @return corpus   * Корпус
     */
    public String getCorpus() {
        return corpus;
    }


    /**
     * Sets the corpus value for this RegAddress.
     * 
     * @param corpus   * Корпус
     */
    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }


    /**
     * Gets the flat value for this RegAddress.
     * 
     * @return flat   * Квартира
     */
    public String getFlat() {
        return flat;
    }


    /**
     * Sets the flat value for this RegAddress.
     * 
     * @param flat   * Квартира
     */
    public void setFlat(String flat) {
        this.flat = flat;
    }


    /**
     * Gets the beginDate value for this RegAddress.
     * 
     * @return beginDate   * Дата регистрации по адресу прописки
     */
    public java.util.Date getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this RegAddress.
     * 
     * @param beginDate   * Дата регистрации по адресу прописки
     */
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the endDate value for this RegAddress.
     * 
     * @return endDate   * Дата снятия с регистрации по адресу прописки
     */
    public java.util.Date getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this RegAddress.
     * 
     * @param endDate   * Дата снятия с регистрации по адресу прописки
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the status value for this RegAddress.
     * 
     * @return status   * Признак регистрации (зарегистрирован/снят с регистрации)
     */
    public gbdul.nat.interactive.persistence.dictionaries.AddressStatus getStatus() {
        return status;
    }


    /**
     * Sets the status value for this RegAddress.
     * 
     * @param status   * Признак регистрации (зарегистрирован/снят с регистрации)
     */
    public void setStatus(gbdul.nat.interactive.persistence.dictionaries.AddressStatus status) {
        this.status = status;
    }


    /**
     * Gets the invalidity value for this RegAddress.
     * 
     * @return invalidity   * Причина снятия с регистрации
     */
    public gbdul.nat.interactive.persistence.dictionaries.AddressInvalidity getInvalidity() {
        return invalidity;
    }


    /**
     * Sets the invalidity value for this RegAddress.
     * 
     * @param invalidity   * Причина снятия с регистрации
     */
    public void setInvalidity(gbdul.nat.interactive.persistence.dictionaries.AddressInvalidity invalidity) {
        this.invalidity = invalidity;
    }


    /**
     * Gets the arCode value for this RegAddress.
     * 
     * @return arCode   * Код адреса в формате Адресного регистра
     */
    public String getArCode() {
        return arCode;
    }


    /**
     * Sets the arCode value for this RegAddress.
     * 
     * @param arCode   * Код адреса в формате Адресного регистра
     */
    public void setArCode(String arCode) {
        this.arCode = arCode;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RegAddress)) return false;
        RegAddress other = (RegAddress) obj;
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
            ((this.street==null && other.getStreet()==null) || 
             (this.street!=null &&
              this.street.equals(other.getStreet()))) &&
            ((this.building==null && other.getBuilding()==null) || 
             (this.building!=null &&
              this.building.equals(other.getBuilding()))) &&
            ((this.corpus==null && other.getCorpus()==null) || 
             (this.corpus!=null &&
              this.corpus.equals(other.getCorpus()))) &&
            ((this.flat==null && other.getFlat()==null) || 
             (this.flat!=null &&
              this.flat.equals(other.getFlat()))) &&
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.invalidity==null && other.getInvalidity()==null) || 
             (this.invalidity!=null &&
              this.invalidity.equals(other.getInvalidity()))) &&
            ((this.arCode==null && other.getArCode()==null) || 
             (this.arCode!=null &&
              this.arCode.equals(other.getArCode())));
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
        if (getStreet() != null) {
            _hashCode += getStreet().hashCode();
        }
        if (getBuilding() != null) {
            _hashCode += getBuilding().hashCode();
        }
        if (getCorpus() != null) {
            _hashCode += getCorpus().hashCode();
        }
        if (getFlat() != null) {
            _hashCode += getFlat().hashCode();
        }
        if (getBeginDate() != null) {
            _hashCode += getBeginDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getInvalidity() != null) {
            _hashCode += getInvalidity().hashCode();
        }
        if (getArCode() != null) {
            _hashCode += getArCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegAddress.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "RegAddress"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Country"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("street");
        elemField.setXmlName(new javax.xml.namespace.QName("", "street"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("building");
        elemField.setXmlName(new javax.xml.namespace.QName("", "building"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corpus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "corpus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "AddressStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invalidity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "invalidity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "AddressInvalidity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arCode"));
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
