package kz.bsbnb.usci.ws.service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponseParser;
import kz.bsbnb.usci.ws.modal.authtoken.AuthTokenRequest;
import kz.bsbnb.usci.ws.modal.authtoken.AuthTokenResponse;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
@Service
public class AuthTokenServiceImpl implements AuthTokenService{
    @Value("${wso2.provider.clientId}")
    private String clientId;
    @Value("${wso2.provider.clientSecret}")
    private String clientSecret;
    @Value("${wso2.token.url}")
    private String tokenUrl;

    @Override
    public AuthTokenResponse getAuthToken(AuthTokenRequest request) {
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        ResponseInfo responseInfo = new ResponseInfo();

        AuthorizationGrant passwordGrant = new ResourceOwnerPasswordCredentialsGrant(request.getUser(), new Secret(request.getUserPass()));
        ClientID cliID = new ClientID(clientId);
        Secret secret = new Secret(clientSecret);
        ClientAuthentication clientAuth = new ClientSecretBasic(cliID, secret);
        Scope scope = new Scope("openid", "email", "profile");

        try {
            URI tokenEndpoint = new URI(tokenUrl);
            TokenRequest requestT = new TokenRequest(tokenEndpoint, clientAuth, passwordGrant, scope);
            TokenResponse tokenResponse = OIDCTokenResponseParser.parse(requestT.toHTTPRequest().send());

            if (!tokenResponse.indicatesSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(101);
                responseInfo.setResponseText("Авторизация пользователя не пройдена: неверный логин или пароль");
                getAuthTokenResponse.setResponseInfo(responseInfo);
                return getAuthTokenResponse;
            }

            OIDCTokenResponse successResponse = (OIDCTokenResponse)tokenResponse.toSuccessResponse();
            JWT idToken = successResponse.getOIDCTokens().getIDToken();
            String userEmail = idToken.getJWTClaimsSet().getSubject();
            AccessToken accessToken = successResponse.getOIDCTokens().getAccessToken();// getOIDCTokens().getAccessToken();

            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
            getAuthTokenResponse.setResponseInfo(responseInfo);
            getAuthTokenResponse.setUserToken(accessToken.getValue());
            return getAuthTokenResponse;

        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            getAuthTokenResponse.setResponseInfo(responseInfo);
            return getAuthTokenResponse;
        }
    }
}
