package org.example.ppmoneytransfer.validations;

import org.example.ppmoneytransfer.domain.PersonType;
import org.example.ppmoneytransfer.exceptions.BusinessRuleException;
import org.example.ppmoneytransfer.vo.TransferRequestVO;
import org.springframework.stereotype.Component;

@Component
public class ValidateTypeOfPayer implements ITransferValidation {

    @Override
    public void validate(TransferRequestVO request) {
        if(request.payer() == null){
            throw new BusinessRuleException("Invalid payer");
        }

        if(!request.payer().getType().equals(PersonType.RegularUser)){
            throw new BusinessRuleException("Only RegularUser can send money");
        }
    }
}
