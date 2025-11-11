/**
 * Document.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.document;


/**
 * Документ, удостоверяющий личность
 */
public class Document  implements java.io.Serializable {
    /* Тип документа (УДЛ, Паспорт, и т.д.) */
    private gbdul.nat.interactive.persistence.dictionaries.DocumentType type;

    /* Номер документа */
    private String number;

    /* Серия документа */
    private String series;

    /* Дата выдачи */
    private java.util.Date beginDate;

    /* Срок действия */
    private java.util.Date endDate;

    /* Организация, выдавшая документ */
    private gbdul.nat.interactive.persistence.dictionaries.DocumentOrganization issueOrganization;

    /* Признак действительности документа */
    private gbdul.nat.interactive.persistence.dictionaries.DocumentInvalidity status;

    /* Дата недействительности документа. Указывается в случаях, когда
     * ДУЛ
     *                                 признан недействительным до наступления
     * даты окончания срока его действия */
    private java.util.Date invalidityDate;

    /* Фамилия физического лица на момент выдачи документа */
    private String surname;

    /* Имя физического лица на момент выдачи документа */
    private String name;

    /* Отчество физического лица на момент выдачи документа */
    private String patronymic;

    /* Дата рождения физического лица */
    private java.util.Date birthDate;

    public Document() {
    }

    public Document(
           gbdul.nat.interactive.persistence.dictionaries.DocumentType type,
           String number,
           String series,
           java.util.Date beginDate,
           java.util.Date endDate,
           gbdul.nat.interactive.persistence.dictionaries.DocumentOrganization issueOrganization,
           gbdul.nat.interactive.persistence.dictionaries.DocumentInvalidity status,
           java.util.Date invalidityDate,
           String surname,
           String name,
           String patronymic,
           java.util.Date birthDate) {
           this.type = type;
           this.number = number;
           this.series = series;
           this.beginDate = beginDate;
           this.endDate = endDate;
           this.issueOrganization = issueOrganization;
           this.status = status;
           this.invalidityDate = invalidityDate;
           this.surname = surname;
           this.name = name;
           this.patronymic = patronymic;
           this.birthDate = birthDate;
    }


    /**
     * Gets the type value for this Document.
     * 
     * @return type   * Тип документа (УДЛ, Паспорт, и т.д.)
     */
    public gbdul.nat.interactive.persistence.dictionaries.DocumentType getType() {
        return type;
    }


    /**
     * Sets the type value for this Document.
     * 
     * @param type   * Тип документа (УДЛ, Паспорт, и т.д.)
     */
    public void setType(gbdul.nat.interactive.persistence.dictionaries.DocumentType type) {
        this.type = type;
    }


    /**
     * Gets the number value for this Document.
     * 
     * @return number   * Номер документа
     */
    public String getNumber() {
        return number;
    }


    /**
     * Sets the number value for this Document.
     * 
     * @param number   * Номер документа
     */
    public void setNumber(String number) {
        this.number = number;
    }


    /**
     * Gets the series value for this Document.
     * 
     * @return series   * Серия документа
     */
    public String getSeries() {
        return series;
    }


    /**
     * Sets the series value for this Document.
     * 
     * @param series   * Серия документа
     */
    public void setSeries(String series) {
        this.series = series;
    }


    /**
     * Gets the beginDate value for this Document.
     * 
     * @return beginDate   * Дата выдачи
     */
    public java.util.Date getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this Document.
     * 
     * @param beginDate   * Дата выдачи
     */
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the endDate value for this Document.
     * 
     * @return endDate   * Срок действия
     */
    public java.util.Date getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this Document.
     * 
     * @param endDate   * Срок действия
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the issueOrganization value for this Document.
     * 
     * @return issueOrganization   * Организация, выдавшая документ
     */
    public gbdul.nat.interactive.persistence.dictionaries.DocumentOrganization getIssueOrganization() {
        return issueOrganization;
    }


    /**
     * Sets the issueOrganization value for this Document.
     * 
     * @param issueOrganization   * Организация, выдавшая документ
     */
    public void setIssueOrganization(gbdul.nat.interactive.persistence.dictionaries.DocumentOrganization issueOrganization) {
        this.issueOrganization = issueOrganization;
    }


