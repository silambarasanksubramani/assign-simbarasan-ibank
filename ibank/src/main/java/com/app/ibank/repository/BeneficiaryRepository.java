package com.app.ibank.repository;

import com.app.ibank.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    public Beneficiary findByAccountIdAndBeneficiaryAccountId(long accountId, long beneficiaryAccountId);
}
