package com.app.ibank.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
//@IdClass(Beneficiary.class)
public class Beneficiary {


    @Column(name="ACCOUNT_ID")
    @Digits(integer = 10, fraction = 0, message = "Please enter valid id.")
    private long accountId;


    @Id
    @Column(name="BENE_ACCOUNT_ID")
    @Digits(integer = 10, fraction = 0, message = "Please enter valid id.")
    private long beneficiaryAccountId;

    @Column(name="BENE_IFSC_CODE")
    @NotNull()
    @Pattern(regexp="^[A-Z]{4}[0-9]{1,6}$",message = "invalid")
    private String beneficiaryIFSCCode;

    @Column(name="BENE_NAME")
    @NotNull()
    @Size(max=100)
    private String beneficiaryName;

    @Column(name="STATUS")
    @NotNull(message = "Please enter account status.")
    private String status;

}
