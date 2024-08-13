package org.example.ppmoneytransfer.service;

import org.example.ppmoneytransfer.dto.TransferResponse;
import org.example.ppmoneytransfer.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class MoneyTransferService {

    @Transactional
    public TransferResponse transfer(BigDecimal value, Integer payerId, Integer payeeId) {
        return new TransferResponse(true, "Transferred successfully");
    }

}
