package com.app.ibank.controller;

import com.app.ibank.domain.TransactionRequest;
import com.app.ibank.entity.Transactions;
import com.app.ibank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ibank/v1/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PutMapping("/transact")
    public ResponseEntity<String> startTransaction(@RequestBody TransactionRequest request){
        return transactionService.startTransaction(request);

    }

    @GetMapping("/summary/{accountId}")
    public ResponseEntity<List<Transactions>> getTransactionSummary(@PathVariable("accountId") long accountId){
        return transactionService.getTransactionSummary(accountId);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam("accountId") long accountId, @RequestParam("amount") double amount){
        return transactionService.depositAndWithdraw(accountId,amount,"withdraw");
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam("accountId") long accountId, @RequestParam("amount") double amount){
        return transactionService.depositAndWithdraw(accountId,amount,"deposit");
    }
}
