package org.example.ppmoneytransfer.exceptions;

public record ValidationError (
    Boolean success,
    String message
){}

