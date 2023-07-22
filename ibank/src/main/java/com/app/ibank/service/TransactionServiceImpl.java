package com.app.ibank.service;

import com.app.ibank.domain.TransactionRequest;
import com.app.ibank.entity.AccountBalance;
import com.app.ibank.entity.Transactions;
import com.app.ibank.repository.AccountBalanceRepository;
import com.app.ibank.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountBalanceRepository accountBalanceRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<String> startTransaction(TransactionRequest transactionRequest) {
        log.info("Method startTransaction() is started.");
        long fromAccountId = transactionRequest.getFromAccountId();
        long toAccountId = transactionRequest.getToAccountId();
        double transRequestedAmount = transactionRequest.getTransactionAmount();
        List<Long> idList = Arrays.asList(fromAccountId, toAccountId);


        List<AccountBalance> finalAccountBalanceList = new ArrayList<AccountBalance>();
        try {

            List<AccountBalance> accountBalancesList = accountBalanceRepository.findAllById(idList);
            Map<Long, List<AccountBalance>> groupedAccountBalanceMap = accountBalancesList.stream().collect(Collectors.groupingBy(AccountBalance::getAccountId));


            if (groupedAccountBalanceMap.containsKey(fromAccountId)) {

                List<AccountBalance> fromAccount = groupedAccountBalanceMap.getOrDefault(fromAccountId, null);
                List<AccountBalance> toAccount = groupedAccountBalanceMap.getOrDefault(fromAccountId, null);
                if (fromAccount.get(0).getBalance() >= transRequestedAmount) {
                    AccountBalance fromAccountBalance = new AccountBalance();
                    AccountBalance toAccountBalance = new AccountBalance();
                    double finalFromAccountAmount = fromAccount.get(0).getBalance() - transRequestedAmount;
                    double finalToAccountAmount = toAccount.get(0).getBalance() + transRequestedAmount;

                    fromAccountBalance.setAccountId(fromAccountId);
                    fromAccountBalance.setBalance(finalFromAccountAmount);
                    toAccountBalance.setAccountId(toAccountId);
                    toAccountBalance.setBalance(finalToAccountAmount);

                    finalAccountBalanceList.add(fromAccountBalance);
                    finalAccountBalanceList.add(toAccountBalance);

                    List<AccountBalance> savedList = accountBalanceRepository.saveAll(finalAccountBalanceList);
                    List<Transactions> savedTransactionList = updateTransactionHistory(fromAccountId, toAccountId, transRequestedAmount, savedList);
                    String trsResponse = CollectionUtils.isEmpty(savedTransactionList) ? "Transaction was not success due to some technical reason." :
                            "Transaction from account " + fromAccountId + " To " + toAccountId + " is done successfully!";
                    return new ResponseEntity<String>(trsResponse, HttpStatus.OK);
                } else {
                    String transactionResponse = "The account " + fromAccount + " does not have sufficient amount.";
                    return new ResponseEntity<String>(transactionResponse, HttpStatus.EXPECTATION_FAILED);
                }
            }
        } catch (Exception e) {
            log.error("Exception occured in method startTransact()", e.getMessage());
        }
        return new ResponseEntity<String>("The transaction is not successful.", HttpStatus.BAD_REQUEST);
    }

    private List<Transactions> updateTransactionHistory(long fromAccountId, long toAccountId, double transRequestedAmount, List<AccountBalance> savedList) {
        try {
            List<Transactions> finalTransactionsAccountList = new ArrayList<Transactions>();
            Transactions fromAccountTransactions = new Transactions();
            Transactions toAccountTransactions = new Transactions();
            LocalDate transDate = LocalDate.now();
            String tranStatus = CollectionUtils.isEmpty(savedList) ? "Failure" : "Success";

            toAccountTransactions.setAccountId(fromAccountId);
            toAccountTransactions.setStatus(tranStatus);
            toAccountTransactions.setTransactionDate(transDate);
            toAccountTransactions.setTransactionType("Credit");
            toAccountTransactions.setAmount(transRequestedAmount);
            toAccountTransactions.setRemarks("");

            fromAccountTransactions.setAccountId(toAccountId);
            fromAccountTransactions.setStatus(tranStatus);
            fromAccountTransactions.setTransactionDate(transDate);
            fromAccountTransactions.setTransactionType("Debit");
            fromAccountTransactions.setAmount(transRequestedAmount);
            fromAccountTransactions.setRemarks("");

            finalTransactionsAccountList.add(fromAccountTransactions);
            finalTransactionsAccountList.add(toAccountTransactions);
            return transactionRepository.saveAll(finalTransactionsAccountList);
        } catch (Exception exception) {
            log.error("Exception occured in updateTransactionHistory() method", exception.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public ResponseEntity<List<Transactions>> getTransactionSummary(long accountId) {
        List<Transactions> transactionsList = transactionRepository.findByAccountId(accountId);
        if (CollectionUtils.isEmpty(transactionsList)) {
            return new ResponseEntity<List<Transactions>>(transactionsList, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<List<Transactions>>(transactionsList, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> depositAndWithdraw(long accountId, double amount, String transactionType) {
        log.info("The method depositAndWithdraw() is started");
        try {
            Optional<AccountBalance> accountBalance = accountBalanceRepository.findById(accountId);
            double balance = accountBalance.get().getBalance();
            if (transactionType.equals("deposit")) {
                accountBalance.get().setBalance(balance + amount);
            } else {
                accountBalance.get().setBalance(balance - amount);
            }
            AccountBalance updatedAccountBalance = accountBalanceRepository.save(accountBalance.get());
            return Objects.nonNull(updatedAccountBalance)
                    ? new ResponseEntity<>("The action " + transactionType + " for account " + accountId + " is completed", HttpStatus.OK)
                    : new ResponseEntity<>("The action " + transactionType + " for account " + accountId + " is not completed", HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            log.error("Exception occured in method updateBeneficiary()", exception.getMessage());
            return new ResponseEntity<>("The action " + transactionType + " for account " + accountId + " is not completed", HttpStatus.BAD_REQUEST);
        }
    }

}
