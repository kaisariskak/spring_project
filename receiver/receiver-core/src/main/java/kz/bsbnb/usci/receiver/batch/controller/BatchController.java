package kz.bsbnb.usci.receiver.batch.controller;

import kz.bsbnb.usci.model.batch.Product;
import kz.bsbnb.usci.model.exception.UsciException;
import kz.bsbnb.usci.model.util.ParentChildRespondentJson;
import kz.bsbnb.usci.model.ws.EntityError;
import kz.bsbnb.usci.model.ws.Protocol;
import kz.bsbnb.usci.receiver.audit.AuditClinetReceiver;
import kz.bsbnb.usci.receiver.audit.model.AuditSendReceiver;
import kz.bsbnb.usci.receiver.batch.service.BatchJsonService;
import kz.bsbnb.usci.receiver.batch.service.BatchMaintenanceService;
import kz.bsbnb.usci.receiver.batch.service.BatchService;
import kz.bsbnb.usci.receiver.model.*;
import kz.bsbnb.usci.receiver.model.json.BatchJson;
import kz.bsbnb.usci.receiver.model.json.BatchJsonList;
import kz.bsbnb.usci.receiver.model.json.BatchSignJson;
import kz.bsbnb.usci.receiver.model.json.BatchSignJsonList;
import kz.bsbnb.usci.receiver.pki_validator_new.KeyUsage;
import kz.bsbnb.usci.receiver.pki_validator_new.PkiValidator;
import kz.bsbnb.usci.receiver.processor.BatchReceiver;
import kz.bsbnb.usci.receiver.queue.BatchQueueHolder;
import kz.bsbnb.usci.receiver.sign.kisc.SignatureChecker;
import kz.bsbnb.usci.receiver.sign.kisc.KISCSignBatch;
import kz.bsbnb.usci.receiver.sign.kisc.SignVerificationInfo;
import kz.bsbnb.usci.util.client.ConfigClient;
import kz.bsbnb.usci.util.json.ext.ExtJsList;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author Jandos Iskakov
 * @author Yernur Bakash
 */

@RestController
@RequestMapping(value = "/batch")
public class BatchController {
    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    private final BatchService batchService;
    private final BatchReceiver batchReceiver;
    private final BatchQueueHolder batchQueueHolder;
    private final BatchJsonService batchJsonService;
    private final BatchMaintenanceService batchMaintenanceService;
    private final ConfigClient configClient;
    public AuditClinetReceiver auditClinet;
    public AuditSendReceiver auditSend;

    public BatchController(BatchService batchService,
                           BatchReceiver batchProcessor,
                           BatchQueueHolder batchQueueHolder,
                           BatchJsonService batchJsonService,
                           BatchMaintenanceService batchMaintenanceService,
                           ConfigClient configClient,
                           AuditClinetReceiver auditClinet) {
        this.batchService = batchService;
        this.batchReceiver = batchProcessor;
        this.batchQueueHolder = batchQueueHolder;
        this.batchJsonService = batchJsonService;
        this.batchMaintenanceService = batchMaintenanceService;
        this.configClient = configClient;
        this.auditClinet=auditClinet;
    }

    @PostMapping(value = "endBatch")
    public void endBatch(@RequestParam(name = "batchId") Long batchId) {
        batchService.endBatch(batchId);
    }

    @PostMapping(value = "incrementActualCounts")
    public boolean incrementActualCounts(@RequestBody Map<Long, Long> batchesToUpdate) {
        return batchService.incrementActualCounts(batchesToUpdate);
    }

    @PostMapping(value = "uploadBatch")
    public void uploadBatch(@RequestParam("file") MultipartFile[] files,
                            @RequestParam(name = "isNb") Boolean isNb,
                            @RequestParam(name = "userId") Long userId) {
        for (MultipartFile uploadedFile : files) {
            BatchFile batchFile = new BatchFile();
            batchFile.setNb(isNb);
            batchFile.setUserId(userId);
            try {
                auditSend = new AuditSendReceiver(null,"LOADDATAAUKN",null,userId,uploadedFile.getOriginalFilename());
                auditSend=  auditClinet.send(auditSend);

                batchFile.setFileContent(uploadedFile.getBytes());
                batchFile.setFileName(uploadedFile.getOriginalFilename());
            } catch (IOException e) {
                auditSend.errorText=e.getMessage();
                auditSend= auditClinet.send(auditSend);
                throw new UsciException("Ошибка загрузки батча");
            }

            batchReceiver.receiveBatch(batchFile);
        }
    }
    @PostMapping(value = "uploadBatchNew")
    public void uploadBatchNew(@RequestParam("file") MultipartFile[] files,
                            @RequestParam(name = "isNb") Boolean isNb,
                            @RequestParam(name = "userId") Long userId) {
        for (MultipartFile uploadedFile : files) {
            BatchFile batchFile = new BatchFile();
            batchFile.setNb(isNb);
            batchFile.setUserId(userId);

            try {
                auditSend = new AuditSendReceiver(null,"LOADDATAAUKN",null,userId,uploadedFile.getOriginalFilename());
                auditSend=  auditClinet.send(auditSend);

                batchFile.setFileContent(uploadedFile.getBytes());
                batchFile.setFileName(uploadedFile.getOriginalFilename());
            } catch (IOException e) {
                auditSend.errorText=e.getMessage();
                auditSend= auditClinet.send(auditSend);
                throw new UsciException("Ошибка загрузки батча");
            }

            batchReceiver.receiveBatch(batchFile);
        }
    }

