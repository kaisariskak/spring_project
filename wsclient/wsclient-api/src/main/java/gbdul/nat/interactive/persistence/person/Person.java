/**
 * Person.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.nat.interactive.persistence.person;


/**
 * Сведения о физическом лице
 */
public class Person  implements java.io.Serializable {
    /* ИИН */
    private String iin;

    /* Фамилия */
    private String surname;

    /* Имя */
    private String name;

    /* Отчество */
    private String patronymic;

    /* Дата рождения */
    private java.util.Date birthDate;

    /* Дата смерти */
    private java.util.Date deathDate;

    /* Пол */
    private gbdul.nat.interactive.persistence.dictionaries.Gender gender;

    /* Национальность */
    private gbdul.nat.interactive.persistence.dictionaries.Nationality nationality;

    /* Гражданство */
    private gbdul.nat.interactive.persistence.dictionaries.Country citizenship;

    /* Статус физического лица (живой/умерший) */
    private gbdul.nat.interactive.persistence.dictionaries.PersonStatus lifeStatus;

    /* Данные свидетельства о рождении */
    private gbdul.nat.interactive.persistence.person.Certificate birthCertificate;

    /* Данные свидетельства о смерти */
    private gbdul.nat.interactive.persistence.person.Certificate deathCertificate;

    /* Место рождения */
    private gbdul.nat.interactive.persistence.person.BirthPlace birthPlace;

    /* Адрес прописки. Может отсутствовать, например, у детей */
    private gbdul.nat.interactive.persistence.person.RegAddress regAddress;

    /* Сведения о дееспособности физического лица */
    private gbdul.nat.interactive.persistence.person.PersonCapableStatus personCapableStatus;

    /* Статус физического лица 'Пропавший без вести' */
    private gbdul.nat.interactive.persistence.person.MissingStatus missingStatus;

    /* Физическое лицо скрывается от дознания, следствия, суда и отбытия
     * наказания */
    private gbdul.nat.interactive.persistence.person.DisappearStatus disappearStatus;

    /* Признак исключения ИИН физического лица */
    private gbdul.nat.interactive.persistence.person.PersonExcludeStatus excludeStatus;

    /* Статус оралмана */
    private gbdul.nat.interactive.persistence.person.RepatriationStatus repatriationStatus;

    private gbdul.nat.interactive.persistence.person.PersonDocuments documents;

    private gbdul.nat.interactive.persistence.person.PersonAddresses addresses;

    /* Признак удаления записи о физическом лице из Национального
     * реестра */
    private boolean removed;

    public Person() {
    }

    public Person(
           String iin,
           String surname,
           String name,
           String patronymic,
           java.util.Date birthDate,
           java.util.Date deathDate,
           gbdul.nat.interactive.persistence.dictionaries.Gender gender,
           gbdul.nat.interactive.persistence.dictionaries.Nationality nationality,
           gbdul.nat.interactive.persistence.dictionaries.Country citizenship,
           gbdul.nat.interactive.persistence.dictionaries.PersonStatus lifeStatus,
           gbdul.nat.interactive.persistence.person.Certificate birthCertificate,
           gbdul.nat.interactive.persistence.person.Certificate deathCertificate,
           gbdul.nat.interactive.persistence.person.BirthPlace birthPlace,
           gbdul.nat.interactive.persistence.person.RegAddress regAddress,
           gbdul.nat.interactive.persistence.person.PersonCapableStatus personCapableStatus,
           gbdul.nat.interactive.persistence.person.MissingStatus missingStatus,
           gbdul.nat.interactive.persistence.person.DisappearStatus disappearStatus,
           gbdul.nat.interactive.persistence.person.PersonExcludeStatus excludeStatus,
           gbdul.nat.interactive.persistence.person.RepatriationStatus repatriationStatus,
           gbdul.nat.interactive.persistence.person.PersonDocuments documents,
           gbdul.nat.interactive.persistence.person.PersonAddresses addresses,
           boolean removed) {
           this.iin = iin;
           this.surname = surname;
           this.name = name;
           this.patronymic = patronymic;
           this.birthDate = birthDate;
           this.deathDate = deathDate;
           this.gender = gender;
           this.nationality = nationality;
           this.citizenship = citizenship;
           this.lifeStatus = lifeStatus;
           this.birthCertificate = birthCertificate;
           this.deathCertificate = deathCertificate;
           this.birthPlace = birthPlace;
           this.regAddress = regAddress;
           this.personCapableStatus = personCapableStatus;
           this.missingStatus = missingStatus;
           this.disappearStatus = disappearStatus;
           this.excludeStatus = excludeStatus;
           this.repatriationStatus = repatriationStatus;
           this.documents = documents;
           this.addresses = addresses;
           this.removed = removed;
    }


