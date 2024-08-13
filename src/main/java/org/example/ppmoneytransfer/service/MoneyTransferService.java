package org.example.ppmoneytransfer.service;

import lombok.extern.slf4j.Slf4j;
import org.example.ppmoneytransfer.exceptions.BusinessRuleException;
import org.example.ppmoneytransfer.entity.Person;
import org.example.ppmoneytransfer.repository.PersonRepository;
import org.example.ppmoneytransfer.dto.TransferResponse;
import org.example.ppmoneytransfer.validations.ITransferValidation;
import org.example.ppmoneytransfer.vo.TransferRequestVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class MoneyTransferService {
    private final PersonRepository personRepository;
    private final Set<ITransferValidation> transferValidations;

    public MoneyTransferService(PersonRepository personRepository, Set<ITransferValidation> transferValidations){
        this.personRepository = personRepository;
        this.transferValidations = transferValidations;
    }

    @Transactional
    public TransferResponse transfer(BigDecimal value, Integer payerId, Integer payeeId) {
        Optional<Person> payer = personRepository.findById(payerId);
        if(payer.isEmpty()){
            throw new BusinessRuleException("Payer not found");
        }

        Optional<Person> payee = personRepository.findById(payeeId);
        if(payee.isEmpty()){
            throw new BusinessRuleException("Payee not found");
        }

        return transfer(new TransferRequestVO(value, payer.get(), payee.get()));
    }

    public TransferResponse transfer(TransferRequestVO transferRequestVO) {
        transferValidations.forEach(rule -> rule.validate(transferRequestVO));

        log.info("Transferred successfully");
        return new TransferResponse(true, "Transferred successfully");
    }

}
