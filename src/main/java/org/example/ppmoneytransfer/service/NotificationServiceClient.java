package org.example.ppmoneytransfer.service;

import lombok.extern.slf4j.Slf4j;
import org.example.ppmoneytransfer.client.NotificationServiceNotifyRequest;
import org.example.ppmoneytransfer.client.NotificationServiceNotifyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@Slf4j
@Service
public class NotificationServiceClient {
    private final WebClient webclient;
    private final URI serviceUri;
    private final Integer maxRetryCount;
    private final Integer minBackoffSeconds;

    public NotificationServiceClient(WebClient webclient,
                                     @Value("${notification_service.url}") String url,
                                     @Value("${notification_service.max-retry-count}") Integer maxRetryCount,
                                     @Value("${notification_service.min-backoff-seconds}") Integer minBackoffSeconds
    ) throws URISyntaxException {
        this.webclient = webclient;
        this.serviceUri = new URI(url);
        this.maxRetryCount = maxRetryCount;
        this.minBackoffSeconds = minBackoffSeconds;
    }

    public void notifyWithRetry(NotificationServiceNotifyRequest request) {
        log.info("Sending notification {}", request);

        try{
            NotificationServiceNotifyResponse response = webclient.post()
                    .uri(serviceUri)
                    .body(Mono.justOrEmpty(request), NotificationServiceNotifyRequest.class)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(NotificationServiceNotifyResponse.class)
                    .retryWhen(Retry.backoff(maxRetryCount, Duration.ofSeconds(minBackoffSeconds)))
                    .block();

        } catch (Exception e) {
            log.error("Error sending notification", e);
        }
    }
}
