package com.api.systemaccount.transaction.service;

import com.api.systemaccount.account.entity.Account;
import com.api.systemaccount.account.repository.AccountRepository;
import com.api.systemaccount.exceptions.BusinessException;
import com.api.systemaccount.transaction.dto.TransactionRequest;
import com.api.systemaccount.transaction.dto.TransactionResponse;
import com.api.systemaccount.transaction.entity.Transaction;
import com.api.systemaccount.transaction.mapper.TransactionMapper;
import com.api.systemaccount.transaction.repository.TransactionRepository;
import com.api.systemaccount.user.entity.User;
import com.api.systemaccount.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            UserRepository userRepository,
            TransactionMapper transactionMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionMapper = transactionMapper;
    }

    public List<TransactionResponse> getTransactions(TransactionRequest transactionRequest) {
        if (transactionRequest == null) throw new BusinessException("As informações de transação não podem ser nulas");

        validateCpf(transactionRequest.getCpf());

        Optional<User> user = userRepository.findByCpf(transactionRequest.getCpf());
        if (user.isEmpty()) throw new BusinessException("O CPF não corresponde a nenhum usuário");

        Optional<Account> account = accountRepository.findByUser(user.get());

        validateAccount(account);

        if (isValidRange(transactionRequest)) {
            validateDate(transactionRequest);
            List<Transaction> transactions = transactionRepository.findAllByAccountAndRangeDate(
                    account.get().getId(),
                    transactionRequest.getInitialDate(),
                    transactionRequest.getFinalDate()
            );

            return mapToResponse(transactions);
        }

        List<Transaction> transactions = transactionRepository.findAllByAccount(account.get());

        return mapToResponse(transactions);
    }

    private List<TransactionResponse> mapToResponse(List<Transaction> transactions) {
        return transactions.stream().map(transactionMapper::toDto).collect(Collectors.toList());
    }

    private void validateDate(TransactionRequest transactionRequest) {
        LocalDateTime initialDate = transactionRequest.getInitialDate();
        LocalDateTime finalDate = transactionRequest.getFinalDate();
        if (finalDate.isBefore(initialDate))
            throw new BusinessException("A data inicial não pode estar a frente da data final");
    }

    private boolean isValidRange(TransactionRequest transactionRequest) {
        return transactionRequest.getInitialDate() != null && transactionRequest.getFinalDate() != null;
    }

    private void validateCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) throw new BusinessException("CPF invalido");
    }

    private void validateAccount(Optional<Account> account) {
        if (account.isEmpty()) throw new BusinessException("Conta não encontrada pelo usuario");
        if (!account.get().isActive()) throw new BusinessException("Operação não permitida para conta inativa");
    }
}

