package com.example.springdatajpa_crud.mapper;

import com.example.springdatajpa_crud.dto.CustomerRequest;
import com.example.springdatajpa_crud.dto.CustomerResponse;
import com.example.springdatajpa_crud.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerResponse toDto(Customer customer);
}
