/**
 * LiquidationType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.tamur.gbdul.egp.gbdulinfobybin;


/**
 * Тип, описывающий данные о ликвидации
 */
public class LiquidationType  implements java.io.Serializable {
    /* Основание ликвидации */
    private DirectoryType liqBase;

    /* Тип ликвидации */
    private DirectoryType liqType;

    /* Дата решения о ликвидации */
    private java.util.Date liqDecisionDate;

    /* Дата ликвидации, реорганизации */
    private java.util.Date liqDate;

    /* Вид реорганизации */
    private DirectoryType reorgType;

    /* БИН правоприемника */
    private String binReceiver;

    public LiquidationType() {
    }

    public LiquidationType(
           DirectoryType liqBase,
           DirectoryType liqType,
           java.util.Date liqDecisionDate,
           java.util.Date liqDate,
           DirectoryType reorgType,
           String binReceiver) {
           this.liqBase = liqBase;
           this.liqType = liqType;
           this.liqDecisionDate = liqDecisionDate;
           this.liqDate = liqDate;
           this.reorgType = reorgType;
           this.binReceiver = binReceiver;
    }


    /**
     * Gets the liqBase value for this LiquidationType.
     * 
     * @return liqBase   * Основание ликвидации
     */
    public DirectoryType getLiqBase() {
        return liqBase;
    }


    /**
     * Sets the liqBase value for this LiquidationType.
     * 
     * @param liqBase   * Основание ликвидации
     */
    public void setLiqBase(DirectoryType liqBase) {
        this.liqBase = liqBase;
    }


    /**
     * Gets the liqType value for this LiquidationType.
     * 
     * @return liqType   * Тип ликвидации
     */
    public DirectoryType getLiqType() {
        return liqType;
    }


    /**
     * Sets the liqType value for this LiquidationType.
     * 
     * @param liqType   * Тип ликвидации
     */
    public void setLiqType(DirectoryType liqType) {
        this.liqType = liqType;
    }


    /**
     * Gets the liqDecisionDate value for this LiquidationType.
     * 
     * @return liqDecisionDate   * Дата решения о ликвидации
     */
    public java.util.Date getLiqDecisionDate() {
        return liqDecisionDate;
    }


    /**
     * Sets the liqDecisionDate value for this LiquidationType.
     * 
     * @param liqDecisionDate   * Дата решения о ликвидации
     */
    public void setLiqDecisionDate(java.util.Date liqDecisionDate) {
        this.liqDecisionDate = liqDecisionDate;
    }


    /**
     * Gets the liqDate value for this LiquidationType.
     * 
     * @return liqDate   * Дата ликвидации, реорганизации
     */
    public java.util.Date getLiqDate() {
        return liqDate;
    }


    /**
     * Sets the liqDate value for this LiquidationType.
     * 
     * @param liqDate   * Дата ликвидации, реорганизации
     */
    public void setLiqDate(java.util.Date liqDate) {
        this.liqDate = liqDate;
    }


    /**
     * Gets the reorgType value for this LiquidationType.
     * 
     * @return reorgType   * Вид реорганизации
     */
    public DirectoryType getReorgType() {
        return reorgType;
    }


    /**
     * Sets the reorgType value for this LiquidationType.
     * 
     * @param reorgType   * Вид реорганизации
     */
    public void setReorgType(DirectoryType reorgType) {
        this.reorgType = reorgType;
    }


    /**
     * Gets the binReceiver value for this LiquidationType.
     * 
     * @return binReceiver   * БИН правоприемника
     */
    public String getBinReceiver() {
        return binReceiver;
    }


    /**
     * Sets the binReceiver value for this LiquidationType.
     * 
     * @param binReceiver   * БИН правоприемника
     */
    public void setBinReceiver(String binReceiver) {
        this.binReceiver = binReceiver;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof LiquidationType)) return false;
        LiquidationType other = (LiquidationType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.liqBase==null && other.getLiqBase()==null) || 
             (this.liqBase!=null &&
              this.liqBase.equals(other.getLiqBase()))) &&
            ((this.liqType==null && other.getLiqType()==null) || 
             (this.liqType!=null &&
              this.liqType.equals(other.getLiqType()))) &&
            ((this.liqDecisionDate==null && other.getLiqDecisionDate()==null) || 
             (this.liqDecisionDate!=null &&
              this.liqDecisionDate.equals(other.getLiqDecisionDate()))) &&
            ((this.liqDate==null && other.getLiqDate()==null) || 
             (this.liqDate!=null &&
              this.liqDate.equals(other.getLiqDate()))) &&
            ((this.reorgType==null && other.getReorgType()==null) || 
             (this.reorgType!=null &&
              this.reorgType.equals(other.getReorgType()))) &&
            ((this.binReceiver==null && other.getBinReceiver()==null) || 
             (this.binReceiver!=null &&
              this.binReceiver.equals(other.getBinReceiver())));
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
        if (getLiqBase() != null) {
            _hashCode += getLiqBase().hashCode();
        }
        if (getLiqType() != null) {
            _hashCode += getLiqType().hashCode();
        }
        if (getLiqDecisionDate() != null) {
            _hashCode += getLiqDecisionDate().hashCode();
        }
        if (getLiqDate() != null) {
            _hashCode += getLiqDate().hashCode();
        }
        if (getReorgType() != null) {
            _hashCode += getReorgType().hashCode();
        }
        if (getBinReceiver() != null) {
            _hashCode += getBinReceiver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LiquidationType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "LiquidationType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("liqBase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LiqBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("liqType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LiqType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("liqDecisionDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LiqDecisionDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("liqDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LiqDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reorgType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReorgType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binReceiver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BinReceiver"));
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
