package com.api.systemaccount.transaction.entity;


import com.api.systemaccount.account.entity.Account;
import com.api.systemaccount.transaction.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    private UUID id;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
        this.transactionDate = LocalDateTime.now();
    }

    public Transaction(BigDecimal value, TransactionType transactionType, Account account) {
        this.value = value;
        this.transactionType = transactionType;
        this.account = account;
    }
}
