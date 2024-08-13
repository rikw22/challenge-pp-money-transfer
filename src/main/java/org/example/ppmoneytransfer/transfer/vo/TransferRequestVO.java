package org.example.ppmoneytransfer.transfer.vo;

import org.example.ppmoneytransfer.entity.Account;
import org.example.ppmoneytransfer.entity.Person;

import java.math.BigDecimal;

public record TransferRequestVO (
        BigDecimal value,
        Person payer,
        Person payee
){
}
