package org.example.ppmoneytransfer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authService", url = "https://util.devi.tools/api/v2")
public interface AuthorizationServiceClient {

    @GetMapping("/authorize")
    AuthorizationServiceResponse authorize();
}