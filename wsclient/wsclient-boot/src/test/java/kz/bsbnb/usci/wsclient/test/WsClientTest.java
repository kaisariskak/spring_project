package kz.bsbnb.usci.wsclient.test;

import gbdfm.RequestData;
import gbdfm.ResponseDataType;
import gbdul.kz.bee.bip.SyncChannel.v10.Types.Response.SyncSendMessageResponse;
import kz.bsbnb.usci.util.SqlJdbcConverter;
import kz.bsbnb.usci.wsclient.client.KGDSOAPClient;
import kz.bsbnb.usci.wsclient.jaxb.ctr.*;
import kz.bsbnb.usci.wsclient.model.ctrkgd.Request;
import kz.bsbnb.usci.wsclient.model.gbdul.GBDULEntity;
import kz.bsbnb.usci.wsclient.service.GBDFamilyService;
import kz.bsbnb.usci.wsclient.service.GBDULService;
import kz.bsbnb.usci.wsclient.service.KGDService;
import kz.bsbnb.usci.wsclient.service.NSIService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsClientTest {

    private static final Logger logger = LoggerFactory.getLogger(WsClientTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NSIService nsiService;
    @Autowired
    KGDService kgdService;
    @Autowired
    GBDULService gbdulService;
    @Autowired
    KGDSOAPClient kgdsoapClient;
    @Autowired
    GBDFamilyService gbdfmService;

    @Test
    public void test0() {
        LocalDate begDate = LocalDate.of(2019,8,2);
        LocalDate endDate = LocalDate.of(2019,8,10);
        nsiService.saveCurrencyRates(begDate,endDate);

    }

    @Test
    public void testGBD() {
        String userId = "test][Test][Test][GKB";
        String nbBin = "941240001151";
        String testBin = "000140000131";
        List<String> binList = gbdulService.getBinList();
        String messageId = "{01DADD89-88CD-4FCE-9999-2E8FC3D552BD}";
        ForkJoinPool forkJoinPool = null;
        try {
            synchronized (binList) {
                forkJoinPool = new ForkJoinPool(10);
                forkJoinPool.submit(() ->

                        binList.parallelStream().forEach(bin -> {
                            SyncSendMessageResponse response = gbdulService.sendMessage(userId, messageId, nbBin, bin);
                            GBDULEntity gbdulEntity = new GBDULEntity();
                            gbdulEntity.setBin(bin);
                            if (response.getResponseData().getData().getResponse().getStatus().getCode().equals("002")) {
                                gbdulEntity.setFullName(response.getResponseData().getData().getResponse().getOrganization().getFullNameRu());
                                if (response.getResponseData().getData().getResponse().getOrganization().getEnterpriseSubject() != null) {
                                    if (response.getResponseData().getData().getResponse().getOrganization().getEnterpriseSubject()) {
                                        gbdulEntity.setPrivateEnterpriseTypeCode(response.getResponseData().getData().getResponse().getOrganization().getPrivateEnterpriseType().getCode());
                                        gbdulEntity.setPrivateEnterpriseTypeNameRu(response.getResponseData().getData().getResponse().getOrganization().getPrivateEnterpriseType().getNameRu());
                                        gbdulEntity.setPrivateEnterpriseTypeNameKz(response.getResponseData().getData().getResponse().getOrganization().getPrivateEnterpriseType().getNameKz());
                                    }
                                }
                            }
                            gbdulEntity.setRequestStatus(response.getResponseData().getData().getResponse().getStatus().getNameRu());
                            gbdulService.saveGBDULInfo(gbdulEntity);
                        })

                ).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (forkJoinPool != null) {
                forkJoinPool.shutdown(); //always remember to shutdown the pool
            }
        }

        //SyncSendMessageResponse response = gbdulService.sendMessage(userId, messageId, nbBin, testBin);
        //System.out.println(response.getResponseData().getData().getResponse().getOrganization().getPrivateEnterpriseType().getNameRu());

    }

    @Test
    public void test2() {
        try {
            kgdService.testRequestKgd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        LocalDate localDate = LocalDate.of(2020,9,20);
        //kgdService.ctrRequest(localDate, null,1,false);
    }

    @Test
    public void test4() {
        LocalDate begDate = LocalDate.of(2021,1,1);
        LocalDate endDate = LocalDate.of(2021,12,31);
        nsiService.saveHolidayDates(begDate,endDate);

    }



    @Test
    public void test5() {
        LocalDate now = LocalDate.now();
        System.out.println("ldtnow " + now.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        //System.out.println("ldtnow Localdate "+ Instant.ofEpochMilli().atZone(ZoneId.systemDefault()).toLocalDate());
        System.out.println("ldt " + LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println("ldt Localdatetime" + LocalDateTime.ofInstant(Instant.ofEpochMilli(now.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()),ZoneId.systemDefault()));
        System.out.println("ctm " + System.currentTimeMillis());

    }

    @Test
    public void testInsert() {
        LocalDate sendDate = LocalDate.of(2024,03,19);
        LocalDate reportDate = LocalDate.of(2024,02,1);  //01.08.2019

        kgdService.ctrRequest(sendDate, reportDate,null,2,false);
    }


    @Test
    public void testOneCtr() {
        ObjectFactory objectFactory = new ObjectFactory();
        Entities entities = new Entities();

        List<CtrTransaction> ctrTransactionList = new ArrayList<>();
        CtrTransaction ctrTransaction = new CtrTransaction();

        ctrTransaction.setCurrTransDate(objectFactory.createCtrTransactionCurrTransDate("20.08.2019"));
        ctrTransaction.setReference("9074590152201092019");
        ctrTransaction.setContSum(objectFactory.createCtrTransactionContSum(SqlJdbcConverter.convertToDouble(new BigDecimal(70))));
        ctrTransaction.setContNum(objectFactory.createCtrTransactionContNum("100"));
        ctrTransaction.setContDate(objectFactory.createCtrTransactionContDate("27.06.2019"));
        ctrTransaction.setContRegNum(objectFactory.createCtrTransactionContRegNum("1/459/2010/8760"));

        CtrSubject beneficiary = new CtrSubject();
        RefCountry refCountry = new RefCountry();
        refCountry.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("DE"));
        RefResidency refResidency = new RefResidency();
        refResidency.setCode("2");
        beneficiary.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidency));
        RefEconSector refEconSector = new RefEconSector();
        refEconSector.setCode("7");
        beneficiary.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSector));
        beneficiary.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountry));
        beneficiary.setName("DFH");
        beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(""));
        //beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("BENEFICIARY_BINIIN"))));
        ctrTransaction.setBeneficiary(objectFactory.createCtrTransactionBeneficiary(beneficiary));

        CtrSubject sender = new CtrSubject();
        RefCountry refCountryS = new RefCountry();
        refCountryS.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("KZ"));
        RefResidency refResidencyS = new RefResidency();
        refResidencyS.setCode("1");
        RefEconSector refEconSectorS = new RefEconSector();
        refEconSectorS.setCode("7");
        sender.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountryS));
        sender.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidencyS));
        sender.setName("ABC");
        sender.setBinIin(objectFactory.createCtrSubjectBinIin("970740001482"));
        //sender.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("SENDER_BINIIN"))));
        sender.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSectorS));
        ctrTransaction.setSender(objectFactory.createCtrTransactionSender(sender));

        RefCurrTransPpc refCurrTransPpc = new RefCurrTransPpc();
        refCurrTransPpc.setCode("710");
        ctrTransaction.setRefCurrTransPpc(objectFactory.createCtrTransactionRefCurrTransPpc(refCurrTransPpc));

        RefCurrency refCurrency = new RefCurrency();
        refCurrency.setCode(objectFactory.createRefCurrencyCode("USD"));
        refCurrency.setShortName(objectFactory.createRefCurrencyShortName("EUR"));
        ctrTransaction.setRefCurrency(objectFactory.createCtrTransactionRefCurrency(refCurrency));

        ctrTransactionList.add(ctrTransaction);

        entities.getCtrTransaction().addAll(ctrTransactionList);

        try {
            Request response = kgdsoapClient.ctrRequest(entities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLess50000() {
        ObjectFactory objectFactory = new ObjectFactory();
        Entities entities = new Entities();

        List<CtrTransaction> ctrTransactionList = new ArrayList<>();
        CtrTransaction ctrTransaction = new CtrTransaction();

        ctrTransaction.setCurrTransDate(objectFactory.createCtrTransactionCurrTransDate("20.07.2019"));
        ctrTransaction.setReference("9074590152201082019");
        ctrTransaction.setContSum(objectFactory.createCtrTransactionContSum(SqlJdbcConverter.convertToDouble(new BigDecimal(5))));
        ctrTransaction.setContNum(objectFactory.createCtrTransactionContNum("100"));
        ctrTransaction.setContDate(objectFactory.createCtrTransactionContDate("27.06.2019"));
        ctrTransaction.setContRegNum(objectFactory.createCtrTransactionContRegNum("1/459/2010/8760"));

        CtrSubject beneficiary = new CtrSubject();
        RefCountry refCountry = new RefCountry();
        refCountry.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("DE"));
        RefResidency refResidency = new RefResidency();
        refResidency.setCode("2");
        beneficiary.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidency));
        RefEconSector refEconSector = new RefEconSector();
        refEconSector.setCode("7");
        beneficiary.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSector));
        beneficiary.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountry));
        beneficiary.setName("DFH");
        beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(""));
        //beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("BENEFICIARY_BINIIN"))));
        ctrTransaction.setBeneficiary(objectFactory.createCtrTransactionBeneficiary(beneficiary));

        CtrSubject sender = new CtrSubject();
        RefCountry refCountryS = new RefCountry();
        refCountryS.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("KZ"));
        RefResidency refResidencyS = new RefResidency();
        refResidencyS.setCode("1");
        RefEconSector refEconSectorS = new RefEconSector();
        refEconSectorS.setCode("7");
        sender.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountryS));
        sender.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidencyS));
        sender.setName("ABC");
        sender.setBinIin(objectFactory.createCtrSubjectBinIin("970740001482"));
        //sender.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("SENDER_BINIIN"))));
        sender.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSectorS));
        ctrTransaction.setSender(objectFactory.createCtrTransactionSender(sender));

        RefCurrTransPpc refCurrTransPpc = new RefCurrTransPpc();
        refCurrTransPpc.setCode("710");
        ctrTransaction.setRefCurrTransPpc(objectFactory.createCtrTransactionRefCurrTransPpc(refCurrTransPpc));

        RefCurrency refCurrency = new RefCurrency();
        refCurrency.setCode(objectFactory.createRefCurrencyCode("EUR"));
        refCurrency.setShortName(objectFactory.createRefCurrencyShortName("EUR"));
        ctrTransaction.setRefCurrency(objectFactory.createCtrTransactionRefCurrency(refCurrency));

        ctrTransactionList.add(ctrTransaction);

        entities.getCtrTransaction().addAll(ctrTransactionList);

        try {
            Request response = kgdsoapClient.ctrRequest(entities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInvalidXSD() {
        ObjectFactory objectFactory = new ObjectFactory();
        Entities entities = new Entities();

        List<CtrTransaction> ctrTransactionList = new ArrayList<>();
        CtrTransaction ctrTransaction = new CtrTransaction();

        ctrTransaction.setCurrTransDate(objectFactory.createCtrTransactionCurrTransDate("20.07.2019"));
        ctrTransaction.setContSum(objectFactory.createCtrTransactionContSum(SqlJdbcConverter.convertToDouble(new BigDecimal(73))));
        ctrTransaction.setContNum(objectFactory.createCtrTransactionContNum("100"));
        ctrTransaction.setContDate(objectFactory.createCtrTransactionContDate("27.06.2019"));
        ctrTransaction.setContRegNum(objectFactory.createCtrTransactionContRegNum("1/459/2010/8760"));

        CtrSubject beneficiary = new CtrSubject();
        RefCountry refCountry = new RefCountry();
        refCountry.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("DE"));
        RefResidency refResidency = new RefResidency();
        refResidency.setCode("2");
        beneficiary.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidency));
        RefEconSector refEconSector = new RefEconSector();
        refEconSector.setCode("7");
        beneficiary.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSector));
        beneficiary.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountry));
        beneficiary.setName("DFH");
        beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(""));
        //beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("BENEFICIARY_BINIIN"))));
        ctrTransaction.setBeneficiary(objectFactory.createCtrTransactionBeneficiary(beneficiary));

        CtrSubject sender = new CtrSubject();
        RefCountry refCountryS = new RefCountry();
        refCountryS.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("KZ"));
        RefResidency refResidencyS = new RefResidency();
        refResidencyS.setCode("1");
        RefEconSector refEconSectorS = new RefEconSector();
        refEconSectorS.setCode("7");
        sender.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountryS));
        sender.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidencyS));
        sender.setName("ABC");
        sender.setBinIin(objectFactory.createCtrSubjectBinIin("970740001482"));
        //sender.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("SENDER_BINIIN"))));
        sender.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSectorS));
        ctrTransaction.setSender(objectFactory.createCtrTransactionSender(sender));

        RefCurrTransPpc refCurrTransPpc = new RefCurrTransPpc();
        refCurrTransPpc.setCode("710");
        ctrTransaction.setRefCurrTransPpc(objectFactory.createCtrTransactionRefCurrTransPpc(refCurrTransPpc));

        RefCurrency refCurrency = new RefCurrency();
        refCurrency.setCode(objectFactory.createRefCurrencyCode("EUR"));
        refCurrency.setShortName(objectFactory.createRefCurrencyShortName("EUR"));
        ctrTransaction.setRefCurrency(objectFactory.createCtrTransactionRefCurrency(refCurrency));

        ctrTransactionList.add(ctrTransaction);

        entities.getCtrTransaction().addAll(ctrTransactionList);

        try {
            Request response = kgdsoapClient.ctrRequest(entities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testManyCtrRequests() {
        ObjectFactory objectFactory = new ObjectFactory();
        for (int i=1;i<1000;i++) {
            Entities entities = new Entities();

            List<CtrTransaction> ctrTransactionList = new ArrayList<>();
            CtrTransaction ctrTransaction = new CtrTransaction();

            ctrTransaction.setCurrTransDate(objectFactory.createCtrTransactionCurrTransDate("20.07.2019"));
            ctrTransaction.setReference("9074590152201082019");
            ctrTransaction.setContSum(objectFactory.createCtrTransactionContSum(SqlJdbcConverter.convertToDouble(new BigDecimal(73))));
            ctrTransaction.setContNum(objectFactory.createCtrTransactionContNum("100"));
            ctrTransaction.setContDate(objectFactory.createCtrTransactionContDate("27.06.2019"));
            ctrTransaction.setContRegNum(objectFactory.createCtrTransactionContRegNum("1/459/2010/8760"));

            CtrSubject beneficiary = new CtrSubject();
            RefCountry refCountry = new RefCountry();
            refCountry.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("DE"));
            RefResidency refResidency = new RefResidency();
            refResidency.setCode("2");
            beneficiary.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidency));
            RefEconSector refEconSector = new RefEconSector();
            refEconSector.setCode("7");
            beneficiary.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSector));
            beneficiary.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountry));
            beneficiary.setName("DFH");
            beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(""));
            //beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("BENEFICIARY_BINIIN"))));
            ctrTransaction.setBeneficiary(objectFactory.createCtrTransactionBeneficiary(beneficiary));

            CtrSubject sender = new CtrSubject();
            RefCountry refCountryS = new RefCountry();
            refCountryS.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("KZ"));
            RefResidency refResidencyS = new RefResidency();
            refResidencyS.setCode("1");
            RefEconSector refEconSectorS = new RefEconSector();
            refEconSectorS.setCode("7");
            sender.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountryS));
            sender.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidencyS));
            sender.setName("ABC");
            sender.setBinIin(objectFactory.createCtrSubjectBinIin("970740001482"));
            //sender.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("SENDER_BINIIN"))));
            sender.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSectorS));
            ctrTransaction.setSender(objectFactory.createCtrTransactionSender(sender));

            RefCurrTransPpc refCurrTransPpc = new RefCurrTransPpc();
            refCurrTransPpc.setCode("710");
            ctrTransaction.setRefCurrTransPpc(objectFactory.createCtrTransactionRefCurrTransPpc(refCurrTransPpc));

            RefCurrency refCurrency = new RefCurrency();
            refCurrency.setCode(objectFactory.createRefCurrencyCode("EUR"));
            refCurrency.setShortName(objectFactory.createRefCurrencyShortName("EUR"));
            ctrTransaction.setRefCurrency(objectFactory.createCtrTransactionRefCurrency(refCurrency));

            ctrTransactionList.add(ctrTransaction);

            entities.getCtrTransaction().addAll(ctrTransactionList);

            try {
                Request response = kgdsoapClient.ctrRequest(entities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testRequestManyCtr() {
        ObjectFactory objectFactory = new ObjectFactory();
        Entities entities = new Entities();

        List<CtrTransaction> ctrTransactionList = new ArrayList<>();

        for (int i=1;i<9800;i++) {
            CtrTransaction ctrTransaction = new CtrTransaction();

            ctrTransaction.setCurrTransDate(objectFactory.createCtrTransactionCurrTransDate("20.07.2019"));
            ctrTransaction.setReference("9074690152201082019");
            ctrTransaction.setContSum(objectFactory.createCtrTransactionContSum(SqlJdbcConverter.convertToDouble(new BigDecimal(73))));
            ctrTransaction.setContNum(objectFactory.createCtrTransactionContNum("100"));
            ctrTransaction.setContDate(objectFactory.createCtrTransactionContDate("27.06.2019"));
            ctrTransaction.setContRegNum(objectFactory.createCtrTransactionContRegNum("1/459/2010/8760"));

            CtrSubject beneficiary = new CtrSubject();
            RefCountry refCountry = new RefCountry();
            refCountry.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("DE"));
            RefResidency refResidency = new RefResidency();
            refResidency.setCode("2");
            beneficiary.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidency));
            RefEconSector refEconSector = new RefEconSector();
            refEconSector.setCode("7");
            beneficiary.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSector));
            beneficiary.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountry));
            beneficiary.setName("DFH");
            beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(""));
            //beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("BENEFICIARY_BINIIN"))));
            ctrTransaction.setBeneficiary(objectFactory.createCtrTransactionBeneficiary(beneficiary));

            CtrSubject sender = new CtrSubject();
            RefCountry refCountryS = new RefCountry();
            refCountryS.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("KZ"));
            RefResidency refResidencyS = new RefResidency();
            refResidencyS.setCode("1");
            RefEconSector refEconSectorS = new RefEconSector();
            refEconSectorS.setCode("7");
            sender.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountryS));
            sender.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidencyS));
            sender.setName("ABC");
            sender.setBinIin(objectFactory.createCtrSubjectBinIin("970740001482"));
            //sender.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("SENDER_BINIIN"))));
            sender.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSectorS));
            ctrTransaction.setSender(objectFactory.createCtrTransactionSender(sender));

            RefCurrTransPpc refCurrTransPpc = new RefCurrTransPpc();
            refCurrTransPpc.setCode("710");
            ctrTransaction.setRefCurrTransPpc(objectFactory.createCtrTransactionRefCurrTransPpc(refCurrTransPpc));

            RefCurrency refCurrency = new RefCurrency();
            refCurrency.setCode(objectFactory.createRefCurrencyCode("EUR"));
            refCurrency.setShortName(objectFactory.createRefCurrencyShortName("EUR"));
            ctrTransaction.setRefCurrency(objectFactory.createCtrTransactionRefCurrency(refCurrency));

            ctrTransactionList.add(ctrTransaction);
        }

        entities.getCtrTransaction().addAll(ctrTransactionList);

        try {
            Request response = kgdsoapClient.ctrRequest(entities);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testManyRequestsWithManyCtr() {
        for (int i=1;i<1000;i++) {
            ObjectFactory objectFactory = new ObjectFactory();
            Entities entities = new Entities();

            List<CtrTransaction> ctrTransactionList = new ArrayList<>();

            for (int j=1;j<3500;j++) {
                CtrTransaction ctrTransaction = new CtrTransaction();

                ctrTransaction.setCurrTransDate(objectFactory.createCtrTransactionCurrTransDate("20.07.2019"));
                ctrTransaction.setReference("9074790152201082019");
                ctrTransaction.setContSum(objectFactory.createCtrTransactionContSum(SqlJdbcConverter.convertToDouble(new BigDecimal(73))));
                ctrTransaction.setContNum(objectFactory.createCtrTransactionContNum("100"));
                ctrTransaction.setContDate(objectFactory.createCtrTransactionContDate("27.06.2019"));
                ctrTransaction.setContRegNum(objectFactory.createCtrTransactionContRegNum("1/459/2010/8760"));

                CtrSubject beneficiary = new CtrSubject();
                RefCountry refCountry = new RefCountry();
                refCountry.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("DE"));
                RefResidency refResidency = new RefResidency();
                refResidency.setCode("2");
                beneficiary.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidency));
                RefEconSector refEconSector = new RefEconSector();
                refEconSector.setCode("7");
                beneficiary.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSector));
                beneficiary.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountry));
                beneficiary.setName("DFH");
                beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(""));
                //beneficiary.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("BENEFICIARY_BINIIN"))));
                ctrTransaction.setBeneficiary(objectFactory.createCtrTransactionBeneficiary(beneficiary));

                CtrSubject sender = new CtrSubject();
                RefCountry refCountryS = new RefCountry();
                refCountryS.setCodeAlpha2(objectFactory.createRefCountryCodeAlpha2("KZ"));
                RefResidency refResidencyS = new RefResidency();
                refResidencyS.setCode("1");
                RefEconSector refEconSectorS = new RefEconSector();
                refEconSectorS.setCode("7");
                sender.setRefCountry(objectFactory.createCtrSubjectRefCountry(refCountryS));
                sender.setRefResidency(objectFactory.createCtrSubjectRefResidency(refResidencyS));
                sender.setName("ABC");
                sender.setBinIin(objectFactory.createCtrSubjectBinIin("970740001482"));
                //sender.setBinIin(objectFactory.createCtrSubjectBinIin(String.valueOf(row.get("SENDER_BINIIN"))));
                sender.setRefEconSector(objectFactory.createCtrSubjectRefEconSector(refEconSectorS));
                ctrTransaction.setSender(objectFactory.createCtrTransactionSender(sender));

                RefCurrTransPpc refCurrTransPpc = new RefCurrTransPpc();
                refCurrTransPpc.setCode("710");
                ctrTransaction.setRefCurrTransPpc(objectFactory.createCtrTransactionRefCurrTransPpc(refCurrTransPpc));

                RefCurrency refCurrency = new RefCurrency();
                refCurrency.setCode(objectFactory.createRefCurrencyCode("EUR"));
                refCurrency.setShortName(objectFactory.createRefCurrencyShortName("EUR"));
                ctrTransaction.setRefCurrency(objectFactory.createCtrTransactionRefCurrency(refCurrency));

                ctrTransactionList.add(ctrTransaction);
            }

            entities.getCtrTransaction().addAll(ctrTransactionList);

            try {
                Request response = kgdsoapClient.ctrRequest(entities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void testGBDFm() {
        String userId = "GKB][GKB][GKB][";
        String iin = "890807300239";
        ResponseDataType response = gbdfmService.sendMessage(userId, iin);
        System.out.println(response);

    }


    @Test
    public void testGReSend() {
        //ResponseDataType response = kgdService.ctrRequest(userId, iin);
        //System.out.println(response);
    //01.04.2022
        LocalDate sendDate = LocalDate.of(2024,9,19);
        LocalDate reportDate = LocalDate.of(2024,8,1);  //01.08.2019


         kgdService.ctrRequest(sendDate, reportDate,null,1,false);
    }

}
