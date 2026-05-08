package com.apex.accounts.repository;

import com.apex.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepo extends JpaRepository<Accounts, Long> {
}
