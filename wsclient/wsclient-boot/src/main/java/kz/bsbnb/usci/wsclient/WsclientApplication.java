package kz.bsbnb.usci.wsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "kz.bsbnb.usci" )
@EnableFeignClients(basePackages = {"kz.bsbnb.usci.mail.client","kz.bsbnb.usci.core.client"})
@EnableConfigurationProperties
@EnableEurekaClient
public class WsclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsclientApplication.class, args);
    }

}
