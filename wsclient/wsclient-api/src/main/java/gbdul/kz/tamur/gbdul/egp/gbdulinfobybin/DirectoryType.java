/**
 * DirectoryType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.tamur.gbdul.egp.gbdulinfobybin;


/**
 * Тип, описывающий данные справочника
 */
public class DirectoryType  implements java.io.Serializable {
    /* код */
    private String code;

    /* значение на русском языке */
    private String nameRu;

    /* значение нагосударственном языке */
    private String nameKz;

    public DirectoryType() {
    }

    public DirectoryType(
           String code,
           String nameRu,
           String nameKz) {
           this.code = code;
           this.nameRu = nameRu;
           this.nameKz = nameKz;
    }


    /**
     * Gets the code value for this DirectoryType.
     * 
     * @return code   * код
     */
    public String getCode() {
        return code;
    }


    /**
     * Sets the code value for this DirectoryType.
     * 
     * @param code   * код
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Gets the nameRu value for this DirectoryType.
     * 
     * @return nameRu   * значение на русском языке
     */
    public String getNameRu() {
        return nameRu;
    }


    /**
     * Sets the nameRu value for this DirectoryType.
     * 
     * @param nameRu   * значение на русском языке
     */
    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }


    /**
     * Gets the nameKz value for this DirectoryType.
     * 
     * @return nameKz   * значение нагосударственном языке
     */
    public String getNameKz() {
        return nameKz;
    }


    /**
     * Sets the nameKz value for this DirectoryType.
     * 
     * @param nameKz   * значение нагосударственном языке
     */
    public void setNameKz(String nameKz) {
        this.nameKz = nameKz;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DirectoryType)) return false;
        DirectoryType other = (DirectoryType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.nameRu==null && other.getNameRu()==null) || 
             (this.nameRu!=null &&
              this.nameRu.equals(other.getNameRu()))) &&
            ((this.nameKz==null && other.getNameKz()==null) || 
             (this.nameKz!=null &&
              this.nameKz.equals(other.getNameKz())));
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
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getNameRu() != null) {
            _hashCode += getNameRu().hashCode();
        }
        if (getNameKz() != null) {
            _hashCode += getNameKz().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DirectoryType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NameRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NameKz"));
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
