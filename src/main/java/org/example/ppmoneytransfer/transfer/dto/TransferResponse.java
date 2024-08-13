package org.example.ppmoneytransfer.transfer.dto;

public record TransferResponse (
    Boolean success,
    String message
){}
