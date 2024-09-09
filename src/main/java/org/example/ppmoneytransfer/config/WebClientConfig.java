package org.example.ppmoneytransfer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Component
public class WebClientConfig {

    @Bean
    WebClient webclientConfig(WebClient.Builder builder){
        return builder.clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient.create().proxyWithSystemProperties()))
                .build();
    }
}
