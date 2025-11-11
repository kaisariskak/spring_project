/**
 * ForeignData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Сведения о адресе за пределами Казахстана
 */
public class ForeignData  implements java.io.Serializable {
    /* Область (не Казахстан) */
    private String districtName;

    /* Район (не Казахстан) */
    private String regionName;

    public ForeignData() {
    }

    public ForeignData(
           String districtName,
           String regionName) {
           this.districtName = districtName;
           this.regionName = regionName;
    }


    /**
     * Gets the districtName value for this ForeignData.
     * 
     * @return districtName   * Область (не Казахстан)
     */
    public String getDistrictName() {
        return districtName;
    }


    /**
     * Sets the districtName value for this ForeignData.
     * 
     * @param districtName   * Область (не Казахстан)
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }


    /**
     * Gets the regionName value for this ForeignData.
     * 
     * @return regionName   * Район (не Казахстан)
     */
    public String getRegionName() {
        return regionName;
    }


    /**
     * Sets the regionName value for this ForeignData.
     * 
     * @param regionName   * Район (не Казахстан)
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ForeignData)) return false;
        ForeignData other = (ForeignData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.districtName==null && other.getDistrictName()==null) || 
             (this.districtName!=null &&
              this.districtName.equals(other.getDistrictName()))) &&
            ((this.regionName==null && other.getRegionName()==null) || 
             (this.regionName!=null &&
              this.regionName.equals(other.getRegionName())));
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
        if (getDistrictName() != null) {
            _hashCode += getDistrictName().hashCode();
        }
        if (getRegionName() != null) {
            _hashCode += getRegionName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ForeignData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "ForeignData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("districtName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "districtName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regionName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "regionName"));
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
