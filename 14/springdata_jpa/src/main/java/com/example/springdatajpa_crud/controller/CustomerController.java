package com.example.springdatajpa_crud.controller;

import com.example.springdatajpa_crud.dto.CustomerRequest;
import com.example.springdatajpa_crud.dto.CustomerResponse;
import com.example.springdatajpa_crud.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerRequest request, UriComponentsBuilder uriBuilder) {
            CustomerResponse created = customerService.addCustomer(request);

            URI location = uriBuilder
                    .path("api/customers/{id}")
                    .buildAndExpand(created.id())
                    .toUri();

            return ResponseEntity.created(location).body(created);
    }
}
