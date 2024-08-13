package org.example.ppmoneytransfer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequest(

        @Schema(type = "number", format = "double", example = "123.45")
        @NotNull
        @DecimalMin(value = "0.01")
        @Min(0)
        @Digits(integer=10, fraction=2)
        BigDecimal value,

        @Schema(type = "number", format = "integer", example = "4")
        @NotNull
        Integer payer,

        @Schema(type = "number", format = "integer", example = "15")
        @NotNull
        Integer payee
) {

}
