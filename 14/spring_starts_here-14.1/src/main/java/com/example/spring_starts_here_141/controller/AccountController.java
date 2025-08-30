package com.example.spring_starts_here_141.controller;

import com.example.spring_starts_here_141.dto.TransferRequest;
import com.example.spring_starts_here_141.model.Account;
import com.example.spring_starts_here_141.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
            @RequestParam(required = false) String name) {
        if (name == null) {
            return accountService.getAllAccounts();
        } else {
            return accountService.findAccountsByName(name);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferMoney(@RequestBody TransferRequest request) {
        accountService.transferMoney(request.senderId(), request.receiverId(), request.amount());
        return ResponseEntity.ok().build();
    }
}
