package com.app.ibank.repository;

import com.app.ibank.entity.Beneficiary;
import com.app.ibank.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

    public List<Transactions> findByAccountId(long beneficiaryAccountId);

}
