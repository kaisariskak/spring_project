package kz.bsbnb.usci.wsclient.config;

import org.apache.axis.*;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.transport.http.HTTPSender;
import org.apache.axis.transport.http.HTTPTransport;
import org.apache.axis.utils.Messages;

public class AxisHandler extends BasicHandler {
    private static final long serialVersionUID = 1L;

    @Override
    public void invoke(MessageContext msgContext) throws AxisFault {
        try {
            logMessage(msgContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logMessage(MessageContext msgContext) throws Exception {
        Message inMsg = msgContext.getRequestMessage();
        Message outMsg = msgContext.getResponseMessage();
        if (outMsg == null) {
            //replace it with logger if you need it log in different file.
            System.out.println("============================= REQUEST ============================================");
            System.out.println(
                    Messages.getMessage("inMsg00", (inMsg == null ? "" : inMsg.getSOAPEnvelope().getAsString())));
        } else {
            System.out.println("===================================RESPONSE======================================");
            System.out.println(
                    Messages.getMessage("outMsg00", (outMsg == null ? "" : outMsg.getSOAPEnvelope().getAsString())));
        }
    }

    @Override
    public void onFault(MessageContext msgContext) {
        super.onFault(msgContext);
        try {
            logMessage(msgContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SimpleProvider configureAxisLogger() {
        SimpleProvider clientConfig = new SimpleProvider();
        AxisHandler logHandler = new AxisHandler();
        SimpleChain reqHandler = new SimpleChain();
        SimpleChain respHandler = new SimpleChain();
        reqHandler.addHandler(logHandler);
        respHandler.addHandler(logHandler);
        Handler pivot = new HTTPSender();
        Handler transport = new SimpleTargetedChain(reqHandler, pivot, respHandler);
        clientConfig.deployTransport(HTTPTransport.DEFAULT_TRANSPORT_NAME, transport);
        return clientConfig;
    }
}