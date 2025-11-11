/**
 * ResponseDataType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdfm;


/**
 * Формат ответа
 */
public class ResponseDataType  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    /* Статус ответа */
    private gbdfm.DirectoryType status;

    /* Данные по Родственным связям физического лица */
    private gbdfm.FamilyDataType familyData;

    public ResponseDataType() {
    }

    public ResponseDataType(
           gbdfm.DirectoryType status,
           gbdfm.FamilyDataType familyData) {
        this.status = status;
        this.familyData = familyData;
    }


    /**
     * Gets the status value for this ResponseDataType.
     *
     * @return status   * Статус ответа
     */
    public gbdfm.DirectoryType getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ResponseDataType.
     *
     * @param status   * Статус ответа
     */
    public void setStatus(gbdfm.DirectoryType status) {
        this.status = status;
    }


    /**
     * Gets the familyData value for this ResponseDataType.
     *
     * @return familyData   * Данные по Родственным связям физического лица
     */
    public gbdfm.FamilyDataType getFamilyData() {
        return familyData;
    }


    /**
     * Sets the familyData value for this ResponseDataType.
     *
     * @param familyData   * Данные по Родственным связям физического лица
     */
    public void setFamilyData(gbdfm.FamilyDataType familyData) {
        this.familyData = familyData;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ResponseDataType)) return false;
        ResponseDataType other = (ResponseDataType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.status==null && other.getStatus()==null) ||
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.familyData==null && other.getFamilyData()==null) ||
             (this.familyData!=null &&
              this.familyData.equals(other.getFamilyData())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getFamilyData() != null) {
            _hashCode += getFamilyData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResponseDataType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "ResponseDataType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "DirectoryType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("familyData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "familyData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "familyDataType"));
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


    /**
     * Writes the exception data to the faultDetails
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
