package com.api.systemaccount.account.mapper;

import com.api.systemaccount.account.dto.AccountBalanceResponse;
import com.api.systemaccount.account.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountBalanceResponse toDto(Account account) {
        if (account == null) return null;
        String name = account.getUserName();
        return new AccountBalanceResponse(
                name,
                account.getBalance(),
                account.getAccountType()
        );
    }
}