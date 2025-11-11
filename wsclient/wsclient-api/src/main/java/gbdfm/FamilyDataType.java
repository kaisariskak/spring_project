/**
 * FamilyDataType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdfm;

public class FamilyDataType  implements java.io.Serializable {
    /* Данные по Родственным связям физического лица */
    private gbdfm.FamilyInfoDTO familyInfoList;

    public FamilyDataType() {
    }

    public FamilyDataType(
           gbdfm.FamilyInfoDTO familyInfoList) {
           this.familyInfoList = familyInfoList;
    }


    /**
     * Gets the familyInfoList value for this FamilyDataType.
     * 
     * @return familyInfoList   * Данные по Родственным связям физического лица
     */
    public gbdfm.FamilyInfoDTO getFamilyInfoList() {
        return familyInfoList;
    }


    /**
     * Sets the familyInfoList value for this FamilyDataType.
     * 
     * @param familyInfoList   * Данные по Родственным связям физического лица
     */
    public void setFamilyInfoList(gbdfm.FamilyInfoDTO familyInfoList) {
        this.familyInfoList = familyInfoList;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof FamilyDataType)) return false;
        FamilyDataType other = (FamilyDataType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.familyInfoList==null && other.getFamilyInfoList()==null) ||
             (this.familyInfoList!=null &&
              this.familyInfoList.equals(other.getFamilyInfoList())));
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
        if (getFamilyInfoList() != null) {
            _hashCode += getFamilyInfoList().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FamilyDataType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "familyDataType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("familyInfoList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "familyInfoList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "familyInfoDTO"));
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
