package com.app.ibank.service;

import com.app.ibank.entity.Beneficiary;
import com.app.ibank.repository.BeneficiaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class BeneficiaryServiceImpl implements BeneficiaryService{

    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    @Override
    public boolean addBeneficiary(Beneficiary beneficiary) {
        Beneficiary created = beneficiaryRepository.save(beneficiary);
        return Objects.nonNull(created);
    }

    @Override
    public boolean updateBeneficiary(Beneficiary beneficiary) {
        try {
            Beneficiary beneficiaryToUpdate = beneficiaryRepository.findByAccountIdAndBeneficiaryAccountId(beneficiary.getAccountId(), beneficiary.getBeneficiaryAccountId());
            beneficiaryToUpdate.setBeneficiaryName(beneficiary.getBeneficiaryName());
            beneficiaryToUpdate.setBeneficiaryIFSCCode(beneficiary.getBeneficiaryIFSCCode());
            beneficiaryToUpdate.setStatus(beneficiary.getStatus());
            Beneficiary updated =
                    beneficiaryRepository.save(beneficiaryToUpdate);
            return Objects.nonNull(updated);
        }catch(Exception exception){
            log.error("Exception occured in method updateBeneficiary()", exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBeneficiary(Beneficiary beneficiary) {
        boolean isDeleted = true;
        try {
            beneficiaryRepository.delete(beneficiary);
            isDeleted = true;
        } catch (Exception exception) {
            log.error("Exception occurred in method deleteBeneficiary()", exception.getMessage());
            isDeleted = false;
        }
        return isDeleted;
    }
}
