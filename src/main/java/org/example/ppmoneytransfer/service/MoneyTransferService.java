package org.example.ppmoneytransfer.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.ppmoneytransfer.client.NotificationServiceNotifyRequest;
import org.example.ppmoneytransfer.domain.Person;
import org.example.ppmoneytransfer.domain.Wallet;
import org.example.ppmoneytransfer.dto.TransferResponse;
import org.example.ppmoneytransfer.exceptions.BusinessRuleException;
import org.example.ppmoneytransfer.repository.WalletRepository;
import org.example.ppmoneytransfer.repository.PersonRepository;
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
    private final WalletRepository walletRepository;
    private final Set<ITransferValidation> transferValidations;
    private final NotificationServiceClient notificationServiceClient;

    public MoneyTransferService(
            PersonRepository personRepository, WalletRepository walletRepository,
            Set<ITransferValidation> transferValidations, NotificationServiceClient notificationServiceClient) {
        this.personRepository = personRepository;
        this.walletRepository = walletRepository;
        this.transferValidations = transferValidations;
        this.notificationServiceClient = notificationServiceClient;
    }

    @Transactional
    public TransferResponse transfer(BigDecimal value, Integer payerId, Integer payeeId) {
        Optional<Person> payer = personRepository.findById(payerId);
        if (payer.isEmpty()) {
            throw new BusinessRuleException("Payer not found");
        }

        Optional<Person> payee = personRepository.findById(payeeId);
        if (payee.isEmpty()) {
            throw new BusinessRuleException("Payee not found");
        }

        return transfer(new TransferRequestVO(value, payer.get(), payee.get()));
    }

    @Transactional
    public TransferResponse transfer(@Valid TransferRequestVO transferRequest) {
        transferValidations.forEach(rule -> rule.validate(transferRequest));

        ////////////////////
        Optional<Wallet> payerWallet = walletRepository.findWalletByOwner(transferRequest.payer());
        if (payerWallet.isEmpty()) {
            throw new BusinessRuleException("The payer doesn't have an account");
        }

        ////////////////////
        Optional<Wallet> payeeWallet = walletRepository.findWalletByOwner(transferRequest.payee());
        if (payeeWallet.isEmpty()) {
            throw new BusinessRuleException("The payee doesn't have an account");
        }

        ////////////////////
        if (payerWallet.get().getBalance().compareTo(transferRequest.value()) < 0) {
            throw new BusinessRuleException("Insufficient funds to complete the transaction");
        }

        ////////////////////
        payerWallet.get().setBalance(
                payerWallet.get().getBalance().subtract(transferRequest.value())
        );
        walletRepository.save(payerWallet.get());


        //////////////////
        payeeWallet.get().setBalance(
                payeeWallet.get().getBalance().add(transferRequest.value())
        );
        walletRepository.save(payeeWallet.get());

        NotificationServiceNotifyRequest notification = new NotificationServiceNotifyRequest(payeeWallet.get().getOwner().getEmail(), "Transferred successfully");
        notificationServiceClient.notifyWithRetry(notification);

        log.info("Transferred successfully");
        return new TransferResponse(true, "Transferred successfully");
    }

}
