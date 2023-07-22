package com.app.ibank.service;

import com.app.ibank.domain.TransactionRequest;
import com.app.ibank.entity.Transactions;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TransactionService {

    public ResponseEntity<String> startTransaction(TransactionRequest transactionRequest);

    public ResponseEntity<List<Transactions>> getTransactionSummary(long accountId);

    public ResponseEntity<String> depositAndWithdraw(long accountId, double amount, String transactionType);
}
