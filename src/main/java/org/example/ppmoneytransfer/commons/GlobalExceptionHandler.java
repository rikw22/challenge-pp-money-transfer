package org.example.ppmoneytransfer.commons;

import lombok.extern.slf4j.Slf4j;
import org.example.ppmoneytransfer.exceptions.BusinessRuleException;
import org.example.ppmoneytransfer.exceptions.ValidationError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidationExceptions (MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.error("MethodArgumentNotValidException: {}", errorMessages, ex);
        return ResponseEntity.badRequest().body(new ValidationError(false, errorMessages));
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ValidationError> handleBusinessRuleExceptions (BusinessRuleException ex) {
        log.error("BusinessRuleException", ex);
        return ResponseEntity.badRequest().body(new ValidationError(false, ex.getMessage()));
    }

}
