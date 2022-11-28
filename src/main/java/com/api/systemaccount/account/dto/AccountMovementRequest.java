package com.api.systemaccount.account.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountMovementRequest {

    @CPF
    @NotNull
    private String cpf;

    @NotNull
    private BigDecimal value;
}
