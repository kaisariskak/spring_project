package kz.bsbnb.usci.wsclient.service;

import gbdul.kz.bee.bip.SyncChannel.v10.Interfaces.UniversalServiceSyncServiceLocator;
import gbdul.kz.bee.bip.SyncChannel.v10.Interfaces.UniversalServiceSyncServiceSoapBindingStub;
import gbdul.kz.bee.bip.SyncChannel.v10.Types.Request.RequestData;
import gbdul.kz.bee.bip.SyncChannel.v10.Types.Request.SyncSendMessageRequest;
import gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse;
import gbdul.kz.bee.bip.SyncChannel.v10.Types.SyncMessageInfo;
import gbdul.kz.bee.bip.common.v10.Types.ErrorInfo;
import gbdul.nat.interactive.persistence.message.Request;
import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.wsclient.config.AxisHandler;
import kz.bsbnb.usci.wsclient.dao.GBDULDao;
import kz.bsbnb.usci.wsclient.model.gbdul.GBDULEntity;
import org.apache.axis.AxisFault;
import org.apache.axis.client.AxisClient;
import org.apache.axis.configuration.SimpleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class GBDULServiceImpl implements GBDULService {
    @Value( "${wsclient.gbdul.url}" )
    private String GBDUL_ENDPOINT_ADDRESS;
    @Value( "${wsclient.gbdul.username}" )
    private String gbdUlUsername;
    @Value( "${wsclient.gbdul.password}" )
    private String gbdUlPassword;

    private final GBDULDao gbdulDao;

    public GBDULServiceImpl(GBDULDao gbdulDao) {
        this.gbdulDao = gbdulDao;
    }

    private UniversalServiceSyncServiceSoapBindingStub getStub() throws ServiceException {
        SimpleProvider clientConfig = AxisHandler.configureAxisLogger();

        UniversalServiceSyncServiceLocator serviceLocator = new UniversalServiceSyncServiceLocator();
        serviceLocator.setEngineConfiguration(clientConfig);
        serviceLocator.setEngine(new AxisClient(clientConfig));
        UniversalServiceSyncServiceSoapBindingStub stub = (UniversalServiceSyncServiceSoapBindingStub) serviceLocator.getUniversalServiceSyncPort();
        stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, GBDUL_ENDPOINT_ADDRESS);
        stub.setUsername(gbdUlUsername);
        stub.setPassword(gbdUlPassword);
        return stub;
    }

    @Override
    public List<String> getBinList() {
        return gbdulDao.getBinList();
    }

    @Override
    public SyncSendMessageResponse sendMessage(String userId, String messageId, String requestorBin, String bin) throws UsciException {
        try {
            UniversalServiceSyncServiceSoapBindingStub stub = getStub();

            SyncMessageInfo requestInfo = new SyncMessageInfo();
            requestInfo.setUserId(userId);
            requestInfo.setMessageId(messageId);

            Request request = new Request();
            request.setBIN(bin);
            request.setRequestorBIN(requestorBin);

            RequestData requestData = new RequestData();
            requestData.setData(request);

            SyncSendMessageRequest sendMessageRequest = new SyncSendMessageRequest();
            sendMessageRequest.setRequestData(requestData);
            sendMessageRequest.setRequestInfo(requestInfo);

            SyncSendMessageResponse response = stub.sendMessage(sendMessageRequest);
            return response;
        } catch (Exception exp) {
            exp.printStackTrace();
            if (exp instanceof RemoteException) {
                AxisFault axisFault = (AxisFault) exp;
                throw new UsciException(axisFault.getFaultCode() +" "+ axisFault.getFaultString() + " "+ axisFault.getFaultDetails());
            } else if (exp instanceof ErrorInfo) {
                ErrorInfo errorInfo = (ErrorInfo) exp;
                throw new UsciException(errorInfo.getErrorMessage());
            } else {
                throw new UsciException(exp.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void saveGBDULInfo(GBDULEntity gbdulEntity) {
        gbdulDao.saveGBDULInfo(gbdulEntity);
        gbdulDao.updateCheck(gbdulEntity.getBin());
    }
}
