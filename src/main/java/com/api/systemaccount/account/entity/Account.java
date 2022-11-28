package com.api.systemaccount.account.entity;

import com.api.systemaccount.account.enums.AccountType;
import com.api.systemaccount.transaction.entity.Transaction;
import com.api.systemaccount.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account")
public class Account {

    @Id
    private UUID id;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal dailyWithdrawLimit;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @PrePersist
    public void prePersist() {
        this.active = true;
        this.dailyWithdrawLimit = BigDecimal.valueOf(5000);
        this.balance = BigDecimal.ZERO;
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    public Account(AccountType accountType, User user) {
        this.accountType = accountType;
        this.user = user;
    }

    public void deposit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void withdraw(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public String getUserName() {
        return this.user.getName();
    }

    public void block() {
        this.active = false;
    }
}
