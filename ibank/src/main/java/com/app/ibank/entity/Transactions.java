package com.app.ibank.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="ACCOUNT_ID")
    private long accountId;

    @Column(name="TRANSAC_DATE")
    private LocalDate transactionDate;

    @Column(name="TRANSAC_TYPE")
    private String transactionType;

    @Column(name="AMOUNT")
    private double amount;

    @Column(name="STATUS")
    private String status;

    @Column(name="REMARKS")
    private String remarks;

}
