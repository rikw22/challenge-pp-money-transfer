package org.example.ppmoneytransfer.commons;

public record ValidationErrorException (
        Boolean success,
        String message
){
}
