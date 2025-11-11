package kz.bsbnb.usci.ws.test;
import kz.bsbnb.usci.ws.controller.EndpointsController;
import kz.bsbnb.usci.ws.modal.authtoken.AuthTokenRequest;
import kz.bsbnb.usci.ws.modal.authtoken.AuthTokenResponse;
import kz.bsbnb.usci.ws.modal.confirm.ConfrimApproveRequest;
import kz.bsbnb.usci.ws.modal.confirm.ConfrimResponse;
import kz.bsbnb.usci.ws.modal.crosscheck.CrossCheckResponse;
import kz.bsbnb.usci.ws.modal.info.ReportInfo;
import kz.bsbnb.usci.ws.modal.info.RequestInfo;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.info.UserInfoRequest;
import kz.bsbnb.usci.ws.modal.protocol.EntityErrorResponse;
import kz.bsbnb.usci.ws.modal.report.OutputFormResponse;
import kz.bsbnb.usci.ws.modal.report.RunReportResponse;
import kz.bsbnb.usci.ws.modal.report.UserReportResponse;
import kz.bsbnb.usci.ws.modal.uscientity.EntitiesRequest;
import kz.bsbnb.usci.ws.modal.uscientity.EntitiesResponse;
import kz.bsbnb.usci.ws.modal.uscientity.Manifest;
import kz.bsbnb.usci.ws.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = {
        "server.port=18080",
        "server.ssl.enabled=false",
        "eureka.client.enabled=false"
})
public class WsTest {
    @Autowired
    DataUploadSigningService wsService;
    @Autowired
    AuthTokenService authTokenService;
    @Autowired
    EndpointsController endpointsController;
    @Autowired
    ProtocolService protocolService;
    @Autowired
    ReportService reportService;
    @org.springframework.boot.web.server.LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate rest;
    @Autowired
    CrossCheckService crossCheckService;
    @Autowired
    ConfirmService confirmService;
    @Test
    public void testAuthToken_ok() {
        String xml =
                "<GetAuthTokenRequest xmlns=\"http://usci.bsbnb.kz/ws/schema\">" +
                        "  <requestInfo><requestId>{A1}</requestId><requestTime>2025-01-15T16:32:00+05:00</requestTime></requestInfo>" +
                        "  <user>testnur</user><userPass>Qwerty12345</userPass>" +
                        "</GetAuthTokenRequest>";

        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_XML);
        h.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

        ResponseEntity<String> rsp = rest.exchange(
                "http://localhost:" + port + "/ws/auth-token",
                HttpMethod.POST,
                new HttpEntity<>(xml, h),
                String.class
        );

        assertThat(rsp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(rsp.getHeaders().getContentType().toString()).contains("application/xml");
        assertThat(rsp.getBody()).contains("<GetAuthTokenResponse");
    }

    @Test
    public void testAuthToken() {
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfo = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("Qwerty12345");
        //getAuthTokenResponse = wsService.getAuthToken(getAuthTokenRequest);
    }

    @Test
    public void testUsciBatch() {
        /***************************************/
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("!Qwerty123");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);
        /**************************************/


