/**
 * AddressType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.tamur.gbdul.egp.gbdulinfobybin;


/**
 * Тип описывающий адрес местонахождения
 */
public class AddressType  implements java.io.Serializable {
    /* Регистрационный код адреса */
    private String RKA;

    /* Почтовый индекс */
    private String ZIPCode;

    private String KATO;

    /* Область на русском языке */
    private String districtRu;

    /* Область на государственном языке */
    private String districtKz;

    /* Район на русском языке */
    private String regionRu;

    /* Район на государственном языке */
    private String regionKz;

    /* Населенный пункт на русском языке */
    private String cityRu;

    /* Населенный пункт на государственном языке */
    private String cityKz;

    /* код геонима по ИС АР */
    private String geonimCode;

    /* Название улицы на русском языке */
    private String streetRu;

    /* Название улицы на государственном языке */
    private String streetKz;

    /* Корпус, блок */
    private String corpus;

    /* Номер дома */
    private String buildingNumber;

    /* Номер квартиры */
    private String appartmentNumber;

    public AddressType() {
    }

    public AddressType(
           String RKA,
           String ZIPCode,
           String KATO,
           String districtRu,
           String districtKz,
           String regionRu,
           String regionKz,
           String cityRu,
           String cityKz,
           String geonimCode,
           String streetRu,
           String streetKz,
           String corpus,
           String buildingNumber,
           String appartmentNumber) {
           this.RKA = RKA;
           this.ZIPCode = ZIPCode;
           this.KATO = KATO;
           this.districtRu = districtRu;
           this.districtKz = districtKz;
           this.regionRu = regionRu;
           this.regionKz = regionKz;
           this.cityRu = cityRu;
           this.cityKz = cityKz;
           this.geonimCode = geonimCode;
           this.streetRu = streetRu;
           this.streetKz = streetKz;
           this.corpus = corpus;
           this.buildingNumber = buildingNumber;
           this.appartmentNumber = appartmentNumber;
    }


    /**
     * Gets the RKA value for this AddressType.
     * 
     * @return RKA   * Регистрационный код адреса
     */
    public String getRKA() {
        return RKA;
    }


    /**
     * Sets the RKA value for this AddressType.
     * 
     * @param RKA   * Регистрационный код адреса
     */
    public void setRKA(String RKA) {
        this.RKA = RKA;
    }


    /**
     * Gets the ZIPCode value for this AddressType.
     * 
     * @return ZIPCode   * Почтовый индекс
     */
    public String getZIPCode() {
        return ZIPCode;
    }


    /**
     * Sets the ZIPCode value for this AddressType.
     * 
     * @param ZIPCode   * Почтовый индекс
     */
    public void setZIPCode(String ZIPCode) {
        this.ZIPCode = ZIPCode;
    }


    /**
     * Gets the KATO value for this AddressType.
     * 
     * @return KATO
     */
    public String getKATO() {
        return KATO;
    }


    /**
     * Sets the KATO value for this AddressType.
     * 
     * @param KATO
     */
    public void setKATO(String KATO) {
        this.KATO = KATO;
    }


    /**
     * Gets the districtRu value for this AddressType.
     * 
     * @return districtRu   * Область на русском языке
     */
    public String getDistrictRu() {
        return districtRu;
    }


    /**
     * Sets the districtRu value for this AddressType.
     * 
     * @param districtRu   * Область на русском языке
     */
    public void setDistrictRu(String districtRu) {
        this.districtRu = districtRu;
    }


    /**
     * Gets the districtKz value for this AddressType.
     * 
     * @return districtKz   * Область на государственном языке
     */
    public String getDistrictKz() {
        return districtKz;
    }


    /**
     * Sets the districtKz value for this AddressType.
     * 
     * @param districtKz   * Область на государственном языке
     */
    public void setDistrictKz(String districtKz) {
        this.districtKz = districtKz;
    }


    /**
     * Gets the regionRu value for this AddressType.
     * 
     * @return regionRu   * Район на русском языке
     */
    public String getRegionRu() {
        return regionRu;
    }