    /**
     * Gets the iin value for this Person.
     * 
     * @return iin   * ИИН
     */
    public String getIin() {
        return iin;
    }


    /**
     * Sets the iin value for this Person.
     * 
     * @param iin   * ИИН
     */
    public void setIin(String iin) {
        this.iin = iin;
    }


    /**
     * Gets the surname value for this Person.
     * 
     * @return surname   * Фамилия
     */
    public String getSurname() {
        return surname;
    }


    /**
     * Sets the surname value for this Person.
     * 
     * @param surname   * Фамилия
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }


    /**
     * Gets the name value for this Person.
     * 
     * @return name   * Имя
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this Person.
     * 
     * @param name   * Имя
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the patronymic value for this Person.
     * 
     * @return patronymic   * Отчество
     */
    public String getPatronymic() {
        return patronymic;
    }


    /**
     * Sets the patronymic value for this Person.
     * 
     * @param patronymic   * Отчество
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }


    /**
     * Gets the birthDate value for this Person.
     * 
     * @return birthDate   * Дата рождения
     */
    public java.util.Date getBirthDate() {
        return birthDate;
    }


    /**
     * Sets the birthDate value for this Person.
     * 
     * @param birthDate   * Дата рождения
     */
    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * Gets the deathDate value for this Person.
     * 
     * @return deathDate   * Дата смерти
     */
    public java.util.Date getDeathDate() {
        return deathDate;
    }


    /**
     * Sets the deathDate value for this Person.
     * 
     * @param deathDate   * Дата смерти
     */
    public void setDeathDate(java.util.Date deathDate) {
        this.deathDate = deathDate;
    }


    /**
     * Gets the gender value for this Person.
     * 
     * @return gender   * Пол
     */
    public gbdul.nat.interactive.persistence.dictionaries.Gender getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this Person.
     * 
     * @param gender   * Пол
     */
    public void setGender(gbdul.nat.interactive.persistence.dictionaries.Gender gender) {
        this.gender = gender;
    }


    /**
     * Gets the nationality value for this Person.
     * 
     * @return nationality   * Национальность
     */
    public gbdul.nat.interactive.persistence.dictionaries.Nationality getNationality() {
        return nationality;
    }


    /**
     * Sets the nationality value for this Person.
     * 
     * @param nationality   * Национальность
     */
    public void setNationality(gbdul.nat.interactive.persistence.dictionaries.Nationality nationality) {
        this.nationality = nationality;
    }


    /**
     * Gets the citizenship value for this Person.
     * 
     * @return citizenship   * Гражданство
     */
    public gbdul.nat.interactive.persistence.dictionaries.Country getCitizenship() {
        return citizenship;
    }


    /**
     * Sets the citizenship value for this Person.
     * 
     * @param citizenship   * Гражданство
     */
    public void setCitizenship(gbdul.nat.interactive.persistence.dictionaries.Country citizenship) {
        this.citizenship = citizenship;
    }


    /**
     * Gets the lifeStatus value for this Person.
     * 
     * @return lifeStatus   * Статус физического лица (живой/умерший)
     */
    public gbdul.nat.interactive.persistence.dictionaries.PersonStatus getLifeStatus() {
        return lifeStatus;
    }