        EntitiesRequest usciEntitiesRequest = new EntitiesRequest();
        RequestInfo requestInfo = new RequestInfo();
        Manifest manifest = new Manifest();
        usciEntitiesRequest.setUser("testnur");
        usciEntitiesRequest.setUserToken(getAuthTokenResponse.getUserToken());
        usciEntitiesRequest.setUserBin("960440000538");
        manifest.setProductCode("DEPOSIT");
        manifest.setReportDate("01.01.2025");
        manifest.setRespondentCode("000");
        usciEntitiesRequest.setManifest(manifest);
        usciEntitiesRequest.setData("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<batch>\n" +
                "<entities xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "<depositor_info operation=\"DELETE\">\n" +
                "    <ref_region>\n" +
                "      <code>59</code>\n" +
                "    </ref_region>\n" +
                "    <docs>\n" +
                "        <item>\n" +
                "          <no>KZ0999999D0777777778</no>\n" +
                "            <ref_doc_type>\n" +
                "              <code>07</code>\n" +
                "            </ref_doc_type>\n" +
                "        </item>\n" +
                "    </docs>\n" +
                "  <is_person>false</is_person>\n" +
                "    <ref_country>\n" +
                "      <code_alpha_2>KZ</code_alpha_2>\n" +
                "      <code>KAZ</code>\n" +
                "      <code_numeric>398</code_numeric>\n" +
                "    </ref_country>\n" +
                "    <ref_residency>\n" +
                "      <code>1</code>\n" +
                "    </ref_residency>\n" +
                "  <is_related>false</is_related>\n" +
                "  <is_se>false</is_se>\n" +
                "    <ref_econ_sector>\n" +
                "      <code>7</code>\n" +
                "    </ref_econ_sector>\n" +
                "</depositor_info>\n" +
                "\n" +
                "\n" +
                "</entities>\n" +
                "</batch>");
        usciEntitiesRequest.setSignature("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><root>\n" +
                "  <ApplicationId>0</ApplicationId>\n" +
                "  <HashAlgorithm>SHA-256</HashAlgorithm>\n" +
                "  <Hash>f6cf3cfdde79e5f64cbdabcc8afaca17 </Hash>\n" +
                "<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
                "<ds:SignedInfo>\n" +
                "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
                "<ds:SignatureMethod Algorithm=\"urn:ietf:params:xml:ns:pkigovkz:xmlsec:algorithms:gostr34102015-gostr34112015-512\"/>\n" +
                "<ds:Reference URI=\"\">\n" +
                "<ds:Transforms>\n" +
                "<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" +
                "<ds:Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"/>\n" +
                "</ds:Transforms>\n" +
                "<ds:DigestMethod Algorithm=\"urn:ietf:params:xml:ns:pkigovkz:xmlsec:algorithms:gostr34112015-512\"/>\n" +
                "<ds:DigestValue>pRnM4lrX4zju/Td0JGlR6sVbaIEBoX0LK4chFNPwd8ornrDLXdQYnwhu58Ig2PZ4Ozcw7dj8FpaP\n" +
                "UHIZeGdFvA==</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "</ds:SignedInfo>\n" +
                "<ds:SignatureValue>\n" +
                "ITzOP0Do+gpo8VOg1dtrD185TBBiuDtl9grnIgPwIu7BDYNhSi+I8bNYiAbV7IVlwsbq3+RidakK\n" +
                "2wVYhRbdKJKib3SJ9wjyCHZ7yiE3uws9YboVd5W2o1cqFjfBSj31PK2QCtuy73VQLjT7CxFPOPB+\n" +
                "/i5ISmdmKrBd8a7QLxs=\n" +
                "</ds:SignatureValue>\n" +
                "<ds:KeyInfo>\n" +
                "<ds:X509Data>\n" +
                "<ds:X509Certificate>\n" +
                "MIIFETCCBHmgAwIBAgIUGbB2oXrKpXaz8ZqbBEv9mq6AKsIwDgYKKoMOAwoBAQIDAgUAMFgxSTBH\n" +
                "BgNVBAMMQNKw0JvQotCi0KvSmiDQmtCj05jQm9CQ0J3QlNCr0KDQo9Co0Ksg0J7QoNCi0JDQm9Cr\n" +
                "0pogKEdPU1QpIDIwMjIxCzAJBgNVBAYTAktaMB4XDTI1MDcxODEyMDQzNVoXDTI2MDcxODEyMDQz\n" +
                "NVowggFAMSIwIAYDVQQDDBnQlNCW0JDQmtCY0KjQldCSINCj0JvQkNCdMRkwFwYDVQQEDBDQlNCW\n" +
                "0JDQmtCY0KjQldCSMRgwFgYDVQQFEw9JSU45MDA4MDYzMDAxMDkxCzAJBgNVBAYTAktaMYGgMIGd\n" +
                "BgNVBAoMgZXQkNC60YbQuNC+0L3QtdGA0L3QvtC1INC+0LHRidC10YHRgtCy0L4gwqvQptC10L3R\n" +
                "gtGAINGG0LjRhNGA0L7QstC+0LPQviDRgNCw0LfQstC40YLQuNGPINCd0LDRhtC40L7QvdCw0LvR\n" +
                "jNC90L7Qs9C+INCR0LDQvdC60LAg0JrQsNC30LDRhdGB0YLQsNC90LDCuzEYMBYGA1UECwwPQklO\n" +
                "OTYwNDQwMDAwNTM4MRswGQYDVQQqDBLQotCe0JvQmNCa0J7QktCY0KcwgawwIwYJKoMOAwoBAQIC\n" +
                "MBYGCiqDDgMKAQECAgEGCCqDDgMKAQMDA4GEAASBgF3qWQQXaIhnRTq1D8Q8sp5cLFvuhs9GSlp2\n" +
                "vIYCEYyvuLDENNXXzkiacwytmkNX+ilgJkVvh5t9znAJgCZajBeVrWn9tv2MAqieInulXvs/o0NU\n" +
                "h87VDC0MYfgCeNafA0oCgs3sRkw9IbfQmzTcePV9x1xtBV9D3nyHmc1z+NqFo4IB3TCCAdkwDgYD\n" +
                "VR0PAQH/BAQDAgPIMDIGA1UdJQQrMCkGCCqDDgMDBAMCBggrBgEFBQcDBAYIKoMOAwMEAQIGCSqD\n" +
                "DgMDBAECBTA4BgNVHSAEMTAvMC0GBiqDDgMDAjAjMCEGCCsGAQUFBwIBFhVodHRwOi8vcGtpLmdv\n" +
                "di5rei9jcHMwOAYDVR0fBDEwLzAtoCugKYYnaHR0cDovL2NybC5wa2kuZ292Lmt6L25jYV9nb3N0\n" +
                "XzIwMjIuY3JsMGgGCCsGAQUFBwEBBFwwWjAiBggrBgEFBQcwAYYWaHR0cDovL29jc3AucGtpLmdv\n" +
                "di5rejA0BggrBgEFBQcwAoYoaHR0cDovL3BraS5nb3Yua3ovY2VydC9uY2FfZ29zdF8yMDIyLmNl\n" +
                "cjA6BgNVHS4EMzAxMC+gLaArhilodHRwOi8vY3JsLnBraS5nb3Yua3ovbmNhX2RfZ29zdF8yMDIy\n" +
                "LmNybDAhBgNVHREEGjAYgRZ1bGFuLmpha3NpaGV2QGJzYm5iLmt6MB0GA1UdDgQWBBQZsHahesql\n" +
                "drPxmpsES/2aroAqwjAfBgNVHSMEGDAWgBT+ML6fyJBjPx//WjwMsMhfTG0XCDAWBgYqgw4DAwUE\n" +
                "DDAKBggqgw4DAwUBATAOBgoqgw4DCgEBAgMCBQADgYEA2NGf6NWB7tvXNnjQKyFESi3nX5zFrsF+\n" +
                "3HguFRhntElU9NVwf7GT39AREHoCKHuyNxuj2HSPo2qKLM79coQ68uOVTOQ7Uzb2v7an0JFDQC+D\n" +
                "PRvqL4L/CzQtBjRb0wbMe24aNXm9eYYWlOgftoLFbQdCZLZcQ7F3YzAL8P8XX5U=\n" +
                "</ds:X509Certificate>\n" +
                "</ds:X509Data>\n" +
                "</ds:KeyInfo>\n" +
                "</ds:Signature></root>");

