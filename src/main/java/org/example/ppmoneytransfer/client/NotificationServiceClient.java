package org.example.ppmoneytransfer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificationService", url = "${notificationservice.url}")
public interface NotificationServiceClient {

    @PostMapping(value = "/notify", consumes = MediaType.APPLICATION_JSON_VALUE)
    NotificationServiceNotifyResponse notify(@RequestBody NotificationServiceNotifyRequest request);
}