    /**
     * Sets the lifeStatus value for this Person.
     * 
     * @param lifeStatus   * Статус физического лица (живой/умерший)
     */
    public void setLifeStatus(gbdul.nat.interactive.persistence.dictionaries.PersonStatus lifeStatus) {
        this.lifeStatus = lifeStatus;
    }


    /**
     * Gets the birthCertificate value for this Person.
     * 
     * @return birthCertificate   * Данные свидетельства о рождении
     */
    public gbdul.nat.interactive.persistence.person.Certificate getBirthCertificate() {
        return birthCertificate;
    }


    /**
     * Sets the birthCertificate value for this Person.
     * 
     * @param birthCertificate   * Данные свидетельства о рождении
     */
    public void setBirthCertificate(gbdul.nat.interactive.persistence.person.Certificate birthCertificate) {
        this.birthCertificate = birthCertificate;
    }


    /**
     * Gets the deathCertificate value for this Person.
     * 
     * @return deathCertificate   * Данные свидетельства о смерти
     */
    public gbdul.nat.interactive.persistence.person.Certificate getDeathCertificate() {
        return deathCertificate;
    }


    /**
     * Sets the deathCertificate value for this Person.
     * 
     * @param deathCertificate   * Данные свидетельства о смерти
     */
    public void setDeathCertificate(gbdul.nat.interactive.persistence.person.Certificate deathCertificate) {
        this.deathCertificate = deathCertificate;
    }


    /**
     * Gets the birthPlace value for this Person.
     * 
     * @return birthPlace   * Место рождения
     */
    public gbdul.nat.interactive.persistence.person.BirthPlace getBirthPlace() {
        return birthPlace;
    }


    /**
     * Sets the birthPlace value for this Person.
     * 
     * @param birthPlace   * Место рождения
     */
    public void setBirthPlace(gbdul.nat.interactive.persistence.person.BirthPlace birthPlace) {
        this.birthPlace = birthPlace;
    }


    /**
     * Gets the regAddress value for this Person.
     * 
     * @return regAddress   * Адрес прописки. Может отсутствовать, например, у детей
     */
    public gbdul.nat.interactive.persistence.person.RegAddress getRegAddress() {
        return regAddress;
    }


    /**
     * Sets the regAddress value for this Person.
     * 
     * @param regAddress   * Адрес прописки. Может отсутствовать, например, у детей
     */
    public void setRegAddress(gbdul.nat.interactive.persistence.person.RegAddress regAddress) {
        this.regAddress = regAddress;
    }


    /**
     * Gets the personCapableStatus value for this Person.
     * 
     * @return personCapableStatus   * Сведения о дееспособности физического лица
     */
    public gbdul.nat.interactive.persistence.person.PersonCapableStatus getPersonCapableStatus() {
        return personCapableStatus;
    }


    /**
     * Sets the personCapableStatus value for this Person.
     * 
     * @param personCapableStatus   * Сведения о дееспособности физического лица
     */
    public void setPersonCapableStatus(gbdul.nat.interactive.persistence.person.PersonCapableStatus personCapableStatus) {
        this.personCapableStatus = personCapableStatus;
    }


    /**
     * Gets the missingStatus value for this Person.
     * 
     * @return missingStatus   * Статус физического лица 'Пропавший без вести'
     */
    public gbdul.nat.interactive.persistence.person.MissingStatus getMissingStatus() {
        return missingStatus;
    }


    /**
     * Sets the missingStatus value for this Person.
     * 
     * @param missingStatus   * Статус физического лица 'Пропавший без вести'
     */
    public void setMissingStatus(gbdul.nat.interactive.persistence.person.MissingStatus missingStatus) {
        this.missingStatus = missingStatus;
    }


    /**
     * Gets the disappearStatus value for this Person.
     * 
     * @return disappearStatus   * Физическое лицо скрывается от дознания, следствия, суда и отбытия
     * наказания
     */
    public gbdul.nat.interactive.persistence.person.DisappearStatus getDisappearStatus() {
        return disappearStatus;
    }


