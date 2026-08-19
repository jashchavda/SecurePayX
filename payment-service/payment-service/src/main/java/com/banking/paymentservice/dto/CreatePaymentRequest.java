package com.banking.paymentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

    //@Schema(description = "Account number associated with the payment", example = "ACC-123456789")
    @NotBlank(message = "Account number is required")
    private String accountNumber;

   // @Schema(description = "Payment amount", example = "500.00")
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

  //  @Schema(description = "Optional description for the payment", example = "Service charge")
    private String description;
}
