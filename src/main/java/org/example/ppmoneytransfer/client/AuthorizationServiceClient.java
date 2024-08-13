package org.example.ppmoneytransfer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authService", url = "${transferauthorization.url}")
public interface AuthorizationServiceClient {

    @GetMapping("/authorize")
    AuthorizationServiceResponse authorize();
}