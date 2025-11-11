package kz.bsbnb.usci.core.test;

import kz.bsbnb.usci.eav.service.ProductService;
import kz.bsbnb.usci.model.batch.Approval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void test0() {
        System.out.println(productService.getProducts());
        System.out.println(productService.getProductById(1L));
        System.out.println(productService.findProductByCode("EEE"));
    }

    @Test
    public void testRead() {
        Approval approval = new Approval();
        approval.setClassId(97L);
        approval.setRespondentTypeId(1L);
        LocalDate localDate =  LocalDate.of(2020,4,1);
        LocalDateTime localDateTime = LocalDateTime.of(2020,4,10,0,0,0);
        approval.setReportDate(localDate);
        approval.setDeadLine(localDateTime);
        productService.updateApproval(approval);


    }
    @Test
    public void testCalcDeadLine() {
        Approval approval = new Approval();
        approval.setClassId(47L);
        approval.setRespondentTypeId(3L);
        productService.calcDeadLine("2020");



    }
}



