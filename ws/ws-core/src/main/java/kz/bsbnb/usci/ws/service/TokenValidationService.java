package kz.bsbnb.usci.ws.service;
import com.nimbusds.oauth2.sdk.TokenIntrospectionRequest;
import com.nimbusds.oauth2.sdk.TokenIntrospectionResponse;
import com.nimbusds.oauth2.sdk.TokenIntrospectionSuccessResponse;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import kz.bsbnb.usci.core.client.RespondentClient;
import kz.bsbnb.usci.model.adm.User;
import kz.bsbnb.usci.model.respondent.Respondent;
import kz.bsbnb.usci.ws.dao.WsDao;
import kz.bsbnb.usci.ws.modal.FullValidationResult;
import kz.bsbnb.usci.ws.modal.TokenValidationResult;
import kz.bsbnb.usci.ws.modal.UserValidationResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;

@Service
public class TokenValidationService {
    private final WsDao wsDao;
    private final RespondentClient respondentClient;

    @Value("${wso2.token.introspectUrl}")
    private String introspectUrl;

    @Value("${wso2.provider.clientId}")
    private String clientId;

    @Value("${wso2.provider.clientSecret}")
    private String clientSecret;

    public TokenValidationService(WsDao wsDao, RespondentClient respondentClient) {
        this.wsDao = wsDao;
        this.respondentClient = respondentClient;
    }
    /**
     * Валидирует токен и возвращает результат
     */
    public TokenValidationResult validateToken(String userToken) {
        try {
            URI introspectionEndpoint = new URI(introspectUrl);
            ClientID cliID = new ClientID("administrator");
            Secret secret = new Secret("Qwerty1234567");
            AccessToken inspectedToken = new BearerAccessToken(userToken);

            HTTPRequest httpRequest = new TokenIntrospectionRequest(
                    introspectionEndpoint,
                    new ClientSecretBasic(cliID, secret),
                    inspectedToken)
                    .toHTTPRequest();
            HTTPResponse httpResponse = httpRequest.send();

            TokenIntrospectionResponse response = TokenIntrospectionResponse.parse(httpResponse);

            if (!response.indicatesSuccess()) {
                return TokenValidationResult.error(105,
                        "Ошибка валидации токена: " + response.toErrorResponse().getErrorObject());
            }

            TokenIntrospectionSuccessResponse tokenDetails = response.toSuccessResponse();
            if (!tokenDetails.isActive()) {
                return TokenValidationResult.error(106,
                        "Недействительный / просроченный токен");
            }

            return TokenValidationResult.success(tokenDetails);

        } catch (Exception e) {
            return TokenValidationResult.error(105,
                    "Ошибка при валидации токена: " + e.getMessage());
        }
    }
    /**
     * Получает пользователя и проверяет доступ к респонденту
     */
    public UserValidationResult validateUserAndRespondent(String username, String respondentCode) {
        try {
            Long userId = wsDao.getUserIdByName(username);
            if (userId == null) {
                return UserValidationResult.error(101, "Пользователь не найден");
            }

            Respondent respondent = respondentClient.getRespondentByUser(new User(userId, false));
            if (respondent == null) {
                return UserValidationResult.error(102,
                        "Пользователь не имеет доступа к респондентам");
            }

            if (respondentCode != null && !respondentCode.equals(respondent.getCode())) {
                return UserValidationResult.error(103,
                        "Несоответствие респондента пользователю портала");
            }

            return UserValidationResult.success(userId, respondent);

        } catch (Exception e) {
            return UserValidationResult.error(100,
                    "Ошибка при проверке пользователя: " + e.getMessage());
        }
    }
    /**
     * Полная валидация: токен + пользователь + респондент
     */
    public FullValidationResult validateAll(String userToken, String username, String respondentCode) {
        // Проверяем токен
        TokenValidationResult tokenResult = validateToken(userToken);
        if (!tokenResult.isSuccess()) {
            return FullValidationResult.fromTokenError(tokenResult);
        }

        // Проверяем пользователя и респондента
        UserValidationResult userResult = validateUserAndRespondent(username, respondentCode);
        if (!userResult.isSuccess()) {
            return FullValidationResult.fromUserError(userResult);
        }

        return FullValidationResult.success(userResult.getUserId(), userResult.getRespondent());
    }

}
