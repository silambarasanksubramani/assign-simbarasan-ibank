package com.app.ibank.controller;

import com.app.ibank.entity.Beneficiary;
import com.app.ibank.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/ibank/v1/beneficiary/")
public class BeneficiaryController {
    @Autowired
    BeneficiaryService beneficiaryService;
    @PostMapping("/create")
    public ResponseEntity<String> addBeneficiary(@Valid @RequestBody Beneficiary beneficiary){
        Boolean isBeneficiaryCreated = beneficiaryService.addBeneficiary(beneficiary);
       return isBeneficiaryCreated ? new ResponseEntity<String>("Beneficiary is created successfully.", HttpStatus.OK)
               : new ResponseEntity<String>("Sorry for the inconvenience! Beneficiary is not created due to some technical error.", HttpStatus.EXPECTATION_FAILED) ;
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBeneficiary(@RequestBody Beneficiary beneficiary){
        Boolean isBeneficiaryUpdated = beneficiaryService.updateBeneficiary(beneficiary);
        return isBeneficiaryUpdated ? new ResponseEntity<String>("Beneficiary is updated successfully.", HttpStatus.OK)
                : new ResponseEntity<String>("Sorry for the inconvenience! Beneficiary is not created due to some technical error.", HttpStatus.EXPECTATION_FAILED) ;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBeneficiary(@RequestBody Beneficiary beneficiary){
        boolean isBeneficiaryUpdated = beneficiaryService.deleteBeneficiary(beneficiary);
        return isBeneficiaryUpdated ? new ResponseEntity<String>("Beneficiary is deleted successfully.", HttpStatus.OK)
                : new ResponseEntity<String>("Sorry for the inconvenience! Beneficiary is not created due to some technical error.", HttpStatus.EXPECTATION_FAILED) ;
    }

    @PutMapping("/transact")
    public ResponseEntity<String> doTansaction(@RequestBody Beneficiary beneficiary){
        Boolean isBeneficiaryUpdated = beneficiaryService.updateBeneficiary(beneficiary);
        return isBeneficiaryUpdated ? new ResponseEntity<String>("Beneficiary is updated successfully.", HttpStatus.OK)
                : new ResponseEntity<String>("Sorry for the inconvenience! Beneficiary is not created due to some technical error.", HttpStatus.EXPECTATION_FAILED) ;
    }
}
