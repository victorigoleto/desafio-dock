package com.api.systemaccount.transaction.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionRequest {

    @NotNull
    private String cpf;

    @NotNull
    private LocalDateTime initialDate;

    @NotNull
    private LocalDateTime finalDate;
}
