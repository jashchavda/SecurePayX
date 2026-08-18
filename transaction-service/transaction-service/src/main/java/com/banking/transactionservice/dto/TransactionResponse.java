package com.banking.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Response containing transaction details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    @Schema(description = "Unique internal ID of the transaction")
    private String id;
    @Schema(description = "Account number of the sender", example = "ACC-123456789")
    private String senderAccountNumber;
    @Schema(description = "Account number of the receiver", example = "ACC-987654321")
    private String receiverAccountNumber;
    @Schema(description = "Transaction amount", example = "500.00")
    private BigDecimal amount;
    @Schema(description = "Type of transaction (e.g., TRANSFER)", example = "TRANSFER")
    private TransactionType type;
    @Schema(description = "Current status of the transaction (e.g., PENDING, COMPLETED, FAILED)", example = "COMPLETED")
    private TransactionStatus status;
    @Schema(description = "Description of the transaction", example = "Payment for services")
    private String description;
    @Schema(description = "Reason for failure, if applicable")
    private String failureReason;
    @Schema(description = "External reference number for the transaction")
    private String referenceNumber;
    @Schema(description = "Timestamp when the transaction was created")
    private LocalDateTime createdAt;
    @Schema(description = "Timestamp when the transaction was completed")
    private LocalDateTime completedAt;
}
