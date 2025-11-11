package kz.bsbnb.usci.ws.service;


import kz.bsbnb.usci.ws.modal.uscientity.EntitiesRequest;
import kz.bsbnb.usci.ws.modal.uscientity.EntitiesResponse;

public interface DataUploadSigningService {
      EntitiesResponse getUsciEntities(EntitiesRequest request);
}
