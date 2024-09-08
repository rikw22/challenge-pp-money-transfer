package org.example.ppmoneytransfer.config;

import feign.Retryer;
import org.example.ppmoneytransfer.client.AuthorizationServiceClient;
import org.example.ppmoneytransfer.client.NotificationServiceClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients(clients = {AuthorizationServiceClient.class, NotificationServiceClient.class})
public class FeignClientConfig {
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(
                100,                                 // Initial delay in milliseconds
                TimeUnit.SECONDS.toMillis(15L),     // Max delay
                5                                           // Max retries
        );
    }
}
