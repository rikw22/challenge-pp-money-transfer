package org.example.ppmoneytransfer.transfer.validations;

import org.example.ppmoneytransfer.entity.PersonType;
import org.example.ppmoneytransfer.exceptions.BusinessRuleException;
import org.example.ppmoneytransfer.transfer.vo.TransferRequestVO;
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
