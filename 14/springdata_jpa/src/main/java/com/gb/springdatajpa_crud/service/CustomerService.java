package com.gb.springdatajpa_crud.service;

import com.gb.springdatajpa_crud.dto.CustomerRequest;
import com.gb.springdatajpa_crud.dto.CustomerResponse;
import com.gb.springdatajpa_crud.model.Customer;
import com.gb.springdatajpa_crud.exception.ConflictException;
import com.gb.springdatajpa_crud.exception.ResourceNotFoundException;
import com.gb.springdatajpa_crud.mapper.CustomerMapper;
import com.gb.springdatajpa_crud.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
            throw new ConflictException("Email already taken.");
        }

        Customer entity = mapper.toEntity(request);
        Customer saved = repository.save(entity);

        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponse> listCustomers(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public CustomerResponse getCustomer(UUID id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
        return mapper.toDto(customer);
    }
}
