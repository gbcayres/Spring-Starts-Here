package com.example.spring_starts_here_131.controller;

import com.example.spring_starts_here_131.dto.AccountResponse;
import com.example.spring_starts_here_131.dto.TransferRequest;
import com.example.spring_starts_here_131.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferMoney(@RequestBody TransferRequest request) {
        accountService.transferMoney(
                request.senderId(),
                request.receiverId(),
                request.amount()
        );

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/rollback")
    public ResponseEntity<Void> transferMoneyButRollback(@RequestBody TransferRequest request) {
        accountService.transferWithRollback(
                request.senderId(),
                request.receiverId(),
                request.amount()
        );

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}