    /**
     * Sets the disappearStatus value for this Person.
     * 
     * @param disappearStatus   * Физическое лицо скрывается от дознания, следствия, суда и отбытия
     * наказания
     */
    public void setDisappearStatus(gbdul.nat.interactive.persistence.person.DisappearStatus disappearStatus) {
        this.disappearStatus = disappearStatus;
    }


    /**
     * Gets the excludeStatus value for this Person.
     * 
     * @return excludeStatus   * Признак исключения ИИН физического лица
     */
    public gbdul.nat.interactive.persistence.person.PersonExcludeStatus getExcludeStatus() {
        return excludeStatus;
    }


    /**
     * Sets the excludeStatus value for this Person.
     * 
     * @param excludeStatus   * Признак исключения ИИН физического лица
     */
    public void setExcludeStatus(gbdul.nat.interactive.persistence.person.PersonExcludeStatus excludeStatus) {
        this.excludeStatus = excludeStatus;
    }


    /**
     * Gets the repatriationStatus value for this Person.
     * 
     * @return repatriationStatus   * Статус оралмана
     */
    public gbdul.nat.interactive.persistence.person.RepatriationStatus getRepatriationStatus() {
        return repatriationStatus;
    }


    /**
     * Sets the repatriationStatus value for this Person.
     * 
     * @param repatriationStatus   * Статус оралмана
     */
    public void setRepatriationStatus(gbdul.nat.interactive.persistence.person.RepatriationStatus repatriationStatus) {
        this.repatriationStatus = repatriationStatus;
    }


    /**
     * Gets the documents value for this Person.
     * 
     * @return documents
     */
    public gbdul.nat.interactive.persistence.person.PersonDocuments getDocuments() {
        return documents;
    }


    /**
     * Sets the documents value for this Person.
     * 
     * @param documents
     */
    public void setDocuments(gbdul.nat.interactive.persistence.person.PersonDocuments documents) {
        this.documents = documents;
    }


    /**
     * Gets the addresses value for this Person.
     * 
     * @return addresses
     */
    public gbdul.nat.interactive.persistence.person.PersonAddresses getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this Person.
     * 
     * @param addresses
     */
    public void setAddresses(gbdul.nat.interactive.persistence.person.PersonAddresses addresses) {
        this.addresses = addresses;
    }


    /**
     * Gets the removed value for this Person.
     * 
     * @return removed   * Признак удаления записи о физическом лице из Национального
     * реестра
     */
    public boolean isRemoved() {
        return removed;
    }


