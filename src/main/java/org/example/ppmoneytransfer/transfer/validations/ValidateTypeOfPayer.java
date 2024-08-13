package org.example.ppmoneytransfer.transfer.validations;

import org.example.ppmoneytransfer.entity.PersonType;
import org.example.ppmoneytransfer.exceptions.BusinessRuleException;
import org.example.ppmoneytransfer.transfer.vo.TransferRequestVO;
import org.springframework.stereotype.Component;

@Component
public class ValidateTypeOfPayer implements ITransferValidation {

    @Override
    public void validate(TransferRequestVO request) {
        if(!request.payer().getType().equals(PersonType.RegularUser)){
            throw new BusinessRuleException("Only RegularUser can send money");
        }
    }
}
