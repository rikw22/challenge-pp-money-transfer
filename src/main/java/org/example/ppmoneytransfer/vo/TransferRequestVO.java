package org.example.ppmoneytransfer.vo;

import org.example.ppmoneytransfer.domain.Person;

import java.math.BigDecimal;

public record TransferRequestVO (
        BigDecimal value,
        Person payer,
        Person payee
){
}
