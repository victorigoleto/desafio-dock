package com.api.systemaccount.account.repository;

import com.api.systemaccount.account.entity.Account;
import com.api.systemaccount.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByUser(User user);
}