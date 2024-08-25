package io.munkush.app.service.mapper;

import io.munkush.app.service.dto.CustomerRequest;
import io.munkush.app.service.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestToCustomerMapper implements Mapper<CustomerRequest, Customer> {


    @Override
    public Customer map(CustomerRequest source) {
        return Customer.builder()
                .id(source.id())
                .address(source.address())
                .email(source.email())
                .firstname(source.firstname())
                .lastname(source.lastname())
                .build();
    }
}
