package com.app.ibank.service;

import com.app.ibank.entity.Accounts;
import com.app.ibank.entity.Beneficiary;
import com.app.ibank.repository.AccountsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
@Slf4j
public class AccountsServiceImpl implements AccountsService{

    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public Accounts getAccounts(long accountId) {
        Optional<Accounts> accounts = accountsRepository.findById(accountId);
        return accounts.get();
    }

    @Override
    public boolean createAccount(Accounts accounts) {
        Accounts created = accountsRepository.save(accounts);
        return Objects.nonNull(created);
    }

    @Override
    public boolean updateAccount(Accounts account) {
        Optional<Accounts> accountCreated = accountsRepository.findById(account.getAccountId());

        Accounts accountsToUpdate = new Accounts();
        accountsToUpdate.setAccountName(account.getAccountName());
        accountsToUpdate.setEmail(account.getEmail());
        accountsToUpdate.setPhone(account.getPhone());
        accountsToUpdate.setStatus(account.getStatus());

        Accounts updated =
                accountsRepository.save(accountsToUpdate);
        return Objects.nonNull(updated);
    }
}
