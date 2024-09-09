package org.example.ppmoneytransfer.validations;

import org.example.ppmoneytransfer.service.AuthorizationServiceClient;
import org.example.ppmoneytransfer.vo.TransferRequestVO;
import org.springframework.stereotype.Component;

@Component
public class ValidateFromAuthorizationService implements ITransferValidation {

    private final AuthorizationServiceClient authorizationServiceClient;

    public ValidateFromAuthorizationService(AuthorizationServiceClient authorizationServiceClient) {
        this.authorizationServiceClient = authorizationServiceClient;
    }

    @Override
    public void validate(TransferRequestVO request) {
        authorizationServiceClient.authorize(request);
    }
}
