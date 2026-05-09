package com.apex.accounts.repository;

import com.apex.accounts.entity.Accounts;
import com.apex.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepo extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(long customerId);
}
