package org.example.ppmoneytransfer.client;

public record AuthorizationServiceResponse(String status, AuthorizationServiceData data) {

    public boolean isAuthorized() {
        return "success".equals(status) && data.authorization();
    }
}