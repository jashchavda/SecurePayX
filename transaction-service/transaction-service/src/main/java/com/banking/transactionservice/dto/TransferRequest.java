package com.banking.transactionservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "Request body for transferring funds")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

    @Schema(description = "Account number of the sender", example = "ACC-123456789")
    @NotBlank(message = "Sender account number is required")
    private String senderAccountNumber;

    @Schema(description = "Account number of the receiver", example = "ACC-987654321")
    @NotBlank(message = "Receiver account number is required")
    private String receiverAccountNumber;

    @Schema(description = "Amount to be transferred", example = "500.00")
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @Schema(description = "Optional description for the transfer", example = "Payment for services")
    private String description;
}