    /**
     * Gets the status value for this Document.
     * 
     * @return status   * Признак действительности документа
     */
    public gbdul.nat.interactive.persistence.dictionaries.DocumentInvalidity getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Document.
     * 
     * @param status   * Признак действительности документа
     */
    public void setStatus(gbdul.nat.interactive.persistence.dictionaries.DocumentInvalidity status) {
        this.status = status;
    }


    /**
     * Gets the invalidityDate value for this Document.
     * 
     * @return invalidityDate   * Дата недействительности документа. Указывается в случаях, когда
     * ДУЛ
     *                                 признан недействительным до наступления
     * даты окончания срока его действия
     */
    public java.util.Date getInvalidityDate() {
        return invalidityDate;
    }


    /**
     * Sets the invalidityDate value for this Document.
     * 
     * @param invalidityDate   * Дата недействительности документа. Указывается в случаях, когда
     * ДУЛ
     *                                 признан недействительным до наступления
     * даты окончания срока его действия
     */
    public void setInvalidityDate(java.util.Date invalidityDate) {
        this.invalidityDate = invalidityDate;
    }


    /**
     * Gets the surname value for this Document.
     * 
     * @return surname   * Фамилия физического лица на момент выдачи документа
     */
    public String getSurname() {
        return surname;
    }


    /**
     * Sets the surname value for this Document.
     * 
     * @param surname   * Фамилия физического лица на момент выдачи документа
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }


    /**
     * Gets the name value for this Document.
     * 
     * @return name   * Имя физического лица на момент выдачи документа
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Document.
     * 
     * @param name   * Имя физического лица на момент выдачи документа
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the patronymic value for this Document.
     * 
     * @return patronymic   * Отчество физического лица на момент выдачи документа
     */
    public String getPatronymic() {
        return patronymic;
    }


    /**
     * Sets the patronymic value for this Document.
     * 
     * @param patronymic   * Отчество физического лица на момент выдачи документа
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }


    /**
     * Gets the birthDate value for this Document.
     * 
     * @return birthDate   * Дата рождения физического лица
     */
    public java.util.Date getBirthDate() {
        return birthDate;
    }


    /**
     * Sets the birthDate value for this Document.
     * 
     * @param birthDate   * Дата рождения физического лица
     */
    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = birthDate;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Document)) return false;
        Document other = (Document) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.series==null && other.getSeries()==null) || 
             (this.series!=null &&
              this.series.equals(other.getSeries()))) &&
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.issueOrganization==null && other.getIssueOrganization()==null) || 
             (this.issueOrganization!=null &&
              this.issueOrganization.equals(other.getIssueOrganization()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.invalidityDate==null && other.getInvalidityDate()==null) || 
             (this.invalidityDate!=null &&
              this.invalidityDate.equals(other.getInvalidityDate()))) &&
            ((this.surname==null && other.getSurname()==null) || 
             (this.surname!=null &&
              this.surname.equals(other.getSurname()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.patronymic==null && other.getPatronymic()==null) || 
             (this.patronymic!=null &&
              this.patronymic.equals(other.getPatronymic()))) &&
            ((this.birthDate==null && other.getBirthDate()==null) || 
             (this.birthDate!=null &&
              this.birthDate.equals(other.getBirthDate())));
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
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getSeries() != null) {
            _hashCode += getSeries().hashCode();
        }
        if (getBeginDate() != null) {
            _hashCode += getBeginDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getIssueOrganization() != null) {
            _hashCode += getIssueOrganization().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getInvalidityDate() != null) {
            _hashCode += getInvalidityDate().hashCode();
        }
        if (getSurname() != null) {
            _hashCode += getSurname().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPatronymic() != null) {
            _hashCode += getPatronymic().hashCode();
        }
        if (getBirthDate() != null) {
            _hashCode += getBirthDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Document.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://document.persistence.interactive.nat", "Document"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "DocumentType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("series");
        elemField.setXmlName(new javax.xml.namespace.QName("", "series"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueOrganization");
        elemField.setXmlName(new javax.xml.namespace.QName("", "issueOrganization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "DocumentOrganization"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "DocumentInvalidity"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invalidityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "invalidityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patronymic");
        elemField.setXmlName(new javax.xml.namespace.QName("", "patronymic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
