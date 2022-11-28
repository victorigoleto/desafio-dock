package com.api.systemaccount.transaction.repository;

import com.api.systemaccount.account.entity.Account;
import com.api.systemaccount.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query(value = """
            select * from transaction t
            where t.account_id = :accountId
            and t.transaction_date >= :initialDate
            and t.transaction_date <= :finalDate
            """, nativeQuery = true
    )
    List<Transaction> findAllByAccountAndRangeDate(
            UUID accountId,
            LocalDateTime initialDate,
            LocalDateTime finalDate
    );

    List<Transaction> findAllByAccount(Account account);
}
