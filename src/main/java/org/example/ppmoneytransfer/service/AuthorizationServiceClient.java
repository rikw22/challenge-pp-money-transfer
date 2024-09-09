package org.example.ppmoneytransfer.service;

import lombok.extern.slf4j.Slf4j;
import org.example.ppmoneytransfer.client.AuthorizationServiceResponse;
import org.example.ppmoneytransfer.exceptions.NotAuthorizedTransactionException;
import org.example.ppmoneytransfer.vo.TransferRequestVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class AuthorizationServiceClient {
    private final WebClient webclient;
    private final URI serviceUri;

    public AuthorizationServiceClient(
            WebClient webclient,
            @Value("${transfer_authorization.url}") String url) throws URISyntaxException {
        this.webclient = webclient;
        this.serviceUri = new URI(url);
    }

    public void authorize(TransferRequestVO transferRequest) {
        log.info("Checking authorization {}", transferRequest);

        AuthorizationServiceResponse response = webclient.get()
                .uri(serviceUri)
                .retrieve()
                .bodyToMono(AuthorizationServiceResponse.class)
                .doOnError(ex ->{
                    throw new NotAuthorizedTransactionException("Not authorized");
                })
                .block();
        if(response == null || !response.isAuthorized()){
            throw new NotAuthorizedTransactionException("Not authorized");
        }

        log.info("Authorized");
    }
}