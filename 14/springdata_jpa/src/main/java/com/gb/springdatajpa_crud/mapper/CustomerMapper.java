package com.gb.springdatajpa_crud.mapper;

import com.gb.springdatajpa_crud.dto.CustomerRequest;
import com.gb.springdatajpa_crud.dto.CustomerResponse;
import com.gb.springdatajpa_crud.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerRequest request);

    CustomerResponse toDto(Customer customer);
}
