/**
 * Certificate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Данные о свидетельстве
 */
public class Certificate  implements java.io.Serializable {
    /* Номер свидетельства */
    private String number;

    /* Дата выдачи свидетельства */
    private java.util.Date beginDate;

    /* Организация, выдавшая свидетельство */
    private String issueOrganisation;

    public Certificate() {
    }

    public Certificate(
           String number,
           java.util.Date beginDate,
           String issueOrganisation) {
           this.number = number;
           this.beginDate = beginDate;
           this.issueOrganisation = issueOrganisation;
    }


    /**
     * Gets the number value for this Certificate.
     * 
     * @return number   * Номер свидетельства
     */
    public String getNumber() {
        return number;
    }


    /**
     * Sets the number value for this Certificate.
     * 
     * @param number   * Номер свидетельства
     */
    public void setNumber(String number) {
        this.number = number;
    }


    /**
     * Gets the beginDate value for this Certificate.
     * 
     * @return beginDate   * Дата выдачи свидетельства
     */
    public java.util.Date getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this Certificate.
     * 
     * @param beginDate   * Дата выдачи свидетельства
     */
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the issueOrganisation value for this Certificate.
     * 
     * @return issueOrganisation   * Организация, выдавшая свидетельство
     */
    public String getIssueOrganisation() {
        return issueOrganisation;
    }


    /**
     * Sets the issueOrganisation value for this Certificate.
     * 
     * @param issueOrganisation   * Организация, выдавшая свидетельство
     */
    public void setIssueOrganisation(String issueOrganisation) {
        this.issueOrganisation = issueOrganisation;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Certificate)) return false;
        Certificate other = (Certificate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.issueOrganisation==null && other.getIssueOrganisation()==null) || 
             (this.issueOrganisation!=null &&
              this.issueOrganisation.equals(other.getIssueOrganisation())));
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
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getBeginDate() != null) {
            _hashCode += getBeginDate().hashCode();
        }
        if (getIssueOrganisation() != null) {
            _hashCode += getIssueOrganisation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Certificate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "Certificate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueOrganisation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "issueOrganisation"));
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
