/**
 * UniversalServiceSyncServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.bee.bip.SyncChannel.v10.Interfaces;

public class UniversalServiceSyncServiceSoapBindingStub extends org.apache.axis.client.Stub implements UniversalServiceSync {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SendMessage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Request", "SyncSendMessageRequest"), gbdul.kz.bee.bip.SyncChannel.v10.Types.Request.SyncSendMessageRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Response", "SyncSendMessageResponse"));
        oper.setReturnClass(gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "response"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types", "SendMessageFault1_SendMessageFault"),
                      "kz.bee.bip.common.v10.Types.ErrorInfo",
                      new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "ErrorInfo"), 
                      true
                     ));
        _operations[0] = oper;

    }

    public UniversalServiceSyncServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public UniversalServiceSyncServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public UniversalServiceSyncServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
            Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "ErrorInfo");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.common.v10.Types.ErrorInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "Property");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.common.v10.Types.Property.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "SenderInfo");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.common.v10.Types.SenderInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/common/v10/Types", "StatusInfo");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.common.v10.Types.StatusInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Request", "RequestData");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.SyncChannel.v10.Types.Request.RequestData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Request", "SyncSendMessageRequest");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.SyncChannel.v10.Types.Request.SyncSendMessageRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Response", "ResponseData");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.ResponseData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Response", "ResponseWrapper");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.ResponseWrapper.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types/Response", "SyncSendMessageResponse");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types", "SyncMessageInfo");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types", "SyncMessageInfoResponse");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "AddressInvalidity");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.AddressInvalidity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "AddressStatus");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.AddressStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "AddressType");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.AddressType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "CapableStatus");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.CapableStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Country");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.Country.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Court");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.Court.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "District");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.District.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "DocumentInvalidity");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.DocumentInvalidity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "DocumentOrganization");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.DocumentOrganization.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "DocumentType");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.DocumentType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "ExcludeReason");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.ExcludeReason.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Gender");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.Gender.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "GpTerritorial");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.GpTerritorial.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "MessageResult");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.MessageResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Nationality");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.Nationality.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Participant");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.Participant.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "PersonStatus");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.PersonStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dictionaries.persistence.interactive.nat", "Region");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.dictionaries.Region.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://document.persistence.interactive.nat", "Document");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.document.Document.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "ActivityType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.ActivityType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "AddressType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.AddressType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "DirectoryType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.DirectoryType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "LiquidationType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.LiquidationType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "OrganizationShortType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationShortType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "OrganizationType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.OrganizationType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "PersonType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.PersonType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "RequestDataType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.RequestDataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://gbdulinfobybin.egp.gbdul.tamur.kz", "ResponseDataType");
            cachedSerQNames.add(qName);
            cls = gbdul.kz.tamur.gbdul.egp.gbdulinfobybin.ResponseDataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://message.persistence.interactive.nat", "Request");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.message.Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", ">Person>addresses");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.PersonAddresses.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", ">Person>documents");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.PersonDocuments.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "Address");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.Address.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "BirthPlace");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.BirthPlace.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "Certificate");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.Certificate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "DisappearStatus");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.DisappearStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "ForeignData");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.ForeignData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "MissingStatus");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.MissingStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "Person");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.Person.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "PersonCapableStatus");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.PersonCapableStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "PersonExcludeStatus");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.PersonExcludeStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "RegAddress");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.RegAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://person.persistence.interactive.nat", "RepatriationStatus");
            cachedSerQNames.add(qName);
            cls = gbdul.nat.interactive.persistence.person.RepatriationStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethodType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.CanonicalizationMethodType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "CryptoBinary");
            cachedSerQNames.add(qName);
            cls = byte[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethodType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.DigestMethodType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "DigestValueType");
            cachedSerQNames.add(qName);
            cls = byte[].class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(arraysf);
            cachedDeserFactories.add(arraydf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "DSAKeyValueType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.DSAKeyValueType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "HMACOutputLengthType");
            cachedSerQNames.add(qName);
            cls = java.math.BigInteger.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "KeyInfoType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.KeyInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "KeyValueType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.KeyValueType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "ManifestType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.ManifestType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "ObjectType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.ObjectType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "PGPDataType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.PGPDataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "ReferenceType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.ReferenceType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "RetrievalMethodType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.RetrievalMethodType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "RSAKeyValueType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.RSAKeyValueType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignatureMethodType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.SignatureMethodType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignaturePropertiesType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.SignaturePropertiesType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignaturePropertyType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.SignaturePropertyType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignatureType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.SignatureType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignatureValueType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.SignatureValueType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfoType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.SignedInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SPKIDataType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.SPKIDataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "TransformsType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.TransformsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "TransformType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.TransformType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "X509DataType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.X509DataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "X509IssuerSerialType");
            cachedSerQNames.add(qName);
            cls = gbdul.org.w3.www._2000._09.xmldsig.X509IssuerSerialType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        Class cls = (Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            Class sf = (Class)
                                 cachedSerFactories.get(i);
                            Class df = (Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse sendMessage(gbdul.kz.bee.bip.SyncChannel.v10.Types.Request.SyncSendMessageRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Types", "SendMessage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        Object _resp = _call.invoke(new Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse) _resp;
            } catch (Exception _exception) {
                return (gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse) org.apache.axis.utils.JavaUtils.convert(_resp, gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof gbdul.kz.bee.bip.common.v10.Types.ErrorInfo) {
              throw (gbdul.kz.bee.bip.common.v10.Types.ErrorInfo) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
