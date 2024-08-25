package io.munkush.app.service.mapper;

import io.munkush.app.service.dto.CustomerResponse;
import io.munkush.app.service.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerResponseMapper implements Mapper<Customer, CustomerResponse> {

    @Override
    public CustomerResponse map(Customer source) {
        return CustomerResponse.builder()
                .id(source.getId())
                .address(source.getAddress())
                .email(source.getEmail())
                .firstname(source.getFirstname())
                .lastname(source.getLastname())
                .build();
    }

}
