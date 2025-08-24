package com.example.spring_starts_here_131.service;

import com.example.spring_starts_here_131.dto.AccountResponse;
import com.example.spring_starts_here_131.model.Account;
import com.example.spring_starts_here_131.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transferMoney(Long senderId, Long receiverId, BigDecimal amount) {
        Account sender = accountRepository.findAccountById(senderId);
        Account receiver = accountRepository.findAccountById(receiverId);

        sender.setAmount(sender.getAmount().subtract(amount));
        receiver.setAmount(receiver.getAmount().add(amount));

        accountRepository.changeAmount(senderId, sender.getAmount());
        accountRepository.changeAmount(receiverId, receiver.getAmount());
    }

    public void transferWithRollback(Long senderId, Long receiverId, BigDecimal amount) {
        Account sender = accountRepository.findAccountById(senderId);
        Account receiver = accountRepository.findAccountById(receiverId);

        sender.setAmount(sender.getAmount().subtract(amount));
        receiver.setAmount(receiver.getAmount().add(amount));

        accountRepository.changeAmount(senderId, sender.getAmount());
        System.out.println("changed sender amount");
        accountRepository.changeAmount(receiverId, receiver.getAmount());
        System.out.println("changed receiver amount");

        throw new RuntimeException("Something went wrong!");
    }

    public List<AccountResponse> getAllAccounts() {

        return accountRepository.findAllAccounts().stream()
                .map(AccountResponse::fromAccount)
                .collect(Collectors.toList());
    }
}
