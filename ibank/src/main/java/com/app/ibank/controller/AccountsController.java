package com.app.ibank.controller;

import com.app.ibank.entity.Accounts;
import com.app.ibank.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/ibank/v1/accounts")
public class AccountsController {
    @Autowired
    AccountsService accountsService;
    @GetMapping("/getAccount/{id}")
    public ResponseEntity<Accounts> getAccount(@PathVariable("id") long accountId){
        Accounts accounts = accountsService.getAccounts(accountId);
       return ResponseEntity.ok(accounts);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@Valid @RequestBody Accounts account){
        boolean accounts = accountsService.createAccount(account);
        return accounts ? new ResponseEntity<String>("Account is created successfully!", HttpStatus.OK)
                : new ResponseEntity<String>("Account was created not successfully!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@Valid @RequestBody Accounts account){
        boolean accounts = accountsService.updateAccount(account);
        return accounts ? new ResponseEntity<String>("Account is updated successfully!", HttpStatus.OK)
                : new ResponseEntity<String>("Account was updated not successfully!", HttpStatus.BAD_REQUEST);
    }
}
