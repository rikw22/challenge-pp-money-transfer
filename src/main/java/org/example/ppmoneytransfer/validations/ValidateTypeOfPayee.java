package org.example.ppmoneytransfer.validations;

import org.example.ppmoneytransfer.domain.PersonType;
import org.example.ppmoneytransfer.exceptions.BusinessRuleException;
import org.example.ppmoneytransfer.vo.TransferRequestVO;
import org.springframework.stereotype.Component;

@Component
public class ValidateTypeOfPayee implements ITransferValidation {

    @Override
    public void validate(TransferRequestVO request) {
        if(!request.payee().getType().equals(PersonType.Merchant)){
            throw new BusinessRuleException("Only Merchants can receive transfers");
        }
    }
}
