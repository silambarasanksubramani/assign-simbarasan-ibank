package com.app.ibank.service;

import com.app.ibank.entity.Accounts;


public interface AccountsService {

    public Accounts getAccounts(long accountId);

    public boolean createAccount(Accounts account);

    public boolean updateAccount(Accounts account);
}
