/**
 * OrganizationType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.tamur.gbdul.egp.gbdulinfobybin;


/**
 * Основной тип, описывающий сведения об организации
 */
public class OrganizationType  implements java.io.Serializable {
    /* БИН организации */
    private String BIN;

    /* Статус юрлица, представительства */
    private DirectoryType regStatus;

    /* Орган юстиции, зарегистрировавший организацию */
    private DirectoryType registrationDepartment;

    /* Способ образования */
    private DirectoryType creationMethod;

    /* Дата первичной регистрации */
    private java.util.Calendar registrationDate;

    /* Дата последней перерегистрации */
    private java.util.Calendar registrationLastDate;

    /* Полное наименование на русском языке */
    private String fullNameRu;

    /* Полное наименование на государственном языке */
    private String fullNameKz;

    /* Полное наименование на английском языке */
    private String fullNameEn;

    /* Сокращенное наименование на русском языке */
    private String shortNameRu;

    /* Сокращенное наименование на государственном языке */
    private String shortNameKz;

    /* Сокращенное наименование на английском языке */
    private String shortNameEn;

    /* Форма организации */
    private DirectoryType orgForm;

    /* ОПФ */
    private DirectoryType formOfLaw;

    /* Тип фонда */
    private DirectoryType fundType;

    /* Форма собственности */
    private DirectoryType propertyType;

    /* 1 - коммерческая организация */
    private Boolean commerceOrg;

    /* 1 - субъект предпринимательства */
    private Boolean enterpriseSubject;

    /* Тип частного предпринимательства */
    private DirectoryType privateEnterpriseType;

    /* 1 - дочерняя организация */
    private Boolean affiliated;

    /* 1 - международная организация; */
    private Boolean international;

    /* 1 - с участием иностранных инвесторов; */
    private Boolean foreignInvest;

    /* 1-Участие лиц без гражданства */
    private Boolean oneCitizenShip;

    /* 1 - деятельность по типовому уставу; */
    private Boolean typicalCharter;

    /* 1-наличие филиалов и представительств */
    private Boolean branchesExistence;

    /* Сведения о ликвидации */
    private LiquidationType liquidation;

    /* Руководитель организации */
    private PersonType organizationLeader;

    /* Виды деятельности */
    private ActivityType[] activity;

    /* Адрес местонахождения */
    private AddressType address;

    /* Участники, учредители - юрлица */
    private gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType[] foundersUL;

    /* Участники, учредители - физлица */
    private PersonType[] foundersFL;

    /* Количество участников, учредителей */
    private Integer foundersCount;

    /* Сведения о головной организации филиала или представительства */
    private gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType headerOrganization;

    public OrganizationType() {
    }

    public OrganizationType(
           String BIN,
           DirectoryType regStatus,
           DirectoryType registrationDepartment,
           DirectoryType creationMethod,
           java.util.Calendar registrationDate,
           java.util.Calendar registrationLastDate,
           String fullNameRu,
           String fullNameKz,
           String fullNameEn,
           String shortNameRu,
           String shortNameKz,
           String shortNameEn,
           DirectoryType orgForm,
           DirectoryType formOfLaw,
           DirectoryType fundType,
           DirectoryType propertyType,
           Boolean commerceOrg,
           Boolean enterpriseSubject,
           DirectoryType privateEnterpriseType,
           Boolean affiliated,
           Boolean international,
           Boolean foreignInvest,
           Boolean oneCitizenShip,
           Boolean typicalCharter,
           Boolean branchesExistence,
           LiquidationType liquidation,
           PersonType organizationLeader,
           ActivityType[] activity,
           AddressType address,
           gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType[] foundersUL,
           PersonType[] foundersFL,
           Integer foundersCount,
           gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType headerOrganization) {
           this.BIN = BIN;
           this.regStatus = regStatus;
           this.registrationDepartment = registrationDepartment;
           this.creationMethod = creationMethod;
           this.registrationDate = registrationDate;
           this.registrationLastDate = registrationLastDate;
           this.fullNameRu = fullNameRu;
           this.fullNameKz = fullNameKz;
           this.fullNameEn = fullNameEn;
           this.shortNameRu = shortNameRu;
           this.shortNameKz = shortNameKz;
           this.shortNameEn = shortNameEn;
           this.orgForm = orgForm;
           this.formOfLaw = formOfLaw;
           this.fundType = fundType;
           this.propertyType = propertyType;
           this.commerceOrg = commerceOrg;
           this.enterpriseSubject = enterpriseSubject;
           this.privateEnterpriseType = privateEnterpriseType;
           this.affiliated = affiliated;
           this.international = international;
           this.foreignInvest = foreignInvest;
           this.oneCitizenShip = oneCitizenShip;
           this.typicalCharter = typicalCharter;
           this.branchesExistence = branchesExistence;
           this.liquidation = liquidation;
           this.organizationLeader = organizationLeader;
           this.activity = activity;
           this.address = address;
           this.foundersUL = foundersUL;
           this.foundersFL = foundersFL;
           this.foundersCount = foundersCount;
           this.headerOrganization = headerOrganization;
    }


