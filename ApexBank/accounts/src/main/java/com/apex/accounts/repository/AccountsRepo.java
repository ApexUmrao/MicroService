package com.apex.accounts.repository;

import com.apex.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountsRepo extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(long customerId);

    @Transactional
    @Modifying
    Optional<Accounts> deleteByCustomerId(long customerId);
}
