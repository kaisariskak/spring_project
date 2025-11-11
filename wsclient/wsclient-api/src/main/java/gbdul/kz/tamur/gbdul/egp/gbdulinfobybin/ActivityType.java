/**
 * ActivityType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.tamur.gbdul.egp.gbdulinfobybin;


/**
 * Сведения о видах деятельности
 */
public class ActivityType  implements java.io.Serializable {
    /* Основной вид дейятельности? */
    private boolean main;

    /* код ОКЭД */
    private String OKED;

    /* Название вида деятельности на государственном */
    private String activityNameKz;

    /* Название вида деятельности на русском языке */
    private String activityNameRu;

    public ActivityType() {
    }

    public ActivityType(
           boolean main,
           String OKED,
           String activityNameKz,
           String activityNameRu) {
           this.main = main;
           this.OKED = OKED;
           this.activityNameKz = activityNameKz;
           this.activityNameRu = activityNameRu;
    }


    /**
     * Gets the main value for this ActivityType.
     * 
     * @return main   * Основной вид дейятельности?
     */
    public boolean isMain() {
        return main;
    }


    /**
     * Sets the main value for this ActivityType.
     * 
     * @param main   * Основной вид дейятельности?
     */
    public void setMain(boolean main) {
        this.main = main;
    }


    /**
     * Gets the OKED value for this ActivityType.
     * 
     * @return OKED   * код ОКЭД
     */
    public String getOKED() {
        return OKED;
    }


    /**
     * Sets the OKED value for this ActivityType.
     * 
     * @param OKED   * код ОКЭД
     */
    public void setOKED(String OKED) {
        this.OKED = OKED;
    }


    /**
     * Gets the activityNameKz value for this ActivityType.
     * 
     * @return activityNameKz   * Название вида деятельности на государственном
     */
    public String getActivityNameKz() {
        return activityNameKz;
    }


    /**
     * Sets the activityNameKz value for this ActivityType.
     * 
     * @param activityNameKz   * Название вида деятельности на государственном
     */
    public void setActivityNameKz(String activityNameKz) {
        this.activityNameKz = activityNameKz;
    }


    /**
     * Gets the activityNameRu value for this ActivityType.
     * 
     * @return activityNameRu   * Название вида деятельности на русском языке
     */
    public String getActivityNameRu() {
        return activityNameRu;
    }


    /**
     * Sets the activityNameRu value for this ActivityType.
     * 
     * @param activityNameRu   * Название вида деятельности на русском языке
     */
    public void setActivityNameRu(String activityNameRu) {
        this.activityNameRu = activityNameRu;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ActivityType)) return false;
        ActivityType other = (ActivityType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.main == other.isMain() &&
            ((this.OKED==null && other.getOKED()==null) || 
             (this.OKED!=null &&
              this.OKED.equals(other.getOKED()))) &&
            ((this.activityNameKz==null && other.getActivityNameKz()==null) || 
             (this.activityNameKz!=null &&
              this.activityNameKz.equals(other.getActivityNameKz()))) &&
            ((this.activityNameRu==null && other.getActivityNameRu()==null) || 
             (this.activityNameRu!=null &&
              this.activityNameRu.equals(other.getActivityNameRu())));
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
        _hashCode += (isMain() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOKED() != null) {
            _hashCode += getOKED().hashCode();
        }
        if (getActivityNameKz() != null) {
            _hashCode += getActivityNameKz().hashCode();
        }
        if (getActivityNameRu() != null) {
            _hashCode += getActivityNameRu().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActivityType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "ActivityType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("main");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Main"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OKED");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OKED"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityNameKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityNameKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityNameRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityNameRu"));
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