    /**
     * Sets the removed value for this Person.
     * 
     * @param removed   * Признак удаления записи о физическом лице из Национального
     * реестра
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.iin==null && other.getIin()==null) || 
             (this.iin!=null &&
              this.iin.equals(other.getIin()))) &&
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
            ((this.deathDate==null && other.getDeathDate()==null) || 
             (this.deathDate!=null &&
              this.deathDate.equals(other.getDeathDate()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            ((this.nationality==null && other.getNationality()==null) || 
             (this.nationality!=null &&
              this.nationality.equals(other.getNationality()))) &&
            ((this.citizenship==null && other.getCitizenship()==null) || 
             (this.citizenship!=null &&
              this.citizenship.equals(other.getCitizenship()))) &&
            ((this.lifeStatus==null && other.getLifeStatus()==null) || 
             (this.lifeStatus!=null &&
              this.lifeStatus.equals(other.getLifeStatus()))) &&
            ((this.birthCertificate==null && other.getBirthCertificate()==null) || 
             (this.birthCertificate!=null &&
              this.birthCertificate.equals(other.getBirthCertificate()))) &&
            ((this.deathCertificate==null && other.getDeathCertificate()==null) || 
             (this.deathCertificate!=null &&
              this.deathCertificate.equals(other.getDeathCertificate()))) &&
            ((this.birthPlace==null && other.getBirthPlace()==null) || 
             (this.birthPlace!=null &&
              this.birthPlace.equals(other.getBirthPlace()))) &&
            ((this.regAddress==null && other.getRegAddress()==null) || 
             (this.regAddress!=null &&
              this.regAddress.equals(other.getRegAddress()))) &&
            ((this.personCapableStatus==null && other.getPersonCapableStatus()==null) || 
             (this.personCapableStatus!=null &&
              this.personCapableStatus.equals(other.getPersonCapableStatus()))) &&
            ((this.missingStatus==null && other.getMissingStatus()==null) || 
             (this.missingStatus!=null &&
              this.missingStatus.equals(other.getMissingStatus()))) &&
            ((this.disappearStatus==null && other.getDisappearStatus()==null) || 
             (this.disappearStatus!=null &&
              this.disappearStatus.equals(other.getDisappearStatus()))) &&
            ((this.excludeStatus==null && other.getExcludeStatus()==null) || 
             (this.excludeStatus!=null &&
              this.excludeStatus.equals(other.getExcludeStatus()))) &&
            ((this.repatriationStatus==null && other.getRepatriationStatus()==null) || 
             (this.repatriationStatus!=null &&
              this.repatriationStatus.equals(other.getRepatriationStatus()))) &&
            ((this.documents==null && other.getDocuments()==null) || 
             (this.documents!=null &&
              this.documents.equals(other.getDocuments()))) &&
            ((this.addresses==null && other.getAddresses()==null) || 
             (this.addresses!=null &&
              this.addresses.equals(other.getAddresses()))) &&
            this.removed == other.isRemoved();
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
        if (getIin() != null) {
            _hashCode += getIin().hashCode();
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
        if (getDeathDate() != null) {
            _hashCode += getDeathDate().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        if (getNationality() != null) {
            _hashCode += getNationality().hashCode();
        }
        if (getCitizenship() != null) {
            _hashCode += getCitizenship().hashCode();
        }
        if (getLifeStatus() != null) {
            _hashCode += getLifeStatus().hashCode();
        }
        if (getBirthCertificate() != null) {
            _hashCode += getBirthCertificate().hashCode();
        }
        if (getDeathCertificate() != null) {
            _hashCode += getDeathCertificate().hashCode();
        }
        if (getBirthPlace() != null) {
            _hashCode += getBirthPlace().hashCode();
        }
        if (getRegAddress() != null) {
            _hashCode += getRegAddress().hashCode();
        }
        if (getPersonCapableStatus() != null) {
            _hashCode += getPersonCapableStatus().hashCode();
        }
        if (getMissingStatus() != null) {
            _hashCode += getMissingStatus().hashCode();
        }
        if (getDisappearStatus() != null) {
            _hashCode += getDisappearStatus().hashCode();
        }
        if (getExcludeStatus() != null) {
            _hashCode += getExcludeStatus().hashCode();
        }
        if (getRepatriationStatus() != null) {
            _hashCode += getRepatriationStatus().hashCode();
        }
        if (getDocuments() != null) {
            _hashCode += getDocuments().hashCode();
        }
        if (getAddresses() != null) {
            _hashCode += getAddresses().hashCode();
        }
        _hashCode += (isRemoved() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Person.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "Person"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deathDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deathDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Gender"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nationality");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nationality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Nationality"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("citizenship");
        elemField.setXmlName(new javax.xml.namespace.QName("", "citizenship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Country"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lifeStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lifeStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "PersonStatus"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthCertificate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthCertificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "Certificate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deathCertificate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deathCertificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "Certificate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthPlace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthPlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "BirthPlace"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "regAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "RegAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personCapableStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "personCapableStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "PersonCapableStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "missingStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "MissingStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disappearStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disappearStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "DisappearStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludeStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excludeStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "PersonExcludeStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repatriationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repatriationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "RepatriationStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documents");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documents"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", ">Person>documents"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://person.persistence.interactive.nat", ">Person>addresses"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("removed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "removed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
