package org.example.ppmoneytransfer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.ppmoneytransfer.service.MoneyTransferService;
import org.example.ppmoneytransfer.dto.TransferRequest;
import org.example.ppmoneytransfer.dto.TransferResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Money Transfer")
@Slf4j
public class MoneyTransferController {
    private final MoneyTransferService moneyTransferService;

    public MoneyTransferController(MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
    }

    @PostMapping(path = "/api/v1/transfer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Transfer money from one account to another")
    ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferRequest transferRequest){
        TransferResponse response = moneyTransferService.transfer(
                transferRequest.value(),
                transferRequest.payer(),
                transferRequest.payee()
        );
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
