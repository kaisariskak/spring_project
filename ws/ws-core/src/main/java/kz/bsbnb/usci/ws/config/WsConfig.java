package kz.bsbnb.usci.ws.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WsConfig {

    @Bean
    public OpenAPI usciOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DEPOSIT REGISTRY  REST API")
                        .description("API для передачи данных в DEPOSIT REGISTRY")
                        .version("1.0.0"));
    }

}
