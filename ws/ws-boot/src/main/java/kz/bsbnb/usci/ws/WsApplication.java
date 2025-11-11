package kz.bsbnb.usci.ws;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "kz.bsbnb.usci" )
@EnableFeignClients(basePackages = {"kz.bsbnb.usci.core.client", "kz.bsbnb.usci.eav.client", "kz.bsbnb.usci.util.client", "kz.bsbnb.usci.receiver.client","kz.bsbnb.usci.report.client"})
@EnableConfigurationProperties
@EnableEurekaClient
@OpenAPIDefinition(
        info = @Info(
                title = "Депозитный Регистр (DEPREG) REST API",
                version = "1.0.0",
                description = "API для взаимодействия с системой Депозитного регистра\n\n" +
                       " Основные возможности:\n" +
                        "- Получение токена авторизации\n" +
                        "- Загрузка данных о депозитах с ЭЦП\n" +
                        "- Получение протоколов загрузки\n" +
                        "- Формирование и получение отчетов\n" +
                        "- Формирование и получение МФК \n" +
                        "- Подтверждение отчетной даты"
        ),
        servers = {
                @Server(
                        url = "https://depregtest.nationalbank.kz",
                        description = "Тестовый сервер Депозитного регистра"
                )/*,
                @Server(
                        url = "https://depreg.nationalbank.kz",
                        description = "Промышленный сервер Депозитного регистра"
                )*/
        }/*,
        tags = {
                @Tag(name = "Авторизация", description = "Операции получения токена доступа"),
                @Tag(name = "Загрузка данных", description = "Операции загрузки данных с ЭЦП"),
                @Tag(name = "Протоколы", description = "Получение протоколов и ошибок загрузки"),
                @Tag(name = "Отчеты", description = "Формирование и получение отчетов")
        }*/
)
public class WsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }

}
