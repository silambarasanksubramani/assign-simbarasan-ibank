package com.app.ibank.domain;

import lombok.Data;

@Data
public class TransactionRequest {

    private long fromAccountId;
    private long toAccountId;
    private double transactionAmount;

}
