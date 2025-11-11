/**
 * PersonDocuments.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;

public class PersonDocuments  implements java.io.Serializable {
    /* Список документов удостоверяющих личность, в том числе
     *                                             неактуальных */
    private gbdul.nat.interactive.persistence.document.Document[] document;

    public PersonDocuments() {
    }

    public PersonDocuments(
           gbdul.nat.interactive.persistence.document.Document[] document) {
           this.document = document;
    }


    /**
     * Gets the document value for this PersonDocuments.
     * 
     * @return document   * Список документов удостоверяющих личность, в том числе
     *                                             неактуальных
     */
    public gbdul.nat.interactive.persistence.document.Document[] getDocument() {
        return document;
    }


    /**
     * Sets the document value for this PersonDocuments.
     * 
     * @param document   * Список документов удостоверяющих личность, в том числе
     *                                             неактуальных
     */
    public void setDocument(gbdul.nat.interactive.persistence.document.Document[] document) {
        this.document = document;
    }

    public gbdul.nat.interactive.persistence.document.Document getDocument(int i) {
        return this.document[i];
    }

    public void setDocument(int i, gbdul.nat.interactive.persistence.document.Document _value) {
        this.document[i] = _value;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PersonDocuments)) return false;
        PersonDocuments other = (PersonDocuments) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.document==null && other.getDocument()==null) || 
             (this.document!=null &&
              java.util.Arrays.equals(this.document, other.getDocument())));
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
        if (getDocument() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocument());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getDocument(), i);
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
        new org.apache.axis.description.TypeDesc(PersonDocuments.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", ">Person>documents"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("document");
        elemField.setXmlName(new javax.xml.namespace.QName("", "document"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://document.persistence.interactive.nat", "Document"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
