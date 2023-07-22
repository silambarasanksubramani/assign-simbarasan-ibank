package com.app.ibank.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TransactionRespone {

    private long accountId;
    private long accountBalance;
    private HttpStatus status;
    private String message;

}
