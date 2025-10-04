package com.gb.springdatajpa_crud.controller;

import com.gb.springdatajpa_crud.dto.CustomerRequest;
import com.gb.springdatajpa_crud.dto.CustomerResponse;
import com.gb.springdatajpa_crud.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable UUID id) {
        CustomerResponse customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> list(@PageableDefault(size = 20, sort = "name") Pageable pageable) {
        Page<CustomerResponse> customers = customerService.listCustomers(pageable);
        return ResponseEntity.ok(customers);
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