    /**
     * Gets the BIN value for this OrganizationType.
     * 
     * @return BIN   * БИН организации
     */
    public String getBIN() {
        return BIN;
    }


    /**
     * Sets the BIN value for this OrganizationType.
     * 
     * @param BIN   * БИН организации
     */
    public void setBIN(String BIN) {
        this.BIN = BIN;
    }


    /**
     * Gets the regStatus value for this OrganizationType.
     * 
     * @return regStatus   * Статус юрлица, представительства
     */
    public DirectoryType getRegStatus() {
        return regStatus;
    }


    /**
     * Sets the regStatus value for this OrganizationType.
     * 
     * @param regStatus   * Статус юрлица, представительства
     */
    public void setRegStatus(DirectoryType regStatus) {
        this.regStatus = regStatus;
    }


    /**
     * Gets the registrationDepartment value for this OrganizationType.
     * 
     * @return registrationDepartment   * Орган юстиции, зарегистрировавший организацию
     */
    public DirectoryType getRegistrationDepartment() {
        return registrationDepartment;
    }


    /**
     * Sets the registrationDepartment value for this OrganizationType.
     * 
     * @param registrationDepartment   * Орган юстиции, зарегистрировавший организацию
     */
    public void setRegistrationDepartment(DirectoryType registrationDepartment) {
        this.registrationDepartment = registrationDepartment;
    }


    /**
     * Gets the creationMethod value for this OrganizationType.
     * 
     * @return creationMethod   * Способ образования
     */
    public DirectoryType getCreationMethod() {
        return creationMethod;
    }


    /**
     * Sets the creationMethod value for this OrganizationType.
     * 
     * @param creationMethod   * Способ образования
     */
    public void setCreationMethod(DirectoryType creationMethod) {
        this.creationMethod = creationMethod;
    }


    /**
     * Gets the registrationDate value for this OrganizationType.
     * 
     * @return registrationDate   * Дата первичной регистрации
     */
    public java.util.Calendar getRegistrationDate() {
        return registrationDate;
    }


    /**
     * Sets the registrationDate value for this OrganizationType.
     * 
     * @param registrationDate   * Дата первичной регистрации
     */
    public void setRegistrationDate(java.util.Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }


    /**
     * Gets the registrationLastDate value for this OrganizationType.
     * 
     * @return registrationLastDate   * Дата последней перерегистрации
     */
    public java.util.Calendar getRegistrationLastDate() {
        return registrationLastDate;
    }


    /**
     * Sets the registrationLastDate value for this OrganizationType.
     * 
     * @param registrationLastDate   * Дата последней перерегистрации
     */
    public void setRegistrationLastDate(java.util.Calendar registrationLastDate) {
        this.registrationLastDate = registrationLastDate;
    }


    /**
     * Gets the fullNameRu value for this OrganizationType.
     * 
     * @return fullNameRu   * Полное наименование на русском языке
     */
    public String getFullNameRu() {
        return fullNameRu;
    }


    /**
     * Sets the fullNameRu value for this OrganizationType.
     * 
     * @param fullNameRu   * Полное наименование на русском языке
     */
    public void setFullNameRu(String fullNameRu) {
        this.fullNameRu = fullNameRu;
    }


    /**
     * Gets the fullNameKz value for this OrganizationType.
     * 
     * @return fullNameKz   * Полное наименование на государственном языке
     */
    public String getFullNameKz() {
        return fullNameKz;
    }


    /**
     * Sets the fullNameKz value for this OrganizationType.
     * 
     * @param fullNameKz   * Полное наименование на государственном языке
     */
    public void setFullNameKz(String fullNameKz) {
        this.fullNameKz = fullNameKz;
    }


