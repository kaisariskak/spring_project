package kz.bsbnb.usci.ws.service;

import kz.bsbnb.usci.core.client.RespondentClient;
import kz.bsbnb.usci.eav.client.ProductClient;
import kz.bsbnb.usci.model.adm.User;
import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.respondent.Respondent;
import kz.bsbnb.usci.receiver.client.BatchClient;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.ws.dao.WsDao;
import kz.bsbnb.usci.ws.modal.FullValidationResult;
import kz.bsbnb.usci.ws.modal.info.ResponseInfo;
import kz.bsbnb.usci.ws.modal.uscientity.EntitiesRequest;
import kz.bsbnb.usci.ws.modal.uscientity.EntitiesResponse;
import kz.bsbnb.usci.ws.modal.uscientity.SignatureInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SuppressWarnings("Duplicates")
@Service
public class DataUploadSigningServiceImpl implements DataUploadSigningService {

    private static final String ROOT_CA_NAME = "CN=ҰЛТТЫҚ КУӘЛАНДЫРУШЫ ОРТАЛЫҚ (GOST),C=KZ";
    private final WsDao wsDao;
    private final RespondentClient respondentClient;
    private final ProductClient productClient;
    private final BatchClient batchClient;
    private final TokenValidationService tokenValidationService;

    @Value("${batch.wsDir}")
    private String batchWsDir;
    @Value("${wso2.provider.clientId}")
    private String clientId;
    @Value("${wso2.provider.clientSecret}")
    private String clientSecret;
    @Value("${wso2.token.url}")
    private String tokenUrl;
    @Value("${wso2.token.introspectUrl}")
    private String introspectUrl;
    @Value("${pki.ocsp.url}")
    private String ocspUrl;
    @Value("${pki.ocsp.cer}")
    private String ocspCer;

    public DataUploadSigningServiceImpl(WsDao wsDao,
                                        RespondentClient respondentClient,
                                        ProductClient productClient,
                                        BatchClient batchClient, TokenValidationService tokenValidationService) {
        this.wsDao = wsDao;
        this.respondentClient = respondentClient;
        this.productClient = productClient;
        this.batchClient = batchClient;
        this.tokenValidationService = tokenValidationService;
    }

    @Override
    public EntitiesResponse getUsciEntities(EntitiesRequest request) {
        LocalDateTime signingTime = LocalDateTime.now();
        EntitiesResponse getUsciEntitiesResponse = new EntitiesResponse();
        ResponseInfo responseInfo = new ResponseInfo();

        if (request.getData() != null) {
            request.setData(cleanXmlDeclaration(request.getData()));
        }
        if (request.getSignature() != null) {
            request.setSignature(cleanXmlDeclaration(request.getSignature()));
        }

        try {
            FullValidationResult validationResult = tokenValidationService.validateAll(request.getUserToken(),request.getUser(),request.getManifest().getRespondentCode());
            if (!validationResult.isSuccess()) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(validationResult.getErrorCode());
                responseInfo.setResponseText(validationResult.getErrorMessage());
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }
            Long userId = wsDao.getUserIdByName(request.getUser());
            Respondent respondent = respondentClient.getRespondentByUser(new User(userId, false));
            if (respondent == null) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(102);
                responseInfo.setResponseText("Пользователь не имеет доступа к респондентам");
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }

