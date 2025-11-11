/**
 * DocumentType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.dictionaries;


/**
 * Справочник типов документов удостоверяющих личность
 */
public class DocumentType  implements java.io.Serializable {
    /* Код справочного значения */
    private String code;

    /* Наименование на русском языке */
    private String nameRu;

    /* Наименование на казахском языке */
    private String nameKz;

    /* Дата последнего изменения справочного значения */
    private java.util.Calendar changeDate;

    public DocumentType() {
    }

    public DocumentType(
           String code,
           String nameRu,
           String nameKz,
           java.util.Calendar changeDate) {
           this.code = code;
           this.nameRu = nameRu;
           this.nameKz = nameKz;
           this.changeDate = changeDate;
    }


    /**
     * Gets the code value for this DocumentType.
     * 
     * @return code   * Код справочного значения
     */
    public String getCode() {
        return code;
    }


    /**
     * Sets the code value for this DocumentType.
     * 
     * @param code   * Код справочного значения
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Gets the nameRu value for this DocumentType.
     * 
     * @return nameRu   * Наименование на русском языке
     */
    public String getNameRu() {
        return nameRu;
    }


    /**
     * Sets the nameRu value for this DocumentType.
     * 
     * @param nameRu   * Наименование на русском языке
     */
    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }


    /**
     * Gets the nameKz value for this DocumentType.
     * 
     * @return nameKz   * Наименование на казахском языке
     */
    public String getNameKz() {
        return nameKz;
    }


    /**
     * Sets the nameKz value for this DocumentType.
     * 
     * @param nameKz   * Наименование на казахском языке
     */
    public void setNameKz(String nameKz) {
        this.nameKz = nameKz;
    }


    /**
     * Gets the changeDate value for this DocumentType.
     * 
     * @return changeDate   * Дата последнего изменения справочного значения
     */
    public java.util.Calendar getChangeDate() {
        return changeDate;
    }


    /**
     * Sets the changeDate value for this DocumentType.
     * 
     * @param changeDate   * Дата последнего изменения справочного значения
     */
    public void setChangeDate(java.util.Calendar changeDate) {
        this.changeDate = changeDate;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof DocumentType)) return false;
        DocumentType other = (DocumentType) obj;
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
              this.nameKz.equals(other.getNameKz()))) &&
            ((this.changeDate==null && other.getChangeDate()==null) || 
             (this.changeDate!=null &&
              this.changeDate.equals(other.getChangeDate())));
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
        if (getChangeDate() != null) {
            _hashCode += getChangeDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "DocumentType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("", "code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nameRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nameKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
