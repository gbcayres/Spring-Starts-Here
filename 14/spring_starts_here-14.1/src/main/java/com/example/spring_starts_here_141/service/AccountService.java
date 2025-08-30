package com.example.spring_starts_here_141.service;

import com.example.spring_starts_here_141.dto.AccountResponse;
import com.example.spring_starts_here_141.exceptions.AccountNotFoundException;
import com.example.spring_starts_here_141.model.Account;
import com.example.spring_starts_here_141.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(Long senderId, Long receiverId, BigDecimal amount) {
        Account sender = accountRepository.findById(senderId).orElseThrow(() -> new AccountNotFoundException(senderId));
        Account receiver = accountRepository.findById(receiverId).orElseThrow(() -> new AccountNotFoundException(receiverId));

        BigDecimal newSenderAmount = sender.getAmount().subtract(amount);
        sender.setAmount(newSenderAmount);

        BigDecimal newReceiverAmount = sender.getAmount().add(amount);
        receiver.setAmount(newReceiverAmount);

        accountRepository.changeAmount(senderId, newSenderAmount);
        accountRepository.changeAmount(receiverId, newReceiverAmount);
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}
