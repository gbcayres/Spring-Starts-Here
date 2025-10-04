package com.gb.springdatajpa_crud.repository;

import com.gb.springdatajpa_crud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsCustomerByEmail(String email);
}
