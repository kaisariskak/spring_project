package kz.bsbnb.usci.ws.service;

import kz.bsbnb.usci.ws.modal.authtoken.AuthTokenRequest;
import kz.bsbnb.usci.ws.modal.authtoken.AuthTokenResponse;

public interface AuthTokenService {
    AuthTokenResponse getAuthToken(AuthTokenRequest request);
}
