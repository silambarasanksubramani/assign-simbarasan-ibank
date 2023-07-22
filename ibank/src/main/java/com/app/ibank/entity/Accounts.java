package com.app.ibank.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ACCOUNT_ID")
    private long accountId;

    @Column(name="ACCOUNT_NAME")
    @NotNull
    private String accountName;

    @Column(name="PHONE")
    @Pattern(regexp = "^\\d{10}$")
    @NotNull
    private String phone;

    @Column(name="EMAIL")
    @Email
    @NotNull
    private String email;

    @Column(name="STATUS")
    @NotNull
    private String status;

}
