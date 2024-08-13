package org.example.ppmoneytransfer.dto;

public record TransferResponse (
    Boolean success,
    String message
){}
