package org.example.ppmoneytransfer.service;

import lombok.extern.slf4j.Slf4j;
import org.example.ppmoneytransfer.dto.TransferResponse;
import org.example.ppmoneytransfer.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class MoneyTransferService {

    @Transactional
    public TransferResponse transfer(BigDecimal value, Integer payerId, Integer payeeId) {
        log.info("Transferred successfully");
        return new TransferResponse(true, "Transferred successfully");
    }

}
