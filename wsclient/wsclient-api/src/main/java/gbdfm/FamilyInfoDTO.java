/**
 * FamilyInfoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdfm;

public class FamilyInfoDTO  implements java.io.Serializable {
    private gbdfm.BirthDTO[] birthInfos;

    private gbdfm.DivorceDTO[] divorceInfos;

    private gbdfm.MarriageDTO[] marriageInfos;

    public FamilyInfoDTO() {
    }

    public FamilyInfoDTO(
           gbdfm.BirthDTO[] birthInfos,
           gbdfm.DivorceDTO[] divorceInfos,
           gbdfm.MarriageDTO[] marriageInfos) {
           this.birthInfos = birthInfos;
           this.divorceInfos = divorceInfos;
           this.marriageInfos = marriageInfos;
    }


    /**
     * Gets the birthInfos value for this FamilyInfoDTO.
     *
     * @return birthInfos
     */
    public gbdfm.BirthDTO[] getBirthInfos() {
        return birthInfos;
    }


    /**
     * Sets the birthInfos value for this FamilyInfoDTO.
     *
     * @param birthInfos
     */
    public void setBirthInfos(gbdfm.BirthDTO[] birthInfos) {
        this.birthInfos = birthInfos;
    }

    public gbdfm.BirthDTO getBirthInfos(int i) {
        return this.birthInfos[i];
    }

    public void setBirthInfos(int i, gbdfm.BirthDTO _value) {
        this.birthInfos[i] = _value;
    }


    /**
     * Gets the divorceInfos value for this FamilyInfoDTO.
     *
     * @return divorceInfos
     */
    public gbdfm.DivorceDTO[] getDivorceInfos() {
        return divorceInfos;
    }


    /**
     * Sets the divorceInfos value for this FamilyInfoDTO.
     *
     * @param divorceInfos
     */
    public void setDivorceInfos(gbdfm.DivorceDTO[] divorceInfos) {
        this.divorceInfos = divorceInfos;
    }

    public gbdfm.DivorceDTO getDivorceInfos(int i) {
        return this.divorceInfos[i];
    }

    public void setDivorceInfos(int i, gbdfm.DivorceDTO _value) {
        this.divorceInfos[i] = _value;
    }


    /**
     * Gets the marriageInfos value for this FamilyInfoDTO.
     *
     * @return marriageInfos
     */
    public gbdfm.MarriageDTO[] getMarriageInfos() {
        return marriageInfos;
    }


    /**
     * Sets the marriageInfos value for this FamilyInfoDTO.
     *
     * @param marriageInfos
     */
    public void setMarriageInfos(gbdfm.MarriageDTO[] marriageInfos) {
        this.marriageInfos = marriageInfos;
    }

    public gbdfm.MarriageDTO getMarriageInfos(int i) {
        return this.marriageInfos[i];
    }

    public void setMarriageInfos(int i, gbdfm.MarriageDTO _value) {
        this.marriageInfos[i] = _value;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof FamilyInfoDTO)) return false;
        FamilyInfoDTO other = (FamilyInfoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.birthInfos==null && other.getBirthInfos()==null) ||
             (this.birthInfos!=null &&
              java.util.Arrays.equals(this.birthInfos, other.getBirthInfos()))) &&
            ((this.divorceInfos==null && other.getDivorceInfos()==null) ||
             (this.divorceInfos!=null &&
              java.util.Arrays.equals(this.divorceInfos, other.getDivorceInfos()))) &&
            ((this.marriageInfos==null && other.getMarriageInfos()==null) ||
             (this.marriageInfos!=null &&
              java.util.Arrays.equals(this.marriageInfos, other.getMarriageInfos())));
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
        if (getBirthInfos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBirthInfos());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getBirthInfos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDivorceInfos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDivorceInfos());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getDivorceInfos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMarriageInfos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMarriageInfos());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getMarriageInfos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FamilyInfoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "familyInfoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthInfos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthInfos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "birthDTO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("divorceInfos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "divorceInfos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "divorceDTO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marriageInfos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marriageInfos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "marriageDTO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
