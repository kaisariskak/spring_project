package kz.bsbnb.usci.ws.endpoint;

import kz.bsbnb.usci.ws.service.DataUploadSigningService;

//@Endpoint
public class Endpoints {
    private final String TARGET_NAMESPACE = "http://usci.bsbnb.kz/ws/schema";

    private final DataUploadSigningService wsService;

    public Endpoints(DataUploadSigningService wsService) {
        this.wsService = wsService;
    }

    /*@PayloadRoot(localPart = "GetUsciEntitiesRequest", namespace = TARGET_NAMESPACE)
    public @ResponsePayload
    GetUsciEntitiesResponse getUsciEntities(@RequestPayload GetUsciEntitiesRequest request) {
        return wsService.getUsciEntities(request);
    }

    @PayloadRoot(localPart = "GetAuthTokenRequest", namespace = TARGET_NAMESPACE)
    public @ResponsePayload
    GetAuthTokenResponse getAuthToken(@RequestPayload GetAuthTokenRequest request) {
        return wsService.getAuthToken(request);
    }*/
}
