package com.api.systemaccount.transaction.controller;

import com.api.systemaccount.transaction.dto.TransactionRequest;
import com.api.systemaccount.transaction.dto.TransactionResponse;
import com.api.systemaccount.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Validated
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestBody @Valid TransactionRequest transactionRequest) {
        List<TransactionResponse> transactions = transactionService.getTransactions(transactionRequest);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
