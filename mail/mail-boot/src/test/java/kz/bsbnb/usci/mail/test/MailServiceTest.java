package kz.bsbnb.usci.mail.test;

import kz.bsbnb.usci.core.client.UserClient;
import kz.bsbnb.usci.mail.model.dto.MailMessageDto;
import kz.bsbnb.usci.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceTest.class);

    @Autowired
    private UserClient userClient;
    @Autowired
    private MailService mailService;

    @Test
    public void test0() {
        Map<String, String> params = new HashMap<>();
        params.put("STATUS", "Ошибка обновления курсов валют");

        MailMessageDto mailMessageDto = new MailMessageDto();
        mailMessageDto.setReceiver(userClient.getUser(152733L));
        mailMessageDto.setMailTemplate("NSI_CURRENCY_UPDATE");
        mailMessageDto.setParams(params);

        try {
            mailService.sendMail(mailMessageDto);
        } catch (Exception e) {
            logger.error(String.format("Ошибка отправки email %s", mailMessageDto), e);
        }
    }
}
