package com.example.springdatajpa_crud.service;

import com.example.springdatajpa_crud.dto.CustomerRequest;
import com.example.springdatajpa_crud.dto.CustomerResponse;
import com.example.springdatajpa_crud.entities.Customer;
import com.example.springdatajpa_crud.mapper.CustomerMapper;
import com.example.springdatajpa_crud.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CustomerResponse addCustomer(CustomerRequest request) {
        if (repository.existsCustomerByEmail(request.email())) {
            throw new IllegalArgumentException("There is already a customer with that email.");
        }

        Customer entity = new Customer(request.name(), request.email());

        Customer saved = repository.save(entity);

        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponse> listCustomers(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }
}
