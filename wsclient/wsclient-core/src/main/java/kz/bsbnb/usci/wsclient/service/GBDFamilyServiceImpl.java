package kz.bsbnb.usci.wsclient.service;

import gbdfm.GBDFLFamilyByIINServiceSoapBindingStub;
import gbdfm.GBDFLFamilyByIINServiceLocator;
import gbdfm.RequestData;
import gbdfm.ResponseDataType;
import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.wsclient.config.AxisHandler;
import org.apache.axis.AxisFault;
import org.apache.axis.client.AxisClient;
import org.apache.axis.configuration.SimpleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;
import java.rmi.RemoteException;

@Service
public class GBDFamilyServiceImpl implements  GBDFamilyService{

   // @Value( "${wsclient.gbdfm.url}" )
    private String GBDFM_ENDPOINT_ADDRESS;
    //@Value( "${wsclient.gbdfm.username}" )
    //private String gbdUlUsername;
    //@Value( "${wsclient.gbdfm.password}" )
    //private String gbdUlPassword;

    public GBDFamilyServiceImpl() {

    }

    private GBDFLFamilyByIINServiceSoapBindingStub getStub() throws ServiceException {
        SimpleProvider clientConfig = AxisHandler.configureAxisLogger();

        GBDFLFamilyByIINServiceLocator serviceLocator = new GBDFLFamilyByIINServiceLocator();
        serviceLocator.setEngineConfiguration(clientConfig);
        serviceLocator.setEngine(new AxisClient(clientConfig));
        GBDFLFamilyByIINServiceSoapBindingStub stub = (GBDFLFamilyByIINServiceSoapBindingStub) serviceLocator.getGBDFLFamilyByIINServicePort();
        stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, GBDFM_ENDPOINT_ADDRESS);
       // stub.setUsername(gbdUlUsername);
       // stub.setPassword(gbdUlPassword);
        return stub;
    }

    @Override
    public ResponseDataType sendMessage(String userId, String iin) throws UsciException {
        try {
            GBDFLFamilyByIINServiceSoapBindingStub stub = getStub();

            RequestData requestInfo = new RequestData();
            requestInfo.setUserId(userId);
            requestInfo.setIin(iin);
            ResponseDataType response = stub.sendMessage(userId, iin);
            return response;

        } catch (Exception exp) {
            exp.printStackTrace();
            if (exp instanceof RemoteException) {
                AxisFault axisFault = (AxisFault) exp;
                throw new UsciException(axisFault.getFaultCode() +" "+ axisFault.getFaultString() + " "+ axisFault.getFaultDetails());
            } else {
                throw new UsciException(exp.getMessage());
            }
        }
    }

}
