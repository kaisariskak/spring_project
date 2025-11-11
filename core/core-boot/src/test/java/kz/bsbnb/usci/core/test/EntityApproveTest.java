package kz.bsbnb.usci.core.test;

import kz.bsbnb.usci.eav.model.base.BaseEntityJson;
import kz.bsbnb.usci.eav.service.BaseEntityApprovalService;
import kz.bsbnb.usci.eav.service.BaseEntityLoadXmlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityApproveTest {

    @Autowired
    BaseEntityApprovalService baseEntityApprovalService;
    @Autowired
    BaseEntityLoadXmlService baseEntityLoadXmlService;

    List<Long> idsList = new ArrayList<Long>(Arrays.asList(1142165L));   //637130
    //947272
    //947271
    //947270
    //947269
    //947268
    //947267
    //947266
//
//583417
    @Test
    public  void test0() {

        for (Long batchId:idsList) {

            List<BaseEntityJson> baseEntityList = baseEntityLoadXmlService.loadEntityForApproval(batchId);


            baseEntityApprovalService.approveEntityMaintenance(baseEntityList, batchId , 10502L);


            System.out.println("END OF FILE" + batchId);
        }
    }

    @Test
    public  void testCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd: HH:mm:ss");
        OffsetDateTime offsetDateTime;
        offsetDateTime = OffsetDateTime.now(ZoneOffset.UTC);
        LocalDateTime now = LocalDateTime.now();


        TimeZone zone  = Calendar.getInstance().getTimeZone();  //TimeZone.getTimeZone("Asia/Aktau");
        System.out.println("CURERENT DATE "+ dtf.format(now));
        System.out.println("LocalDateTime DATE "+ LocalDateTime.now());

        /*Instant instant = Instant.now();
        ZoneId z = ZoneId.of("Asia/Atyrau");
        ZonedDateTime zdt = instant.atZone(z);
        System.out.println("instant "+ zdt);*/

        System.out.println("ZoneId  DATE "+ ZoneId.systemDefault());



    }
}


