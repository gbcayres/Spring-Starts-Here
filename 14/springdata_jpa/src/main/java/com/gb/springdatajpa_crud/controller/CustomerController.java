package com.gb.springdatajpa_crud.controller;

import com.gb.springdatajpa_crud.dto.CustomerRequest;
import com.gb.springdatajpa_crud.dto.CustomerResponse;
import com.gb.springdatajpa_crud.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Tag(name = "Customer", description = "Operations related to customer management")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "Create customer",
            description = "Creates and persists a new customer"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Customer succesfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(
                            mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Business conflict",
                    content = @Content(
                            mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
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

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable UUID id) {
        CustomerResponse customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Page<CustomerResponse>> list(@PageableDefault(size = 20, sort = "name") Pageable pageable) {
        Page<CustomerResponse> customers = customerService.listCustomers(pageable);
        return ResponseEntity.ok(customers);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@Valid @RequestBody CustomerRequest request) {
        //
    } */
}
