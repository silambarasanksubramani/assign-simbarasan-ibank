package com.app.ibank.repository;

import com.app.ibank.entity.AccountBalance;
import com.app.ibank.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
}
