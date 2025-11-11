/**
 * Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.message;


/**
 * Сообщение запроса на поиск физического лица. Поля messageId, messageDate,
 * senderCode обязательны для заполнения. Поддерживатеся 3 типа запроса:
 * поиск по ИИН, по ФИО и
 *                         дате рождения, по номеру документа удостоверяющего
 * личность (ДУЛ). Для поиска по ИИН следует
 *                         заполнить поле iin. Для поиска по ФИО и дате
 * рождения следует заполнить поля surname, firstname,
 *                         secondname, birthDate. Для этого критерия
 * поиска обязательными являются поле birthDate и одно из
 *                         surname или firstname. При этом, если не указано,
 * например, поле firstname, то будет
 *                         осуществляться поиск человека с указанной
 * в критерии поиска фамлилией и без имени, а не всех
 *                         физических лиц с такой фамилией, т.е., такой
 * критерий должен использоваться только для поиска
 *                         оралманов. Для поиска по номеру ДУЛ следует
 * заполнить поле documentNumber. При выборе
 *                         конкретного типа запроса следует заполнить
 * только поля критериев поиска, относящиеся именно к
 *                         этому типу, остальные же критерии оставить
 * пустыми.
 */
public class Request  implements java.io.Serializable {
    /* Уникальный иденитификатор сообщения. Размерность не более 36
     * символов
     *                                 латиницы. */
    private String messageId;

    /* Дата и время создания сообщения */
    private java.util.Calendar messageDate;

    /* Код участника взаимодействия - отправителя запроса. Выдается
     * Системе при
     *                                 согласовании Правил взаимодействия
     * с ИЧ ГБД ФЛ. */
    private String senderCode;

    /* ИИН */
    private String iin;

    /* БИН запрашивающего учреждения */
    private String requestorBIN;

    /* БИН */
    private String BIN;

    /* Фамилия */
    private String surname;

    /* Имя */
    private String name;

    /* Отчество */
    private String patronymic;

    /* Дата рождения */
    private java.util.Date birthDate;

    /* Номер документа удостоверяющего личность (кроме свидетельства
     * о
     *                                 рождении) */
    private String documentNumber;

    public Request() {
    }

    public Request(
           String messageId,
           java.util.Calendar messageDate,
           String senderCode,
           String iin,
           String requestorBIN,
           String BIN,
           String surname,
           String name,
           String patronymic,
           java.util.Date birthDate,
           String documentNumber) {
           this.messageId = messageId;
           this.messageDate = messageDate;
           this.senderCode = senderCode;
           this.iin = iin;
           this.requestorBIN = requestorBIN;
           this.BIN = BIN;
           this.surname = surname;
           this.name = name;
           this.patronymic = patronymic;
           this.birthDate = birthDate;
           this.documentNumber = documentNumber;
    }


    /**
     * Gets the messageId value for this Request.
     * 
     * @return messageId   * Уникальный иденитификатор сообщения. Размерность не более 36
     * символов
     *                                 латиницы.
     */
    public String getMessageId() {
        return messageId;
    }


    /**
     * Sets the messageId value for this Request.
     * 
     * @param messageId   * Уникальный иденитификатор сообщения. Размерность не более 36
     * символов
     *                                 латиницы.
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


    /**
     * Gets the messageDate value for this Request.
     * 
     * @return messageDate   * Дата и время создания сообщения
     */
    public java.util.Calendar getMessageDate() {
        return messageDate;
    }


    /**
     * Sets the messageDate value for this Request.
     * 
     * @param messageDate   * Дата и время создания сообщения
     */
    public void setMessageDate(java.util.Calendar messageDate) {
        this.messageDate = messageDate;
    }


    /**
     * Gets the senderCode value for this Request.
     * 
     * @return senderCode   * Код участника взаимодействия - отправителя запроса. Выдается
     * Системе при
     *                                 согласовании Правил взаимодействия
     * с ИЧ ГБД ФЛ.
     */
    public String getSenderCode() {
        return senderCode;
    }


