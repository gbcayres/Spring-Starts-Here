package com.example.springdatajpa_crud.repository;

import com.example.springdatajpa_crud.dto.CustomerResponse;
import com.example.springdatajpa_crud.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsCustomerByEmail(String email);
}