    /**
     * Sets the regionRu value for this AddressType.
     * 
     * @param regionRu   * Район на русском языке
     */
    public void setRegionRu(String regionRu) {
        this.regionRu = regionRu;
    }


    /**
     * Gets the regionKz value for this AddressType.
     * 
     * @return regionKz   * Район на государственном языке
     */
    public String getRegionKz() {
        return regionKz;
    }


    /**
     * Sets the regionKz value for this AddressType.
     * 
     * @param regionKz   * Район на государственном языке
     */
    public void setRegionKz(String regionKz) {
        this.regionKz = regionKz;
    }


    /**
     * Gets the cityRu value for this AddressType.
     * 
     * @return cityRu   * Населенный пункт на русском языке
     */
    public String getCityRu() {
        return cityRu;
    }


    /**
     * Sets the cityRu value for this AddressType.
     * 
     * @param cityRu   * Населенный пункт на русском языке
     */
    public void setCityRu(String cityRu) {
        this.cityRu = cityRu;
    }


    /**
     * Gets the cityKz value for this AddressType.
     * 
     * @return cityKz   * Населенный пункт на государственном языке
     */
    public String getCityKz() {
        return cityKz;
    }


    /**
     * Sets the cityKz value for this AddressType.
     * 
     * @param cityKz   * Населенный пункт на государственном языке
     */
    public void setCityKz(String cityKz) {
        this.cityKz = cityKz;
    }


    /**
     * Gets the geonimCode value for this AddressType.
     * 
     * @return geonimCode   * код геонима по ИС АР
     */
    public String getGeonimCode() {
        return geonimCode;
    }


    /**
     * Sets the geonimCode value for this AddressType.
     * 
     * @param geonimCode   * код геонима по ИС АР
     */
    public void setGeonimCode(String geonimCode) {
        this.geonimCode = geonimCode;
    }


    /**
     * Gets the streetRu value for this AddressType.
     * 
     * @return streetRu   * Название улицы на русском языке
     */
    public String getStreetRu() {
        return streetRu;
    }


    /**
     * Sets the streetRu value for this AddressType.
     * 
     * @param streetRu   * Название улицы на русском языке
     */
    public void setStreetRu(String streetRu) {
        this.streetRu = streetRu;
    }


    /**
     * Gets the streetKz value for this AddressType.
     * 
     * @return streetKz   * Название улицы на государственном языке
     */
    public String getStreetKz() {
        return streetKz;
    }


    /**
     * Sets the streetKz value for this AddressType.
     * 
     * @param streetKz   * Название улицы на государственном языке
     */
    public void setStreetKz(String streetKz) {
        this.streetKz = streetKz;
    }


    /**
     * Gets the corpus value for this AddressType.
     * 
     * @return corpus   * Корпус, блок
     */
    public String getCorpus() {
        return corpus;
    }


