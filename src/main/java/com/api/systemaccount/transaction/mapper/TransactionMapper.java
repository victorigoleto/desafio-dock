package com.api.systemaccount.transaction.mapper;

import com.api.systemaccount.transaction.dto.TransactionResponse;
import com.api.systemaccount.transaction.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponse toDto(Transaction transaction) {
        if (transaction == null) return null;
        return new TransactionResponse(
                transaction.getValue(),
                transaction.getTransactionDate(),
                transaction.getTransactionType()
        );
    }
}
