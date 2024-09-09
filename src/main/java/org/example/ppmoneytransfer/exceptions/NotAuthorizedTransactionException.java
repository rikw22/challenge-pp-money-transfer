package org.example.ppmoneytransfer.exceptions;

public class NotAuthorizedTransactionException extends BusinessRuleException {
    public NotAuthorizedTransactionException(String message) {
        super(message);
    }
}
