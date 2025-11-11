package kz.bsbnb.usci.wsclient.test;


import kz.bsbnb.usci.wsclient.service.NSIService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HolidayTest {

    private static final Logger logger = LoggerFactory.getLogger(WsClientTest.class);

    @Autowired
    NSIService nsiService;

    @Test
    public void test0() {
        LocalDate begDate = LocalDate.of(2023,7,4);
        LocalDate endDate = LocalDate.of(2023,7,4);
        nsiService.saveHolidayDates(begDate,endDate);

    }
    @Test
    public void testCourse() {
        LocalDate begDate = LocalDate.of(2020,7,5);
        LocalDate endDate = LocalDate.of(2020,7,7);
        nsiService.saveCurrencyRates(begDate,endDate);

    }
}