    @PostMapping(value = "receiveBatchFromWebservice")
    public void receiveBatchFromWebservice(@RequestParam(name = "userId") Long userId,
                                           @RequestParam(name = "respondentId") Long respondentId,
                                           @RequestParam(name = "batchName") String batchName,
                                           @RequestParam(name = "filePath") String filePath,
                                           @RequestParam(name = "fileContent") byte[] fileContent,
                                           @RequestParam(name = "reportDate")  @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate,
                                           @RequestParam(name = "sign") String sign,
                                           @RequestParam(name = "signInfo") String signInfo,
                                           @RequestParam(name = "signTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime signTime,
                                           //@RequestParam(name = "signTime") LocalDateTime signTime,
                                           @RequestBody Product product) {
            batchReceiver.receiveBatchFromWebservice(userId, respondentId, product, batchName, filePath, fileContent, reportDate, sign, signInfo, signTime);
    }

    @GetMapping(value = "getBatchList")
    public ExtJsList getBatchList(@RequestParam(name = "respondentIds") List<Long> respondentIds,
                                  @RequestParam(name = "userId") Long userId,
                                  @RequestParam(name = "isNb") Boolean isNb,
                                  @RequestParam(name = "reportDate")  @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate,
                                  @RequestParam(name = "page") Integer pageIndex,
                                  @RequestParam(name = "limit") Integer pageSize) {
        return batchJsonService.getBatchList(respondentIds, userId, isNb, reportDate, pageIndex, pageSize);
    }

    @GetMapping(value = "getBatchStatusList")
    public ExtJsList getBatchStatusList(@RequestParam(name = "batchId") Long batchId,
                                        @RequestParam(name = "statusTypes") List<String> statusTypes) {
        return batchJsonService.getBatchStatusList(batchId, statusTypes);
    }

    @GetMapping(value = "getBatchContent")
    public byte[] getBatchContent(@RequestParam(name = "batchId") Long batchId) {
        return batchService.getBatch(batchId).getContent();
    }

    @PostMapping(value = "getBatchExcelContent")
    public byte[] getBatchExcelContent(@RequestBody BatchJsonList batchJsonList) {
        return batchJsonService.getExcelFromBatch(batchJsonList.getBatchList(), batchJsonList.getColumnList());
    }

    @PostMapping(value = "getProtocolExcelContent")
    public byte[] getProtocolExcelContent(@RequestBody BatchStatusJsonList batchStatusJsonList) {
        return batchJsonService.getExcelFromProtocol(batchStatusJsonList.getProtocolList(), batchStatusJsonList.getColumnList());
    }

    @GetMapping(value = "getPendingBatchList")
    public List<BatchJson> getPendingBatchList(@RequestParam(name = "respondentIds") List<Long> respondentIds) {
        return batchJsonService.getPendingBatchList(respondentIds);
    }

    @GetMapping(value = "getMaintenanceBatchList")
    public List<BatchJson> getMaintenanceBatchList(@RequestParam(name = "respondentIds") List<Long> respondentIds,
                                                   @RequestParam(name = "reportDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate,
                                                   @RequestParam(name = "userId") Long userId) {
        return batchJsonService.getMaintenanceBatchList(respondentIds, reportDate, userId);
    }

    @PostMapping(value = "approveAndSendMaintenance")
    public void approveAndSendMaintenance(@RequestParam(name = "batchIds") List<Long> batchIds) {
        batchService.approveMaintenanceBatchList(batchIds);
        for (Long batchId : batchIds) {
           batchReceiver.processBatch(batchId);
        }
    }

    @PostMapping(value = "declineAndSendMaintenance")
    public void declineAndSendMaintenance(@RequestParam(name = "batchIds") List<Long> batchIds) {
        batchService.declineMaintenanceBatchList(batchIds);
        for (Long batchId : batchIds) {
            batchReceiver.declineMaintenanceBatch(batchId);
        }
    }

    @PostMapping(value = "reloadQueueConfig")
    public void reloadQueueConfig() {
        batchQueueHolder.reloadConfig();
    }

    @GetMapping(value = "getQueuePreviewBatches")
    public ExtJsList getQueuePreviewBatches(@RequestParam(name = "respondentsWithPriority") Set<Long> respondentsWithPriority,
                                            @RequestParam(name = "queueAlgo") String queueAlgo) {
        return new ExtJsList(batchQueueHolder.getOrderedBatches(respondentsWithPriority, queueAlgo));
    }

    @PostMapping(value = "getQueueBatchExcelContent")
    public byte[] getQueueBatchExcelContent(@RequestBody BatchJsonList batchJsonList) {
        return batchJsonService.getExcelFromQueueBatch(batchJsonList.getBatchList(), batchJsonList.getColumnList());
    }

    @GetMapping(value = "getBatchListToSign")
    public List<BatchJson> getBatchListToSign(@RequestParam(name = "respondentId") Long respondentId,
                                              @RequestParam(name = "userId") Long userId) {
        return batchJsonService.getBatchListToSign(respondentId, userId);
    }

    /*****************vremenno********************/
    @PostMapping(value = "saveSignedBatchListNew")
    public void saveSignedBatchListNew(@RequestParam(name = "respondentBin") String respondentBin,
                                    @RequestParam(name = "signType") String signType,
                                    @RequestParam(name = "userId") Long userId,
                                    @RequestBody BatchSignJsonList batchSignJsonList) {
        if (signType.equals("KISC")) {
            String ocspServiceUrl = "http://91.195.226.34:62255";
            SignatureChecker checker = new SignatureChecker(respondentBin, ocspServiceUrl);
            for (BatchSignJson batch : batchSignJsonList.getBatchList()) {
                try {
                    /*checker.checkAndUpdate(batch);
                    batchService.signBatch(batch.getId(), batch.getSignature(), batch.getInformation(), batch.getSigningTime(), null);
                    batchReceiver.processBatch(batch.getId());*/
                    SignVerificationInfo signVerificationInfo= KISCSignBatch.validateXML(batch.getSignature(),ocspServiceUrl);
                    if(!respondentBin.equals(signVerificationInfo.getBin())) {
                        throw new UsciException("Бин не совпадает!!! -> " + respondentBin+" <> "+ signVerificationInfo.getBin());
                    }
                    LocalDateTime signTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
                    batch.setSigningTime(signTime);
                    batch.setInformation(signVerificationInfo.getPrincipal());
                    batchService.signBatch(batch.getId(), batch.getSignature(), batch.getInformation(), batch.getSigningTime(), null);
                    batchReceiver.processBatch(batch.getId());
                } catch (UsciException e) {
                    logger.error("По батчу обнаружены ошибки id = {}, ошибка = {}", batch.getId(), e);

                    batchService.addBatchStatus(new BatchStatus()
                            .setBatchId(batch.getId())
                            .setStatus(BatchStatusType.ERROR)
                            .setText(e.getMessage())
                            .setExceptionTrace(e != null? ExceptionUtils.getStackTrace(e): null)
                            .setReceiptDate(LocalDateTime.now()));

                    batchService.endBatch(batch.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (signType.equals("PKI")) {
            //String ocspServiceUrl = "http://ocsp.pki.gov.kz";
            String binParent=null;
            //PkiSignatureChecker checker = new PkiSignatureChecker(respondentBin, ocspServiceUrl);
            Set<Long> batchIds = new HashSet<>();
            for (BatchSignJson batch : batchSignJsonList.getBatchList()) {
                batchIds.add(batch.getId());
            }

            for (BatchSignJson batch : batchSignJsonList.getBatchList()) {
                try {

                    auditSend = new AuditSendReceiver(null,"SIGNINGAUKN",null,userId,batch.getFileName());
                    auditSend=  auditClinet.send(auditSend);

                    SignatureInfo info = PkiValidator.validate(batch.getSignature(),batch.getHash(), KeyUsage.SIGN);
                    String binMsq = (respondentBin.length()==0) ?" Бин орг. пустой! ":respondentBin;
                    ParentChildRespondentJson parentChildRespondentJson= configClient.getChildRespondent(respondentBin);
                    if(parentChildRespondentJson!=null) {
                        if (!parentChildRespondentJson.parentRespondent.bin.equals(info.getBin())) {
                            throw new UsciException("Бин не совпадает!!! -> " + binMsq + " <> " + info.getBin());
                        }
                    }
                        /*if (!parentChildRespondentJson.childRespondent.bin.equals(respondentBin)){
                            throw new UsciException("Бин не совпадает!!! -> " + respondentBin+" <> "+ parentChildRespondentJson.childRespondent.bin);
                        }*/
                    else if(!respondentBin.equals(info.getBin())) {
                        throw new UsciException("Бин не совпадает!!! -> " + binMsq+" <> "+ info.getBin());
                    }
                    //binParent = parentChildRespondentJson.parentRespondent.bin;

                    //checker.checkAndUpdate(batch,binParent);
                    //batchService.signBatch(batch.getId(), batch.getSignature(), batch.getInformation(), batch.getSigningTime(), batchIds);
                    LocalDateTime signTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
                    batch.setSigningTime(signTime);
                    batch.setInformation(info.getPrincipal());
                    String base64Signature = Base64.getEncoder().encodeToString(batch.getSignature().getBytes());
                    batchService.signBatch(batch.getId(), /*batch.getSignature()*/base64Signature, batch.getInformation(), batch.getSigningTime(), batchIds);
                    batchReceiver.processBatch(batch.getId());
                } catch (UsciException e) {
                    logger.error("По батчу обнаружены ошибки id = {}, ошибка = {}", batch.getId(), e);

                    batchService.addBatchStatus(new BatchStatus()
                            .setBatchId(batch.getId())
                            .setStatus(BatchStatusType.ERROR)
                            .setText(e.getMessage())
                            .setExceptionTrace(e != null? ExceptionUtils.getStackTrace(e): null)
                            .setReceiptDate(LocalDateTime.now()));

                    batchService.endBatch(batch.getId());
                }
            }
        }
    }
    /***********************************/

    @PostMapping(value = "saveSignedBatchList")
    public void saveSignedBatchList(@RequestParam(name = "respondentBin") String respondentBin,
                                    @RequestParam(name = "signType") String signType,
                                    @RequestBody BatchSignJsonList batchSignJsonList) {
        if (signType.equals("KISC")) {
            String ocspServiceUrl = "http://91.195.226.34:62255";
            SignatureChecker checker = new SignatureChecker(respondentBin, ocspServiceUrl);
            for (BatchSignJson batch : batchSignJsonList.getBatchList()) {
                try {
                    /*checker.checkAndUpdate(batch);
                    batchService.signBatch(batch.getId(), batch.getSignature(), batch.getInformation(), batch.getSigningTime(), null);
                    batchReceiver.processBatch(batch.getId());*/
                    SignVerificationInfo signVerificationInfo= KISCSignBatch.validateXML(batch.getSignature(),ocspServiceUrl);
                    if(!respondentBin.equals(signVerificationInfo.getBin())) {
                        throw new UsciException("Бин не совпадает!!! -> " + respondentBin+" <> "+ signVerificationInfo.getBin());
                    }
                    LocalDateTime signTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
                    batch.setSigningTime(signTime);
                    batch.setInformation(signVerificationInfo.getPrincipal());
                    batchService.signBatch(batch.getId(), batch.getSignature(), batch.getInformation(), batch.getSigningTime(), null);
                    batchReceiver.processBatch(batch.getId());
                } catch (UsciException e) {
                    logger.error("По батчу обнаружены ошибки id = {}, ошибка = {}", batch.getId(), e);

                    batchService.addBatchStatus(new BatchStatus()
                            .setBatchId(batch.getId())
                            .setStatus(BatchStatusType.ERROR)
                            .setText(e.getMessage())
                            .setExceptionTrace(e != null? ExceptionUtils.getStackTrace(e): null)
                            .setReceiptDate(LocalDateTime.now()));

                    batchService.endBatch(batch.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (signType.equals("PKI")) {
            //String ocspServiceUrl = "http://ocsp.pki.gov.kz";
            String binParent=null;
            //PkiSignatureChecker checker = new PkiSignatureChecker(respondentBin, ocspServiceUrl);
            Set<Long> batchIds = new HashSet<>();
            for (BatchSignJson batch : batchSignJsonList.getBatchList()) {
                    batchIds.add(batch.getId());
            }

            for (BatchSignJson batch : batchSignJsonList.getBatchList()) {
                try {

                    /*auditSend = new AuditSendReceiver(null,"SIGNINGAUKN",null,null,batch.getFileName());
                    auditSend=  auditClinet.send(auditSend);
*/
                    SignatureInfo info = PkiValidator.validate(batch.getSignature(),batch.getHash(), KeyUsage.SIGN);
                    String binMsq = (respondentBin.length()==0) ?" Бин орг. пустой! ":respondentBin;
                    ParentChildRespondentJson parentChildRespondentJson= configClient.getChildRespondent(respondentBin);
                    if(parentChildRespondentJson!=null) {
                        if (!parentChildRespondentJson.parentRespondent.bin.equals(info.getBin())) {
                            throw new UsciException("Бин не совпадает!!! -> " + binMsq + " <> " + info.getBin());
                        }
                    }
                        /*if (!parentChildRespondentJson.childRespondent.bin.equals(respondentBin)){
                            throw new UsciException("Бин не совпадает!!! -> " + respondentBin+" <> "+ parentChildRespondentJson.childRespondent.bin);
                        }*/
                    else if(!respondentBin.equals(info.getBin())) {
                       throw new UsciException("Бин не совпадает!!! -> " + binMsq+" <> "+ info.getBin());
                     }
                            //binParent = parentChildRespondentJson.parentRespondent.bin;

                    //checker.checkAndUpdate(batch,binParent);
                    //batchService.signBatch(batch.getId(), batch.getSignature(), batch.getInformation(), batch.getSigningTime(), batchIds);
                    LocalDateTime signTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
                    batch.setSigningTime(signTime);
                    batch.setInformation(info.getPrincipal());
                    String base64Signature = Base64.getEncoder().encodeToString(batch.getSignature().getBytes());
                    batchService.signBatch(batch.getId(), /*batch.getSignature()*/base64Signature, batch.getInformation(), batch.getSigningTime(), batchIds);
                    batchReceiver.processBatch(batch.getId());
                } catch (UsciException e) {
                    logger.error("По батчу обнаружены ошибки id = {}, ошибка = {}", batch.getId(), e);

                    batchService.addBatchStatus(new BatchStatus()
                            .setBatchId(batch.getId())
                            .setStatus(BatchStatusType.ERROR)
                            .setText(e.getMessage())
                            .setExceptionTrace(e != null? ExceptionUtils.getStackTrace(e): null)
                            .setReceiptDate(LocalDateTime.now()));

                    batchService.endBatch(batch.getId());
                }
            }
        }
    }

    @PostMapping(value = "cancelBatch")
    public void cancelBatch(@RequestParam(name = "batchIds") List<Long> batchIds) {
        for (Long batchId : batchIds) {
            batchService.cancelBatch(batchId);
        }
    }

    @PostMapping(value = "removeBatchFromQueue")
    public void removeBatchFromQueue(@RequestParam(name = "batchId") Long batchId) {
        batchQueueHolder.removeBatch(batchId);
    }

    @GetMapping(value = "getBatchListToApprove")
    public List<BatchJson> getBatchListToApprove(@RequestParam(name = "productId") Long productId,
                                                 @RequestParam(name = "respondentId") Long respondentId) {
        return batchJsonService.getBatchListToApprove(productId, respondentId);
    }
    @PostMapping(value = "checkSignatureWs")
    public Map<String, String>  checkSignatureWs(@RequestParam(name = "signature") String signature){
        SignatureInfo info = PkiValidator.validate(signature,null,KeyUsage.SIGN);
        Map<String, String> result = new HashMap<>();
        result.put("bin", info.getBin());
        result.put("iin", info.getIin());
        result.put("principal", info.getPrincipal());
        result.put("sing", info.getSing());
        return  result;
    }
    @GetMapping(value = "getBatchListWs")
    public List<Protocol>  getBatchListWs(@RequestParam(name = "respondentId") Long respondentId,
                                          @RequestParam(name = "productId") Long productId,
                                          @RequestParam(name = "userId") Long userId,
                                          @RequestParam(name = "reportDate")  @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate) {
        return batchJsonService.getBatchListWs(respondentId,productId, userId, reportDate);
    }
    @GetMapping(value = "getEntityErrorList")
    public List<EntityError> getEntityErrorList(@RequestParam(name = "respondentId") Long respondentId,
                                                @RequestParam(name = "productId") Long productId,
                                                @RequestParam(name = "userId") Long userId,
                                                @RequestParam(name = "reportDate")  @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate reportDate) {
       return batchJsonService.getBatchStatusWsList(reportDate,respondentId,productId,userId);
    }
}