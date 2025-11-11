package kz.bsbnb.usci.jobstore;

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
@EnableFeignClients(basePackages = {"kz.bsbnb.usci.wsclient.client"})
@EnableConfigurationProperties
@EnableScheduling
@EnableEurekaClient
public class JobstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobstoreApplication.class, args);
    }

    @Bean
    TaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

}