        EntitiesResponse getUsciEntitiesResponse = wsService.getUsciEntities(usciEntitiesRequest);



    }
    @Test
    public void testProtocol(){
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("Qwerty12345");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);

        UserInfoRequest getProtocolRequest = new UserInfoRequest();
        EntityErrorResponse getProtocolResponse = new EntityErrorResponse();
        getProtocolRequest.setUser("testnur");
        getProtocolRequest.setUserToken(getAuthTokenResponse.getUserToken());
        getProtocolRequest.setRespondentCode("000");
        getProtocolRequest.setProductCode("DEPOSIT");
        getProtocolRequest.setReportDate("01.08.2025");
        getProtocolResponse = protocolService.getEntityErrorResponseList(getProtocolRequest);
    }
    @Test
    public void testRunReport(){
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("Qwerty12345");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);

        ReportInfo reportInfo = new ReportInfo();
        RunReportResponse response = new RunReportResponse();
        reportInfo.setUser("testnur");
        reportInfo.setUserToken(getAuthTokenResponse.getUserToken());
        reportInfo.setRespondentCode("000");
        reportInfo.setProductCode("DEPOSIT");
        reportInfo.setReportDate("01.07.2025");
        reportInfo.setVitrina("VREP_DEPOSIT_CHANGE");
        //response = reportService.callRunReport(reportInfo);
    }

    @Test
    public void testGetRepList(){
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("Qwerty12345");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);

        UserInfoRequest reportInfo = new UserInfoRequest();
        OutputFormResponse response = new OutputFormResponse();
        reportInfo.setUser("testnur");
        reportInfo.setUserToken(getAuthTokenResponse.getUserToken());
        reportInfo.setRespondentCode("000");
        reportInfo.setProductCode("DEPOSIT");
        reportInfo.setReportDate("01.07.2025");
        response = reportService.getOutputFormList(reportInfo);
    }
    @Test
    public void testGetUserRepList(){
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("Qwerty12345");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);

        UserReportResponse response = new UserReportResponse();
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setUser("testnur");
        reportInfo.setUserToken(getAuthTokenResponse.getUserToken());
        reportInfo.setRespondentCode("000");
        reportInfo.setProductCode("DEPOSIT");
        reportInfo.setReportDate("01.09.2025");
        reportInfo.setVitrina("VREP_DEPOSIT_CHANGE");
        response = reportService.getUserReport(reportInfo);
    }
    @Test
    public void testCroscheck(){
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("Qwerty12345");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);

        UserInfoRequest getProtocolRequest = new UserInfoRequest();
        CrossCheckResponse getProtocolResponse = new CrossCheckResponse();
        getProtocolRequest.setUser("testnur");
        getProtocolRequest.setUserToken(getAuthTokenResponse.getUserToken());
        getProtocolRequest.setRespondentCode("000");
        getProtocolRequest.setProductCode("DEPOSIT");
        getProtocolRequest.setReportDate("01.04.2025");
        getProtocolResponse = crossCheckService.getCrossCheckList(getProtocolRequest);
    }
    @Test
    public void testRunCroscheck(){
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("!Qwerty123");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);

        UserInfoRequest reportInfo = new UserInfoRequest();
        ResponseInfo response = new ResponseInfo();
        reportInfo.setUser("testnur");
        reportInfo.setUserToken(getAuthTokenResponse.getUserToken());
        reportInfo.setRespondentCode("000");
        reportInfo.setProductCode("DEPOSIT");
        reportInfo.setReportDate("01.07.2025");
        response = crossCheckService.callCrossCheck(reportInfo);
    }
    @Test
    public void testGetConfirm(){
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("!Qwerty123");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);

        UserInfoRequest reportInfo = new UserInfoRequest();
        ConfrimResponse response = new ConfrimResponse();
        reportInfo.setUser("testnur");
        reportInfo.setUserToken(getAuthTokenResponse.getUserToken());
        reportInfo.setRespondentCode("000");
        reportInfo.setProductCode("DEPOSIT");
        reportInfo.setReportDate("01.07.2025");
        response = confirmService.getConfirmList(reportInfo);
    }

    @Test
    public void testrunonfirm(){
        AuthTokenRequest getAuthTokenRequest = new AuthTokenRequest();
        RequestInfo requestInfoT = new RequestInfo();
        AuthTokenResponse getAuthTokenResponse = new AuthTokenResponse();
        getAuthTokenRequest.setUser("testnur");
        getAuthTokenRequest.setUserPass("!Qwerty123");
        getAuthTokenResponse = authTokenService.getAuthToken(getAuthTokenRequest);

        UserInfoRequest userInfoRequest = new  UserInfoRequest();
        userInfoRequest.setUserToken(getAuthTokenResponse.getUserToken());
        userInfoRequest.setUser("testnur");
        userInfoRequest.setUserToken(getAuthTokenResponse.getUserToken());
        userInfoRequest.setRespondentCode("000");
        userInfoRequest.setProductCode("DEPOSIT");
        userInfoRequest.setReportDate("01.07.2025");
        ConfrimApproveRequest confrimApproveRequest = new ConfrimApproveRequest();
        confrimApproveRequest.setUserInfo(userInfoRequest);
        confrimApproveRequest.setIdConfirm(123030L);
        confrimApproveRequest.setUserNameSigning("ДЖАКИШЕВ УЛАН");
        confrimApproveRequest.setDocumentHash("64c55ee1cd2efb7558925eb74d4dea8a");
        confrimApproveRequest.setSignature("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><data>\n" +
                "          <HashAlgorithm>SHA-256</HashAlgorithm>\n" +
                "          <Hash>64c55ee1cd2efb7558925eb74d4dea8a</Hash>\n" +
                "        <ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
                "<ds:SignedInfo>\n" +
                "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
                "<ds:SignatureMethod Algorithm=\"urn:ietf:params:xml:ns:pkigovkz:xmlsec:algorithms:gostr34102015-gostr34112015-512\"/>\n" +
                "<ds:Reference URI=\"\">\n" +
                "<ds:Transforms>\n" +
                "<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" +
                "<ds:Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments\"/>\n" +
                "</ds:Transforms>\n" +
                "<ds:DigestMethod Algorithm=\"urn:ietf:params:xml:ns:pkigovkz:xmlsec:algorithms:gostr34112015-512\"/>\n" +
                "<ds:DigestValue>kdvKLhP2P/N860oiCcIaSHFv7ZwYINnzVExnbQOw+aUOc9V5/NR/IAUe9d2XnoDVI6pzo7usBDUw\n" +
                "BvO3zdEjIA==</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "</ds:SignedInfo>\n" +
                "<ds:SignatureValue>\n" +
                "z15/7W21Q2+/XmsZ9anUI2flFnhTn63td5q2zZhu4B4n11JjJt7NPceT+zCpnH9EYGVti0w4Gwx7\n" +
                "wwJn4GXJTGbrAAr95blPTCJI6i+efQFq0dALSSofyFpgH/EDs/bO/0dF/jwu4uxWJXVsvpbZuUei\n" +
                "Utu3/2k8Tcyhwvde9GA=\n" +
                "</ds:SignatureValue>\n" +
                "<ds:KeyInfo>\n" +
                "<ds:X509Data>\n" +
                "<ds:X509Certificate>\n" +
                "MIIFETCCBHmgAwIBAgIUGbB2oXrKpXaz8ZqbBEv9mq6AKsIwDgYKKoMOAwoBAQIDAgUAMFgxSTBH\n" +
                "BgNVBAMMQNKw0JvQotCi0KvSmiDQmtCj05jQm9CQ0J3QlNCr0KDQo9Co0Ksg0J7QoNCi0JDQm9Cr\n" +
                "0pogKEdPU1QpIDIwMjIxCzAJBgNVBAYTAktaMB4XDTI1MDcxODEyMDQzNVoXDTI2MDcxODEyMDQz\n" +
                "NVowggFAMSIwIAYDVQQDDBnQlNCW0JDQmtCY0KjQldCSINCj0JvQkNCdMRkwFwYDVQQEDBDQlNCW\n" +
                "0JDQmtCY0KjQldCSMRgwFgYDVQQFEw9JSU45MDA4MDYzMDAxMDkxCzAJBgNVBAYTAktaMYGgMIGd\n" +
                "BgNVBAoMgZXQkNC60YbQuNC+0L3QtdGA0L3QvtC1INC+0LHRidC10YHRgtCy0L4gwqvQptC10L3R\n" +
                "gtGAINGG0LjRhNGA0L7QstC+0LPQviDRgNCw0LfQstC40YLQuNGPINCd0LDRhtC40L7QvdCw0LvR\n" +
                "jNC90L7Qs9C+INCR0LDQvdC60LAg0JrQsNC30LDRhdGB0YLQsNC90LDCuzEYMBYGA1UECwwPQklO\n" +
                "OTYwNDQwMDAwNTM4MRswGQYDVQQqDBLQotCe0JvQmNCa0J7QktCY0KcwgawwIwYJKoMOAwoBAQIC\n" +
                "MBYGCiqDDgMKAQECAgEGCCqDDgMKAQMDA4GEAASBgF3qWQQXaIhnRTq1D8Q8sp5cLFvuhs9GSlp2\n" +
                "vIYCEYyvuLDENNXXzkiacwytmkNX+ilgJkVvh5t9znAJgCZajBeVrWn9tv2MAqieInulXvs/o0NU\n" +
                "h87VDC0MYfgCeNafA0oCgs3sRkw9IbfQmzTcePV9x1xtBV9D3nyHmc1z+NqFo4IB3TCCAdkwDgYD\n" +
                "VR0PAQH/BAQDAgPIMDIGA1UdJQQrMCkGCCqDDgMDBAMCBggrBgEFBQcDBAYIKoMOAwMEAQIGCSqD\n" +
                "DgMDBAECBTA4BgNVHSAEMTAvMC0GBiqDDgMDAjAjMCEGCCsGAQUFBwIBFhVodHRwOi8vcGtpLmdv\n" +
                "di5rei9jcHMwOAYDVR0fBDEwLzAtoCugKYYnaHR0cDovL2NybC5wa2kuZ292Lmt6L25jYV9nb3N0\n" +
                "XzIwMjIuY3JsMGgGCCsGAQUFBwEBBFwwWjAiBggrBgEFBQcwAYYWaHR0cDovL29jc3AucGtpLmdv\n" +
                "di5rejA0BggrBgEFBQcwAoYoaHR0cDovL3BraS5nb3Yua3ovY2VydC9uY2FfZ29zdF8yMDIyLmNl\n" +
                "cjA6BgNVHS4EMzAxMC+gLaArhilodHRwOi8vY3JsLnBraS5nb3Yua3ovbmNhX2RfZ29zdF8yMDIy\n" +
                "LmNybDAhBgNVHREEGjAYgRZ1bGFuLmpha3NpaGV2QGJzYm5iLmt6MB0GA1UdDgQWBBQZsHahesql\n" +
                "drPxmpsES/2aroAqwjAfBgNVHSMEGDAWgBT+ML6fyJBjPx//WjwMsMhfTG0XCDAWBgYqgw4DAwUE\n" +
                "DDAKBggqgw4DAwUBATAOBgoqgw4DCgEBAgMCBQADgYEA2NGf6NWB7tvXNnjQKyFESi3nX5zFrsF+\n" +
                "3HguFRhntElU9NVwf7GT39AREHoCKHuyNxuj2HSPo2qKLM79coQ68uOVTOQ7Uzb2v7an0JFDQC+D\n" +
                "PRvqL4L/CzQtBjRb0wbMe24aNXm9eYYWlOgftoLFbQdCZLZcQ7F3YzAL8P8XX5U=\n" +
                "</ds:X509Certificate>\n" +
                "</ds:X509Data>\n" +
                "</ds:KeyInfo>\n" +
                "</ds:Signature></data>");

        confrimApproveRequest.setSignType("PKI");
        confirmService.confrimApprove(confrimApproveRequest);
    }

}
