package com.api.systemaccount.account.service;

import com.api.systemaccount.account.dto.AccountBalanceResponse;
import com.api.systemaccount.account.dto.AccountMovementRequest;
import com.api.systemaccount.account.dto.AccountRequest;
import com.api.systemaccount.account.entity.Account;
import com.api.systemaccount.account.mapper.AccountMapper;
import com.api.systemaccount.account.repository.AccountRepository;
import com.api.systemaccount.exceptions.BusinessException;
import com.api.systemaccount.transaction.entity.Transaction;
import com.api.systemaccount.transaction.enums.TransactionType;
import com.api.systemaccount.transaction.repository.TransactionRepository;
import com.api.systemaccount.user.entity.User;
import com.api.systemaccount.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final TransactionRepository transactionRepository;

    public AccountService(
            AccountRepository accountRepository,
            UserRepository userRepository,
            AccountMapper accountMapper,
            TransactionRepository transactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void createAccount(AccountRequest accountRequest) {
        log.info("Validando account request");
        if (accountRequest == null) throw new BusinessException("As informações da conta não pode ser nulas");

        validateCpf(accountRequest.getCpf());

        log.info("Iniciando criação de conta para cpf {}", accountRequest.getCpf());

        Optional<User> user = userRepository.findByCpf(accountRequest.getCpf());

        if (user.isEmpty()) throw new BusinessException("O CPF não corresponde a nenhum usuário");

        accountRepository.save(new Account(accountRequest.getAccountType(), user.get()));
    }

    @Transactional
    public void deposit(AccountMovementRequest accountMovementRequest) {

        validateValue(accountMovementRequest);

        Optional<User> user = userRepository.findByCpf(accountMovementRequest.getCpf());
        if (user.isEmpty()) throw new BusinessException("O CPF não corresponde a nenhum usuário");

        Optional<Account> account = accountRepository.findByUser(user.get());

        validateAccount(account);

        account.get().deposit(accountMovementRequest.getValue());

        createTransaction(accountMovementRequest, TransactionType.DEPOSIT, account.get());

        accountRepository.save(account.get());
    }

    @Transactional
    public void withdraw(AccountMovementRequest accountMovementRequest) {

        validateValue(accountMovementRequest);

        Optional<User> user = userRepository.findByCpf(accountMovementRequest.getCpf());
        if (user.isEmpty()) throw new BusinessException("O CPF não corresponde a nenhum usuário");

        Optional<Account> account = accountRepository.findByUser(user.get());

        validateAccount(account);

        account.get().withdraw(accountMovementRequest.getValue());

        createTransaction(accountMovementRequest, TransactionType.WITHDRAW, account.get());

        accountRepository.save(account.get());
    }

    @Transactional
    public AccountBalanceResponse getBalance(String cpf) {

        validateCpf(cpf);

        Optional<User> user = userRepository.findByCpf(cpf);
        if (user.isEmpty()) throw new BusinessException("O CPF não corresponde a nenhum usuário");

        Optional<Account> account = accountRepository.findByUser(user.get());

        validateAccount(account);

        return accountMapper.toDto(account.get());
    }

    @Transactional
    public void block(String cpf) {

        validateCpf(cpf);

        Optional<User> user = userRepository.findByCpf(cpf);
        if (user.isEmpty()) throw new BusinessException("O CPF não corresponde a nenhum usuário");

        Optional<Account> account = accountRepository.findByUser(user.get());

        validateAccount(account);

        account.get().block();

        accountRepository.save(account.get());
    }

    private void createTransaction(
            AccountMovementRequest accountMovementRequest,
            TransactionType transactionType,
            Account account
    ) {
        TransactionType type = transactionType == TransactionType.DEPOSIT ? TransactionType.DEPOSIT : TransactionType.WITHDRAW;
        Transaction transaction = new Transaction(
                accountMovementRequest.getValue(),
                type,
                account
        );
        transactionRepository.save(transaction);
    }

    private void validateCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) throw new BusinessException("CPF invalido");
    }

    private void validateValue(AccountMovementRequest accountMovementRequest) {
        if (accountMovementRequest == null) throw new BusinessException("As informações da conta não pode ser nulas");
        validateCpf(accountMovementRequest.getCpf());
        if (accountMovementRequest.getValue().longValueExact() <= 0L)
            throw new BusinessException("Valor não pode ser negativo");
    }

    private void validateAccount(Optional<Account> account) {
        if (account.isEmpty()) throw new BusinessException("Conta não encontrada pelo usuario");
        if (!account.get().isActive()) throw new BusinessException("Operação não permitida para conta inativa");
    }

}
