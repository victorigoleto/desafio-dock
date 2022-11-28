package com.api.systemaccount.account.controller;

import com.api.systemaccount.account.dto.AccountBalanceResponse;
import com.api.systemaccount.account.dto.AccountMovementRequest;
import com.api.systemaccount.account.dto.AccountRequest;
import com.api.systemaccount.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> create(@RequestBody @Valid AccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/deposit")
    public ResponseEntity<?> deposit(@RequestBody @Valid AccountMovementRequest accountMovementRequest) {
        accountService.deposit(accountMovementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public ResponseEntity<AccountBalanceResponse> getBalance(@PathVariable("cpf") String cpf) {
        AccountBalanceResponse balance = accountService.getBalance(cpf);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PutMapping(path = "/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody @Valid AccountMovementRequest accountMovementRequest) {
        accountService.withdraw(accountMovementRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/block/cpf/{cpf}")
    public ResponseEntity<?> block(@PathVariable("cpf") String cpf) {
        accountService.block(cpf);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