    /**
     * Sets the corpus value for this AddressType.
     * 
     * @param corpus   * Корпус, блок
     */
    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }


    /**
     * Gets the buildingNumber value for this AddressType.
     * 
     * @return buildingNumber   * Номер дома
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }


    /**
     * Sets the buildingNumber value for this AddressType.
     * 
     * @param buildingNumber   * Номер дома
     */
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }


    /**
     * Gets the appartmentNumber value for this AddressType.
     * 
     * @return appartmentNumber   * Номер квартиры
     */
    public String getAppartmentNumber() {
        return appartmentNumber;
    }


    /**
     * Sets the appartmentNumber value for this AddressType.
     * 
     * @param appartmentNumber   * Номер квартиры
     */
    public void setAppartmentNumber(String appartmentNumber) {
        this.appartmentNumber = appartmentNumber;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof AddressType)) return false;
        AddressType other = (AddressType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RKA==null && other.getRKA()==null) || 
             (this.RKA!=null &&
              this.RKA.equals(other.getRKA()))) &&
            ((this.ZIPCode==null && other.getZIPCode()==null) || 
             (this.ZIPCode!=null &&
              this.ZIPCode.equals(other.getZIPCode()))) &&
            ((this.KATO==null && other.getKATO()==null) || 
             (this.KATO!=null &&
              this.KATO.equals(other.getKATO()))) &&
            ((this.districtRu==null && other.getDistrictRu()==null) || 
             (this.districtRu!=null &&
              this.districtRu.equals(other.getDistrictRu()))) &&
            ((this.districtKz==null && other.getDistrictKz()==null) || 
             (this.districtKz!=null &&
              this.districtKz.equals(other.getDistrictKz()))) &&
            ((this.regionRu==null && other.getRegionRu()==null) || 
             (this.regionRu!=null &&
              this.regionRu.equals(other.getRegionRu()))) &&
            ((this.regionKz==null && other.getRegionKz()==null) || 
             (this.regionKz!=null &&
              this.regionKz.equals(other.getRegionKz()))) &&
            ((this.cityRu==null && other.getCityRu()==null) || 
             (this.cityRu!=null &&
              this.cityRu.equals(other.getCityRu()))) &&
            ((this.cityKz==null && other.getCityKz()==null) || 
             (this.cityKz!=null &&
              this.cityKz.equals(other.getCityKz()))) &&
            ((this.geonimCode==null && other.getGeonimCode()==null) || 
             (this.geonimCode!=null &&
              this.geonimCode.equals(other.getGeonimCode()))) &&
            ((this.streetRu==null && other.getStreetRu()==null) || 
             (this.streetRu!=null &&
              this.streetRu.equals(other.getStreetRu()))) &&
            ((this.streetKz==null && other.getStreetKz()==null) || 
             (this.streetKz!=null &&
              this.streetKz.equals(other.getStreetKz()))) &&
            ((this.corpus==null && other.getCorpus()==null) || 
             (this.corpus!=null &&
              this.corpus.equals(other.getCorpus()))) &&
            ((this.buildingNumber==null && other.getBuildingNumber()==null) || 
             (this.buildingNumber!=null &&
              this.buildingNumber.equals(other.getBuildingNumber()))) &&
            ((this.appartmentNumber==null && other.getAppartmentNumber()==null) || 
             (this.appartmentNumber!=null &&
              this.appartmentNumber.equals(other.getAppartmentNumber())));
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
        if (getRKA() != null) {
            _hashCode += getRKA().hashCode();
        }
        if (getZIPCode() != null) {
            _hashCode += getZIPCode().hashCode();
        }
        if (getKATO() != null) {
            _hashCode += getKATO().hashCode();
        }
        if (getDistrictRu() != null) {
            _hashCode += getDistrictRu().hashCode();
        }
        if (getDistrictKz() != null) {
            _hashCode += getDistrictKz().hashCode();
        }
        if (getRegionRu() != null) {
            _hashCode += getRegionRu().hashCode();
        }
        if (getRegionKz() != null) {
            _hashCode += getRegionKz().hashCode();
        }
        if (getCityRu() != null) {
            _hashCode += getCityRu().hashCode();
        }
        if (getCityKz() != null) {
            _hashCode += getCityKz().hashCode();
        }
        if (getGeonimCode() != null) {
            _hashCode += getGeonimCode().hashCode();
        }
        if (getStreetRu() != null) {
            _hashCode += getStreetRu().hashCode();
        }
        if (getStreetKz() != null) {
            _hashCode += getStreetKz().hashCode();
        }
        if (getCorpus() != null) {
            _hashCode += getCorpus().hashCode();
        }
        if (getBuildingNumber() != null) {
            _hashCode += getBuildingNumber().hashCode();
        }
        if (getAppartmentNumber() != null) {
            _hashCode += getAppartmentNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "AddressType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RKA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RKA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZIPCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZIPCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("districtRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DistrictRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("districtKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DistrictKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regionRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegionRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regionKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegionKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cityRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CityRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cityKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CityKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("geonimCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GeonimCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StreetRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StreetKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("corpus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Corpus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BuildingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appartmentNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AppartmentNumber"));
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
