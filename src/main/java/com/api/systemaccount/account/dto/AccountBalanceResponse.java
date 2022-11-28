package com.api.systemaccount.account.dto;

import com.api.systemaccount.account.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record AccountBalanceResponse(
        String name,
        BigDecimal balance,
        AccountType accountType
) {
}
