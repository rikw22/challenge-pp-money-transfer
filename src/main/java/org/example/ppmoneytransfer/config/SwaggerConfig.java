package org.example.ppmoneytransfer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {

    @Bean
    public OpenAPI openApiConfig(){
        return new OpenAPI()
                .info(
                        new Info().title("PP Money Transfer")
                                .description("Webservice that deals with money transfer")
                                .version("v0.1"));

    }
}
