package com.api.systemaccount.transaction.dto;

import com.api.systemaccount.transaction.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionResponse {

    @NotNull
    private BigDecimal value;

    @NotNull
    private LocalDateTime transactionDate;

    @NotNull
    private TransactionType transactionType;
}
