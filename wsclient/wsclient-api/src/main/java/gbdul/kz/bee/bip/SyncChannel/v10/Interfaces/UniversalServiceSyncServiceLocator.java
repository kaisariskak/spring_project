/**
 * UniversalServiceSyncServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gbdul.kz.bee.bip.SyncChannel.v10.Interfaces;

public class UniversalServiceSyncServiceLocator extends org.apache.axis.client.Service implements UniversalServiceSyncService {

    public UniversalServiceSyncServiceLocator() {
    }


    public UniversalServiceSyncServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UniversalServiceSyncServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UniversalServiceSyncPort
    private String UniversalServiceSyncPort_address = "http://BSB16-W10:8088/mockUniversalServiceSyncServiceSoapBinding";

    public String getUniversalServiceSyncPortAddress() {
        return UniversalServiceSyncPort_address;
    }

    // The WSDD service name defaults to the port name.
    private String UniversalServiceSyncPortWSDDServiceName = "UniversalServiceSyncPort";

    public String getUniversalServiceSyncPortWSDDServiceName() {
        return UniversalServiceSyncPortWSDDServiceName;
    }

    public void setUniversalServiceSyncPortWSDDServiceName(String name) {
        UniversalServiceSyncPortWSDDServiceName = name;
    }

    public UniversalServiceSync getUniversalServiceSyncPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UniversalServiceSyncPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUniversalServiceSyncPort(endpoint);
    }

    public UniversalServiceSync getUniversalServiceSyncPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gbdul.kz.bee.bip.SyncChannel.v10.Interfaces.UniversalServiceSyncServiceSoapBindingStub _stub = new gbdul.kz.bee.bip.SyncChannel.v10.Interfaces.UniversalServiceSyncServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getUniversalServiceSyncPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUniversalServiceSyncPortEndpointAddress(String address) {
        UniversalServiceSyncPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (UniversalServiceSync.class.isAssignableFrom(serviceEndpointInterface)) {
                gbdul.kz.bee.bip.SyncChannel.v10.Interfaces.UniversalServiceSyncServiceSoapBindingStub _stub = new gbdul.kz.bee.bip.SyncChannel.v10.Interfaces.UniversalServiceSyncServiceSoapBindingStub(new java.net.URL(UniversalServiceSyncPort_address), this);
                _stub.setPortName(getUniversalServiceSyncPortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("UniversalServiceSyncPort".equals(inputPortName)) {
            return getUniversalServiceSyncPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "UniversalServiceSyncService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://bip.bee.kz/SyncChannel/v10/Interfaces", "UniversalServiceSyncPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("UniversalServiceSyncPort".equals(portName)) {
            setUniversalServiceSyncPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
