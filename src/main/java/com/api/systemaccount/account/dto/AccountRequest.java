package com.api.systemaccount.account.dto;

import com.api.systemaccount.account.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRequest {

    @NotNull
    private AccountType accountType;

    @NotNull
    @CPF
    private String cpf;
}
