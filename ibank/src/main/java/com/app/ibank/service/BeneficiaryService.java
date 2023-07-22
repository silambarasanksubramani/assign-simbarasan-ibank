package com.app.ibank.service;

import com.app.ibank.entity.Beneficiary;


public interface BeneficiaryService {

    public boolean addBeneficiary(Beneficiary beneficiary);

    public boolean updateBeneficiary(Beneficiary beneficiary);

    public boolean deleteBeneficiary(Beneficiary beneficiary);
}
