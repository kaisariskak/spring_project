package kz.bsbnb.usci.ws.service;

import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import kz.bsbnb.usci.ws.modal.protocol.EntityErrorResponse;
import kz.bsbnb.usci.ws.modal.protocol.ProtocolRequest;
import kz.bsbnb.usci.ws.modal.protocol.ProtocolResponse;

public interface ProtocolService {
    ProtocolResponse getProtocolList(ProtocolRequest getProtocolRequest);
    EntityErrorResponse getEntityErrorResponseList(UserInfoRequest request);
}
