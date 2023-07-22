package com.app.ibank.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class AccountBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ACCOUNT_ID")
    private long accountId;

    @Column(name="BALANCE")
    private double balance;


}
