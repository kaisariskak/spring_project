package kz.bsbnb.usci.wsclient.service;

import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.wsclient.client.KGDSOAPClient;
import kz.bsbnb.usci.wsclient.dao.KGDDao;
import kz.bsbnb.usci.wsclient.jaxb.ctr.*;
import kz.bsbnb.usci.wsclient.jaxb.kgd.ResponseMessage;
import kz.bsbnb.usci.wsclient.model.ctrkgd.Request;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class KGDServiceImpl implements KGDService {
    private static final Logger logger = LoggerFactory.getLogger(KGDServiceImpl.class);

    private final KGDDao kgdDao;
    private final KGDSOAPClient kgdsoapClient;

    public KGDServiceImpl(KGDDao kgdDao, KGDSOAPClient kgdsoapClient) {
        this.kgdDao = kgdDao;
        this.kgdsoapClient = kgdsoapClient;
    }

    @Override
    public void testRequestKgd() {
        try {
            ResponseMessage response = kgdsoapClient.testResponseMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void ctrRequest(LocalDate sendDate, LocalDate reportDate, Long id, int operationId, boolean isUpdate) {

        LocalDateTime datePeriod = sendDate.atStartOfDay();
        List<Map<String, Object>> entities = kgdDao.entityRows(reportDate, datePeriod, operationId);
       final AtomicInteger counter = new AtomicInteger();
        if (!isUpdate) {
            if (entities.size() > 0) {
                if (entities.size() > 2000) {
                    final Collection<List<Map<String, Object>>> entitiespart = entities.stream()
                            .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 2000))
                            .values();
                    logger.info("entitiespart ",entitiespart);


                    for( List<Map<String, Object>> map : entitiespart){

                        logger.info("Started request");
                        Request ctrRequest = kgdsoapClient.ctrRequest(getCtrList(map));
                        logger.info("end of Started request");
                        ctrRequest.setEntitiesCount(Long.valueOf(map.size()));
                        ctrRequest.setReportDate(reportDate);
                        ctrRequest.setSendDate(sendDate);
                        ctrRequest.setOperationId(operationId);
                        kgdDao.insertRequest(ctrRequest);
                        logger.info("inserted request");


                    }
                    /*entitiespart.forEach(t -> {
                        logger.info("Started request");
                        Request ctrRequest = kgdsoapClient.ctrRequest(getCtrList(t));
                        logger.info("end of Started request");
                        ctrRequest.setEntitiesCount(Long.valueOf(t.size()));
                        ctrRequest.setReportDate(reportDate);
                        ctrRequest.setSendDate(sendDate);
                        ctrRequest.setOperationId(operationId);
                        kgdDao.insertRequest(ctrRequest);
                        logger.info("inserted request");

                    });*/

                } else {
                    logger.info("Started request");
                    Request ctrRequest = kgdsoapClient.ctrRequest(getCtrList(entities));
                    logger.info("end of Started request");
                    ctrRequest.setEntitiesCount(Long.valueOf(entities.size()));
                    ctrRequest.setReportDate(reportDate);
                    ctrRequest.setSendDate(sendDate);
                    ctrRequest.setOperationId(operationId);
                    kgdDao.insertRequest(ctrRequest);
                    logger.info("inserted request");
                }
            }
        } else {
            Request ctrRequest = kgdsoapClient.ctrRequest(getCtrList(entities));
            ctrRequest.setEntitiesCount(Long.valueOf(entities.size()));
            ctrRequest.setId(id);
            ctrRequest.setReportDate(reportDate);
            ctrRequest.setOperationId(operationId);
            kgdDao.updateRequest(ctrRequest);
        }
    }

    @Override
    public List<Request> getCtrRequestList(LocalDate reportDate) {
        return kgdDao.getCtrRequestList(reportDate);
    }

    private static Entities getCtrList(List<Map<String, Object>> rows) {
        ObjectFactory objectFactory = new ObjectFactory();
        Entities entities = new Entities();

        List<CtrTransaction> ctrTransactionList = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            CtrTransaction ctrTransaction = new CtrTransaction();
            if (!SqlJdbcConverter.convertObjectToString(row.get("MESSAGE_TYPE")).equals("DELETE")) {


                ctrTransaction.setCurrTransDate(objectFactory.createCtrTransactionCurrTransDate(SqlJdbcConverter.convertToString(SqlJdbcConverter.convertToLocalDate(row.get("CURR_TRANS_DATE")))));
                ctrTransaction.setReference(SqlJdbcConverter.convertObjectToString(row.get("REFERENCE")));

                MessageType messageType = MessageType.valueOf(SqlJdbcConverter.convertObjectToString(row.get("MESSAGE_TYPE")));
                ctrTransaction.setMessageType(messageType);

                ctrTransaction.setCorrectionDate(objectFactory.createCtrTransactionCorrectionDate(SqlJdbcConverter.convertToString(SqlJdbcConverter.convertToLocalDate(row.get("CORRECTION_DATE")))));
                ctrTransaction.setContSum(objectFactory.createCtrTransactionContSum(SqlJdbcConverter.convertToDouble(row.get("CONT_SUM"))));
                ctrTransaction.setContNum(objectFactory.createCtrTransactionContNum(SqlJdbcConverter.convertObjectToString(row.get("CONT_NUM"))));
                ctrTransaction.setContDate(objectFactory.createCtrTransactionContDate(SqlJdbcConverter.convertToString(SqlJdbcConverter.convertToLocalDate(row.get("CONT_DATE")))));
                ctrTransaction.setContRegNum(objectFactory.createCtrTransactionContRegNum(SqlJdbcConverter.convertObjectToString(row.get("CONT_REG_NUM"))));

                CtrSubject beneficiary = new CtrSubject();
                RefCountry refCountry = new RefCountry();
                refCountry.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2(SqlJdbcConverter.convertObjectToString(row.get("BENEFICIARY_COUNTRY_CODE"))));
                RefResidency refResidency = new RefResidency();
                if (SqlJdbcConverter.convertObjectToString(row.get("BENEFICIARY_RESIDENCY_CODE")) != null) {
                    refResidency.setCode(SqlJdbcConverter.convertObjectToString(row.get("BENEFICIARY_RESIDENCY_CODE")));
                    beneficiary.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidency));
                }
                RefEconSector refEconSector = new RefEconSector();
                if (SqlJdbcConverter.convertObjectToString(row.get("BENEFICIARY_ECON_SECTOR_CODE")) != null) {
                    refEconSector.setCode(SqlJdbcConverter.convertObjectToString(row.get("BENEFICIARY_ECON_SECTOR_CODE")));
                    beneficiary.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSector));
                }
                beneficiary.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountry));
                beneficiary.setName(SqlJdbcConverter.convertObjectToString(row.get("BENEFICIARY_NAME")));
                beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(SqlJdbcConverter.convertObjectToString(row.get("BENEFICIARY_BINIIN"))));
                //beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("BENEFICIARY_BINIIN"))));
                ctrTransaction.setBeneficiary(objectFactory.createCtrTransactionBeneficiary(beneficiary));

                CtrSubject sender = new CtrSubject();
                RefCountry refCountryS = new RefCountry();
                refCountryS.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2(SqlJdbcConverter.convertObjectToString(row.get("SENDER_COUNTRY_CODE"))));
                RefResidency refResidencyS = new RefResidency();
                refResidencyS.setCode(SqlJdbcConverter.convertObjectToString(row.get("SENDER_RESIDENCY_CODE")));
                RefEconSector refEconSectorS = new RefEconSector();
                refEconSectorS.setCode(SqlJdbcConverter.convertObjectToString(row.get("SENDER_ECON_SECTOR_CODE")));
                sender.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountryS));
                sender.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidencyS));
                sender.setName(SqlJdbcConverter.convertObjectToString(row.get("SENDER_NAME")));
                sender.setBinIin(objectFactory.createCtrSubjectBinIin(SqlJdbcConverter.convertObjectToString(row.get("SENDER_BINIIN"))));
                //sender.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("SENDER_BINIIN"))));
                sender.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSectorS));
                ctrTransaction.setSender(objectFactory.createCtrTransactionSender(sender));

                RefCurrTransPpc refCurrTransPpc = new RefCurrTransPpc();
                refCurrTransPpc.setCode(SqlJdbcConverter.convertObjectToString(row.get("CURR_TRANS_PPC_CODE")));
                ctrTransaction.setRefCurrTransPpc(objectFactory.createCtrTransactionRefCurrTransPpc(refCurrTransPpc));

                RefCurrency refCurrency = new RefCurrency();
                refCurrency.setCode(objectFactory.createRefCurrencyCode(SqlJdbcConverter.convertObjectToString(row.get("CURRENCY_CODE"))));
                refCurrency.setShortName(objectFactory.createRefCurrencyShortName(SqlJdbcConverter.convertObjectToString(row.get("CURRENCY_NAME"))));
                ctrTransaction.setRefCurrency(objectFactory.createCtrTransactionRefCurrency(refCurrency));
            } else {
                ctrTransaction.setReference(SqlJdbcConverter.convertObjectToString(row.get("REFERENCE")));
                ctrTransaction.setCorrectionDate(objectFactory.createCtrTransactionCorrectionDate(SqlJdbcConverter.convertToString(SqlJdbcConverter.convertToLocalDate(row.get("CORRECTION_DATE")))));
                MessageType messageType = MessageType.valueOf(SqlJdbcConverter.convertObjectToString(row.get("MESSAGE_TYPE")));
                ctrTransaction.setMessageType(messageType);
            }

            ctrTransactionList.add(ctrTransaction);
        }

        entities.getCtrTransaction().addAll(ctrTransactionList);

        return entities;
    }
}