    /**
     * Gets the fullNameEn value for this OrganizationType.
     * 
     * @return fullNameEn   * Полное наименование на английском языке
     */
    public String getFullNameEn() {
        return fullNameEn;
    }


    /**
     * Sets the fullNameEn value for this OrganizationType.
     * 
     * @param fullNameEn   * Полное наименование на английском языке
     */
    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }


    /**
     * Gets the shortNameRu value for this OrganizationType.
     * 
     * @return shortNameRu   * Сокращенное наименование на русском языке
     */
    public String getShortNameRu() {
        return shortNameRu;
    }


    /**
     * Sets the shortNameRu value for this OrganizationType.
     * 
     * @param shortNameRu   * Сокращенное наименование на русском языке
     */
    public void setShortNameRu(String shortNameRu) {
        this.shortNameRu = shortNameRu;
    }


    /**
     * Gets the shortNameKz value for this OrganizationType.
     * 
     * @return shortNameKz   * Сокращенное наименование на государственном языке
     */
    public String getShortNameKz() {
        return shortNameKz;
    }


    /**
     * Sets the shortNameKz value for this OrganizationType.
     * 
     * @param shortNameKz   * Сокращенное наименование на государственном языке
     */
    public void setShortNameKz(String shortNameKz) {
        this.shortNameKz = shortNameKz;
    }


    /**
     * Gets the shortNameEn value for this OrganizationType.
     * 
     * @return shortNameEn   * Сокращенное наименование на английском языке
     */
    public String getShortNameEn() {
        return shortNameEn;
    }


    /**
     * Sets the shortNameEn value for this OrganizationType.
     * 
     * @param shortNameEn   * Сокращенное наименование на английском языке
     */
    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn;
    }


    /**
     * Gets the orgForm value for this OrganizationType.
     * 
     * @return orgForm   * Форма организации
     */
    public DirectoryType getOrgForm() {
        return orgForm;
    }


    /**
     * Sets the orgForm value for this OrganizationType.
     * 
     * @param orgForm   * Форма организации
     */
    public void setOrgForm(DirectoryType orgForm) {
        this.orgForm = orgForm;
    }


    /**
     * Gets the formOfLaw value for this OrganizationType.
     * 
     * @return formOfLaw   * ОПФ
     */
    public DirectoryType getFormOfLaw() {
        return formOfLaw;
    }


    /**
     * Sets the formOfLaw value for this OrganizationType.
     * 
     * @param formOfLaw   * ОПФ
     */
    public void setFormOfLaw(DirectoryType formOfLaw) {
        this.formOfLaw = formOfLaw;
    }


    /**
     * Gets the fundType value for this OrganizationType.
     * 
     * @return fundType   * Тип фонда
     */
    public DirectoryType getFundType() {
        return fundType;
    }


    /**
     * Sets the fundType value for this OrganizationType.
     * 
     * @param fundType   * Тип фонда
     */
    public void setFundType(DirectoryType fundType) {
        this.fundType = fundType;
    }


    /**
     * Gets the propertyType value for this OrganizationType.
     * 
     * @return propertyType   * Форма собственности
     */
    public DirectoryType getPropertyType() {
        return propertyType;
    }


    /**
     * Sets the propertyType value for this OrganizationType.
     * 
     * @param propertyType   * Форма собственности
     */
    public void setPropertyType(DirectoryType propertyType) {
        this.propertyType = propertyType;
    }


    /**
     * Gets the commerceOrg value for this OrganizationType.
     * 
     * @return commerceOrg   * 1 - коммерческая организация
     */
    public Boolean getCommerceOrg() {
        return commerceOrg;
    }


    /**
     * Sets the commerceOrg value for this OrganizationType.
     * 
     * @param commerceOrg   * 1 - коммерческая организация
     */
    public void setCommerceOrg(Boolean commerceOrg) {
        this.commerceOrg = commerceOrg;
    }


    /**
     * Gets the enterpriseSubject value for this OrganizationType.
     * 
     * @return enterpriseSubject   * 1 - субъект предпринимательства
     */
    public Boolean getEnterpriseSubject() {
        return enterpriseSubject;
    }


    /**
     * Sets the enterpriseSubject value for this OrganizationType.
     * 
     * @param enterpriseSubject   * 1 - субъект предпринимательства
     */
    public void setEnterpriseSubject(Boolean enterpriseSubject) {
        this.enterpriseSubject = enterpriseSubject;
    }


    /**
     * Gets the privateEnterpriseType value for this OrganizationType.
     * 
     * @return privateEnterpriseType   * Тип частного предпринимательства
     */
    public DirectoryType getPrivateEnterpriseType() {
        return privateEnterpriseType;
    }


    /**
     * Sets the privateEnterpriseType value for this OrganizationType.
     * 
     * @param privateEnterpriseType   * Тип частного предпринимательства
     */
    public void setPrivateEnterpriseType(DirectoryType privateEnterpriseType) {
        this.privateEnterpriseType = privateEnterpriseType;
    }


    /**
     * Gets the affiliated value for this OrganizationType.
     * 
     * @return affiliated   * 1 - дочерняя организация
     */
    public Boolean getAffiliated() {
        return affiliated;
    }


    /**
     * Sets the affiliated value for this OrganizationType.
     * 
     * @param affiliated   * 1 - дочерняя организация
     */
    public void setAffiliated(Boolean affiliated) {
        this.affiliated = affiliated;
    }


    /**
     * Gets the international value for this OrganizationType.
     * 
     * @return international   * 1 - международная организация;
     */
    public Boolean getInternational() {
        return international;
    }


    /**
     * Sets the international value for this OrganizationType.
     * 
     * @param international   * 1 - международная организация;
     */
    public void setInternational(Boolean international) {
        this.international = international;
    }


    /**
     * Gets the foreignInvest value for this OrganizationType.
     * 
     * @return foreignInvest   * 1 - с участием иностранных инвесторов;
     */
    public Boolean getForeignInvest() {
        return foreignInvest;
    }


    /**
     * Sets the foreignInvest value for this OrganizationType.
     * 
     * @param foreignInvest   * 1 - с участием иностранных инвесторов;
     */
    public void setForeignInvest(Boolean foreignInvest) {
        this.foreignInvest = foreignInvest;
    }


    /**
     * Gets the oneCitizenShip value for this OrganizationType.
     * 
     * @return oneCitizenShip   * 1-Участие лиц без гражданства
     */
    public Boolean getOneCitizenShip() {
        return oneCitizenShip;
    }


    /**
     * Sets the oneCitizenShip value for this OrganizationType.
     * 
     * @param oneCitizenShip   * 1-Участие лиц без гражданства
     */
    public void setOneCitizenShip(Boolean oneCitizenShip) {
        this.oneCitizenShip = oneCitizenShip;
    }


    /**
     * Gets the typicalCharter value for this OrganizationType.
     * 
     * @return typicalCharter   * 1 - деятельность по типовому уставу;
     */
    public Boolean getTypicalCharter() {
        return typicalCharter;
    }


    /**
     * Sets the typicalCharter value for this OrganizationType.
     * 
     * @param typicalCharter   * 1 - деятельность по типовому уставу;
     */
    public void setTypicalCharter(Boolean typicalCharter) {
        this.typicalCharter = typicalCharter;
    }


    /**
     * Gets the branchesExistence value for this OrganizationType.
     * 
     * @return branchesExistence   * 1-наличие филиалов и представительств
     */
    public Boolean getBranchesExistence() {
        return branchesExistence;
    }


    /**
     * Sets the branchesExistence value for this OrganizationType.
     * 
     * @param branchesExistence   * 1-наличие филиалов и представительств
     */
    public void setBranchesExistence(Boolean branchesExistence) {
        this.branchesExistence = branchesExistence;
    }


    /**
     * Gets the liquidation value for this OrganizationType.
     * 
     * @return liquidation   * Сведения о ликвидации
     */
    public LiquidationType getLiquidation() {
        return liquidation;
    }


    /**
     * Sets the liquidation value for this OrganizationType.
     * 
     * @param liquidation   * Сведения о ликвидации
     */
    public void setLiquidation(LiquidationType liquidation) {
        this.liquidation = liquidation;
    }


    /**
     * Gets the organizationLeader value for this OrganizationType.
     * 
     * @return organizationLeader   * Руководитель организации
     */
    public PersonType getOrganizationLeader() {
        return organizationLeader;
    }


    /**
     * Sets the organizationLeader value for this OrganizationType.
     * 
     * @param organizationLeader   * Руководитель организации
     */
    public void setOrganizationLeader(PersonType organizationLeader) {
        this.organizationLeader = organizationLeader;
    }


    /**
     * Gets the activity value for this OrganizationType.
     * 
     * @return activity   * Виды деятельности
     */
    public ActivityType[] getActivity() {
        return activity;
    }


    /**
     * Sets the activity value for this OrganizationType.
     * 
     * @param activity   * Виды деятельности
     */
    public void setActivity(ActivityType[] activity) {
        this.activity = activity;
    }

    public ActivityType getActivity(int i) {
        return this.activity[i];
    }

    public void setActivity(int i, ActivityType _value) {
        this.activity[i] = _value;
    }


    /**
     * Gets the address value for this OrganizationType.
     * 
     * @return address   * Адрес местонахождения
     */
    public AddressType getAddress() {
        return address;
    }


    /**
     * Sets the address value for this OrganizationType.
     * 
     * @param address   * Адрес местонахождения
     */
    public void setAddress(AddressType address) {
        this.address = address;
    }


    /**
     * Gets the foundersUL value for this OrganizationType.
     * 
     * @return foundersUL   * Участники, учредители - юрлица
     */
    public gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType[] getFoundersUL() {
        return foundersUL;
    }


    /**
     * Sets the foundersUL value for this OrganizationType.
     * 
     * @param foundersUL   * Участники, учредители - юрлица
     */
    public void setFoundersUL(gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType[] foundersUL) {
        this.foundersUL = foundersUL;
    }

    public gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType getFoundersUL(int i) {
        return this.foundersUL[i];
    }

    public void setFoundersUL(int i, gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType _value) {
        this.foundersUL[i] = _value;
    }


    /**
     * Gets the foundersFL value for this OrganizationType.
     * 
     * @return foundersFL   * Участники, учредители - физлица
     */
    public PersonType[] getFoundersFL() {
        return foundersFL;
    }


    /**
     * Sets the foundersFL value for this OrganizationType.
     * 
     * @param foundersFL   * Участники, учредители - физлица
     */
    public void setFoundersFL(PersonType[] foundersFL) {
        this.foundersFL = foundersFL;
    }

    public PersonType getFoundersFL(int i) {
        return this.foundersFL[i];
    }

    public void setFoundersFL(int i, PersonType _value) {
        this.foundersFL[i] = _value;
    }


    /**
     * Gets the foundersCount value for this OrganizationType.
     * 
     * @return foundersCount   * Количество участников, учредителей
     */
    public Integer getFoundersCount() {
        return foundersCount;
    }


    /**
     * Sets the foundersCount value for this OrganizationType.
     * 
     * @param foundersCount   * Количество участников, учредителей
     */
    public void setFoundersCount(Integer foundersCount) {
        this.foundersCount = foundersCount;
    }


    /**
     * Gets the headerOrganization value for this OrganizationType.
     * 
     * @return headerOrganization   * Сведения о головной организации филиала или представительства
     */
    public gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType getHeaderOrganization() {
        return headerOrganization;
    }


    /**
     * Sets the headerOrganization value for this OrganizationType.
     * 
     * @param headerOrganization   * Сведения о головной организации филиала или представительства
     */
    public void setHeaderOrganization(gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType headerOrganization) {
        this.headerOrganization = headerOrganization;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof OrganizationType)) return false;
        OrganizationType other = (OrganizationType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BIN==null && other.getBIN()==null) || 
             (this.BIN!=null &&
              this.BIN.equals(other.getBIN()))) &&
            ((this.regStatus==null && other.getRegStatus()==null) || 
             (this.regStatus!=null &&
              this.regStatus.equals(other.getRegStatus()))) &&
            ((this.registrationDepartment==null && other.getRegistrationDepartment()==null) || 
             (this.registrationDepartment!=null &&
              this.registrationDepartment.equals(other.getRegistrationDepartment()))) &&
            ((this.creationMethod==null && other.getCreationMethod()==null) || 
             (this.creationMethod!=null &&
              this.creationMethod.equals(other.getCreationMethod()))) &&
            ((this.registrationDate==null && other.getRegistrationDate()==null) || 
             (this.registrationDate!=null &&
              this.registrationDate.equals(other.getRegistrationDate()))) &&
            ((this.registrationLastDate==null && other.getRegistrationLastDate()==null) || 
             (this.registrationLastDate!=null &&
              this.registrationLastDate.equals(other.getRegistrationLastDate()))) &&
            ((this.fullNameRu==null && other.getFullNameRu()==null) || 
             (this.fullNameRu!=null &&
              this.fullNameRu.equals(other.getFullNameRu()))) &&
            ((this.fullNameKz==null && other.getFullNameKz()==null) || 
             (this.fullNameKz!=null &&
              this.fullNameKz.equals(other.getFullNameKz()))) &&
            ((this.fullNameEn==null && other.getFullNameEn()==null) || 
             (this.fullNameEn!=null &&
              this.fullNameEn.equals(other.getFullNameEn()))) &&
            ((this.shortNameRu==null && other.getShortNameRu()==null) || 
             (this.shortNameRu!=null &&
              this.shortNameRu.equals(other.getShortNameRu()))) &&
            ((this.shortNameKz==null && other.getShortNameKz()==null) || 
             (this.shortNameKz!=null &&
              this.shortNameKz.equals(other.getShortNameKz()))) &&
            ((this.shortNameEn==null && other.getShortNameEn()==null) || 
             (this.shortNameEn!=null &&
              this.shortNameEn.equals(other.getShortNameEn()))) &&
            ((this.orgForm==null && other.getOrgForm()==null) || 
             (this.orgForm!=null &&
              this.orgForm.equals(other.getOrgForm()))) &&
            ((this.formOfLaw==null && other.getFormOfLaw()==null) || 
             (this.formOfLaw!=null &&
              this.formOfLaw.equals(other.getFormOfLaw()))) &&
            ((this.fundType==null && other.getFundType()==null) || 
             (this.fundType!=null &&
              this.fundType.equals(other.getFundType()))) &&
            ((this.propertyType==null && other.getPropertyType()==null) || 
             (this.propertyType!=null &&
              this.propertyType.equals(other.getPropertyType()))) &&
            ((this.commerceOrg==null && other.getCommerceOrg()==null) || 
             (this.commerceOrg!=null &&
              this.commerceOrg.equals(other.getCommerceOrg()))) &&
            ((this.enterpriseSubject==null && other.getEnterpriseSubject()==null) || 
             (this.enterpriseSubject!=null &&
              this.enterpriseSubject.equals(other.getEnterpriseSubject()))) &&
            ((this.privateEnterpriseType==null && other.getPrivateEnterpriseType()==null) || 
             (this.privateEnterpriseType!=null &&
              this.privateEnterpriseType.equals(other.getPrivateEnterpriseType()))) &&
            ((this.affiliated==null && other.getAffiliated()==null) || 
             (this.affiliated!=null &&
              this.affiliated.equals(other.getAffiliated()))) &&
            ((this.international==null && other.getInternational()==null) || 
             (this.international!=null &&
              this.international.equals(other.getInternational()))) &&
            ((this.foreignInvest==null && other.getForeignInvest()==null) || 
             (this.foreignInvest!=null &&
              this.foreignInvest.equals(other.getForeignInvest()))) &&
            ((this.oneCitizenShip==null && other.getOneCitizenShip()==null) || 
             (this.oneCitizenShip!=null &&
              this.oneCitizenShip.equals(other.getOneCitizenShip()))) &&
            ((this.typicalCharter==null && other.getTypicalCharter()==null) || 
             (this.typicalCharter!=null &&
              this.typicalCharter.equals(other.getTypicalCharter()))) &&
            ((this.branchesExistence==null && other.getBranchesExistence()==null) || 
             (this.branchesExistence!=null &&
              this.branchesExistence.equals(other.getBranchesExistence()))) &&
            ((this.liquidation==null && other.getLiquidation()==null) || 
             (this.liquidation!=null &&
              this.liquidation.equals(other.getLiquidation()))) &&
            ((this.organizationLeader==null && other.getOrganizationLeader()==null) || 
             (this.organizationLeader!=null &&
              this.organizationLeader.equals(other.getOrganizationLeader()))) &&
            ((this.activity==null && other.getActivity()==null) || 
             (this.activity!=null &&
              java.util.Arrays.equals(this.activity, other.getActivity()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.foundersUL==null && other.getFoundersUL()==null) || 
             (this.foundersUL!=null &&
              java.util.Arrays.equals(this.foundersUL, other.getFoundersUL()))) &&
            ((this.foundersFL==null && other.getFoundersFL()==null) || 
             (this.foundersFL!=null &&
              java.util.Arrays.equals(this.foundersFL, other.getFoundersFL()))) &&
            ((this.foundersCount==null && other.getFoundersCount()==null) || 
             (this.foundersCount!=null &&
              this.foundersCount.equals(other.getFoundersCount()))) &&
            ((this.headerOrganization==null && other.getHeaderOrganization()==null) || 
             (this.headerOrganization!=null &&
              this.headerOrganization.equals(other.getHeaderOrganization())));
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
        if (getBIN() != null) {
            _hashCode += getBIN().hashCode();
        }
        if (getRegStatus() != null) {
            _hashCode += getRegStatus().hashCode();
        }
        if (getRegistrationDepartment() != null) {
            _hashCode += getRegistrationDepartment().hashCode();
        }
        if (getCreationMethod() != null) {
            _hashCode += getCreationMethod().hashCode();
        }
        if (getRegistrationDate() != null) {
            _hashCode += getRegistrationDate().hashCode();
        }
        if (getRegistrationLastDate() != null) {
            _hashCode += getRegistrationLastDate().hashCode();
        }
        if (getFullNameRu() != null) {
            _hashCode += getFullNameRu().hashCode();
        }
        if (getFullNameKz() != null) {
            _hashCode += getFullNameKz().hashCode();
        }
        if (getFullNameEn() != null) {
            _hashCode += getFullNameEn().hashCode();
        }
        if (getShortNameRu() != null) {
            _hashCode += getShortNameRu().hashCode();
        }
        if (getShortNameKz() != null) {
            _hashCode += getShortNameKz().hashCode();
        }
        if (getShortNameEn() != null) {
            _hashCode += getShortNameEn().hashCode();
        }
        if (getOrgForm() != null) {
            _hashCode += getOrgForm().hashCode();
        }
        if (getFormOfLaw() != null) {
            _hashCode += getFormOfLaw().hashCode();
        }
        if (getFundType() != null) {
            _hashCode += getFundType().hashCode();
        }
        if (getPropertyType() != null) {
            _hashCode += getPropertyType().hashCode();
        }
        if (getCommerceOrg() != null) {
            _hashCode += getCommerceOrg().hashCode();
        }
        if (getEnterpriseSubject() != null) {
            _hashCode += getEnterpriseSubject().hashCode();
        }
        if (getPrivateEnterpriseType() != null) {
            _hashCode += getPrivateEnterpriseType().hashCode();
        }
        if (getAffiliated() != null) {
            _hashCode += getAffiliated().hashCode();
        }
        if (getInternational() != null) {
            _hashCode += getInternational().hashCode();
        }
        if (getForeignInvest() != null) {
            _hashCode += getForeignInvest().hashCode();
        }
        if (getOneCitizenShip() != null) {
            _hashCode += getOneCitizenShip().hashCode();
        }
        if (getTypicalCharter() != null) {
            _hashCode += getTypicalCharter().hashCode();
        }
        if (getBranchesExistence() != null) {
            _hashCode += getBranchesExistence().hashCode();
        }
        if (getLiquidation() != null) {
            _hashCode += getLiquidation().hashCode();
        }
        if (getOrganizationLeader() != null) {
            _hashCode += getOrganizationLeader().hashCode();
        }
        if (getActivity() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActivity());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getActivity(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getFoundersUL() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFoundersUL());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFoundersUL(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFoundersFL() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFoundersFL());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFoundersFL(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFoundersCount() != null) {
            _hashCode += getFoundersCount().hashCode();
        }
        if (getHeaderOrganization() != null) {
            _hashCode += getHeaderOrganization().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrganizationType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "OrganizationType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationDepartment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegistrationDepartment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreationMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegistrationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationLastDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RegistrationLastDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullNameRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FullNameRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullNameKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FullNameKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullNameEn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FullNameEn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortNameRu");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShortNameRu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortNameKz");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShortNameKz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortNameEn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShortNameEn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgForm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrgForm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formOfLaw");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FormOfLaw"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FundType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PropertyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commerceOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CommerceOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EnterpriseSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privateEnterpriseType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrivateEnterpriseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("affiliated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Affiliated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("international");
        elemField.setXmlName(new javax.xml.namespace.QName("", "International"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreignInvest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ForeignInvest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oneCitizenShip");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OneCitizenShip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typicalCharter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TypicalCharter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branchesExistence");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branchesExistence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("liquidation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Liquidation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "LiquidationType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organizationLeader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrganizationLeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "PersonType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Activity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "ActivityType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "AddressType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foundersUL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FoundersUL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "OrganizationShortType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foundersFL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FoundersFL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "PersonType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foundersCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FoundersCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headerOrganization");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HeaderOrganization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "OrganizationShortType"));
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
