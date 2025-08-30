package com.example.spring_starts_here_141.repository;

import com.example.spring_starts_here_141.model.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Query("SELECT * FROM accounts WHERE name = :name ")
    public List<Account> findAccountsByName(String name);

    @Modifying
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    public void changeAmount(Long id, BigDecimal amount);
}