    /**
     * Sets the senderCode value for this Request.
     * 
     * @param senderCode   * Код участника взаимодействия - отправителя запроса. Выдается
     * Системе при
     *                                 согласовании Правил взаимодействия
     * с ИЧ ГБД ФЛ.
     */
    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }


    /**
     * Gets the iin value for this Request.
     * 
     * @return iin   * ИИН
     */
    public String getIin() {
        return iin;
    }


    /**
     * Sets the iin value for this Request.
     * 
     * @param iin   * ИИН
     */
    public void setIin(String iin) {
        this.iin = iin;
    }


    /**
     * Gets the requestorBIN value for this Request.
     * 
     * @return requestorBIN   * БИН запрашивающего учреждения
     */
    public String getRequestorBIN() {
        return requestorBIN;
    }


    /**
     * Sets the requestorBIN value for this Request.
     * 
     * @param requestorBIN   * БИН запрашивающего учреждения
     */
    public void setRequestorBIN(String requestorBIN) {
        this.requestorBIN = requestorBIN;
    }


    /**
     * Gets the BIN value for this Request.
     * 
     * @return BIN   * БИН
     */
    public String getBIN() {
        return BIN;
    }


    /**
     * Sets the BIN value for this Request.
     * 
     * @param BIN   * БИН
     */
    public void setBIN(String BIN) {
        this.BIN = BIN;
    }


    /**
     * Gets the surname value for this Request.
     * 
     * @return surname   * Фамилия
     */
    public String getSurname() {
        return surname;
    }


    /**
     * Sets the surname value for this Request.
     * 
     * @param surname   * Фамилия
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }


    /**
     * Gets the name value for this Request.
     * 
     * @return name   * Имя
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Request.
     * 
     * @param name   * Имя
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the patronymic value for this Request.
     * 
     * @return patronymic   * Отчество
     */
    public String getPatronymic() {
        return patronymic;
    }


    /**
     * Sets the patronymic value for this Request.
     * 
     * @param patronymic   * Отчество
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }


    /**
     * Gets the birthDate value for this Request.
     * 
     * @return birthDate   * Дата рождения
     */
    public java.util.Date getBirthDate() {
        return birthDate;
    }


    /**
     * Sets the birthDate value for this Request.
     * 
     * @param birthDate   * Дата рождения
     */
    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * Gets the documentNumber value for this Request.
     * 
     * @return documentNumber   * Номер документа удостоверяющего личность (кроме свидетельства
     * о
     *                                 рождении)
     */
    public String getDocumentNumber() {
        return documentNumber;
    }


    /**
     * Sets the documentNumber value for this Request.
     * 
     * @param documentNumber   * Номер документа удостоверяющего личность (кроме свидетельства
     * о
     *                                 рождении)
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Request)) return false;
        Request other = (Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.messageId==null && other.getMessageId()==null) || 
             (this.messageId!=null &&
              this.messageId.equals(other.getMessageId()))) &&
            ((this.messageDate==null && other.getMessageDate()==null) || 
             (this.messageDate!=null &&
              this.messageDate.equals(other.getMessageDate()))) &&
            ((this.senderCode==null && other.getSenderCode()==null) || 
             (this.senderCode!=null &&
              this.senderCode.equals(other.getSenderCode()))) &&
            ((this.iin==null && other.getIin()==null) || 
             (this.iin!=null &&
              this.iin.equals(other.getIin()))) &&
            ((this.requestorBIN==null && other.getRequestorBIN()==null) || 
             (this.requestorBIN!=null &&
              this.requestorBIN.equals(other.getRequestorBIN()))) &&
            ((this.BIN==null && other.getBIN()==null) || 
             (this.BIN!=null &&
              this.BIN.equals(other.getBIN()))) &&
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
              this.birthDate.equals(other.getBirthDate()))) &&
            ((this.documentNumber==null && other.getDocumentNumber()==null) || 
             (this.documentNumber!=null &&
              this.documentNumber.equals(other.getDocumentNumber())));
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
        if (getMessageId() != null) {
            _hashCode += getMessageId().hashCode();
        }
        if (getMessageDate() != null) {
            _hashCode += getMessageDate().hashCode();
        }
        if (getSenderCode() != null) {
            _hashCode += getSenderCode().hashCode();
        }
        if (getIin() != null) {
            _hashCode += getIin().hashCode();
        }
        if (getRequestorBIN() != null) {
            _hashCode += getRequestorBIN().hashCode();
        }
        if (getBIN() != null) {
            _hashCode += getBIN().hashCode();
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
        if (getDocumentNumber() != null) {
            _hashCode += getDocumentNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://message.persistence.interactive.nat", "Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "messageDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestorBIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestorBIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentNumber"));
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