            if (!request.getManifest().getRespondentCode().equals(respondent.getCode())) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(103);
                responseInfo.setResponseText("Несоответствие респондента пользователю портала");
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }

            Product product = productClient.findProductByCode(request.getManifest().getProductCode());
            if (product == null) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(111);
                responseInfo.setResponseText("Продукт не верный");
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }

            Integer checkUserProduct = wsDao.checkUserProduct(userId,product.getId());
            if (checkUserProduct == 0) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(104);
                responseInfo.setResponseText("Пользователь не имеет права загружать информацию по продукту");
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }

            String signedXml = request.getData();
            Map<String, Object> signatureInfoData =batchClient.checkSignatureWs(request.getSignature());
            SignatureInfo info = SignatureInfo.fromMap(signatureInfoData);
            String sign = info.getSing();
            try {
                if (info.getBin() == null) {
                    responseInfo.setResponseTime(LocalDateTime.now().toString());
                    responseInfo.setResponseCode(306);
                    responseInfo.setResponseText("В ЭЦП отсутствует БИН");
                    getUsciEntitiesResponse.setResponseInfo(responseInfo);
                    return getUsciEntitiesResponse;
                }
            } catch (Exception e) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(307);
                responseInfo.setResponseText(String.format("Ошибка при считывании БИН с ЭЦП: %s", e.getMessage()));
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }

            String signInfo = info.getPrincipal();
            InputStream xsdStream = new ByteArrayInputStream(product.getXsd());
            InputStream xmlStream = new ByteArrayInputStream(signedXml.getBytes(StandardCharsets.UTF_8));
            Source xsd = new StreamSource(xsdStream);
            Source xml = new StreamSource(xmlStream);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsd);
            Validator validator = schema.newValidator();
            try {
                validator.validate(xml);
            } catch (SAXParseException e) {
                int line = e.getLineNumber();
                int col = e.getColumnNumber();
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(201);
                responseInfo.setResponseText(String.format("XML не прошёл проверку XSD: Line %d Column %d %s", line, col, e.getMessage()));
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            } catch (SAXException | IOException e) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(202);
                responseInfo.setResponseText(String.format("XML не прошёл проверку XSD: %s", e.getMessage()));
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }

            String manifestXml = "<manifest>\n" +
                    "\t<product>" + request.getManifest().getProductCode() + "</product>\n" +
                    "\t<report_date>" + request.getManifest().getReportDate() + "</report_date>\n";

            manifestXml += "\t<respondent>"+ request.getManifest().getRespondentCode() + "</respondent>\n";
            manifestXml += "</manifest>";

            File tempWsBatchFile = null;

            try {
                tempWsBatchFile = File.createTempFile("ws_", ".zip", new File(batchWsDir));
            } catch (IOException e) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(401);
                responseInfo.setResponseText(String.format("Ошибка при создании батч файла: %s", e.getMessage()));
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try (ZipOutputStream zipFile = new ZipOutputStream(bos)) {
                ZipEntry zipEntryData = new ZipEntry("data.xml");
                zipFile.putNextEntry(zipEntryData);
                zipFile.write(signedXml.getBytes());

                ZipEntry zipEntryManifest = new ZipEntry("usci_manifest.xml");
                zipFile.putNextEntry(zipEntryManifest);
                zipFile.write(manifestXml.getBytes());
            } catch (IOException e) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(401);
                responseInfo.setResponseText(String.format("Ошибка при создании батч файла: %s", e.getMessage()));
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }

            byte[] zipBytes = bos.toByteArray();

            try (FileOutputStream fileOutputStream = new FileOutputStream(tempWsBatchFile)) {
                fileOutputStream.write(zipBytes);
            } catch (IOException e) {
                responseInfo.setResponseTime(LocalDateTime.now().toString());
                responseInfo.setResponseCode(401);
                responseInfo.setResponseText(String.format("Ошибка при создании батч файла: %s", e.getMessage()));
                getUsciEntitiesResponse.setResponseInfo(responseInfo);
                return getUsciEntitiesResponse;
            }
            LocalDate reportDate = SqlJdbcConverter.convertToLocalDate(request.getManifest().getReportDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            batchClient.receiveBatchFromWebservice(userId, respondent.getId(), tempWsBatchFile.getName(), tempWsBatchFile.getPath(), zipBytes,
                                                   reportDate.format(formatter), sign, signInfo, signingTime.format(dateTimeFormatter), product);

            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(0);
            responseInfo.setResponseText("Запрос обработан успешно");
            getUsciEntitiesResponse.setResponseInfo(responseInfo);
            getUsciEntitiesResponse.setGeneratedBatchName(tempWsBatchFile.getName());
            return getUsciEntitiesResponse;
        } catch (Exception e) {
            responseInfo.setResponseTime(LocalDateTime.now().toString());
            responseInfo.setResponseCode(666);
            responseInfo.setResponseText("Ошибка: " + e.getMessage());
            getUsciEntitiesResponse.setResponseInfo(responseInfo);
            return getUsciEntitiesResponse;
        }

    }
    private String cleanXmlDeclaration(String xml) {
        if (xml == null) return null;
        return xml.replaceAll("<\\?xml[^?>]*\\?>\\s*", "").trim();
    }

}
