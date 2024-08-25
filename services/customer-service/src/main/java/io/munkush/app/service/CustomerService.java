package io.munkush.app.service;

import io.munkush.app.service.dto.CustomerRequest;
import io.munkush.app.service.dto.CustomerResponse;
import io.munkush.app.service.exception.CustomerNotFoundException;
import io.munkush.app.service.mapper.CustomerRequestToCustomerMapper;
import io.munkush.app.service.mapper.CustomerToCustomerResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerRequestToCustomerMapper customerRequestToCustomerMapper;
    private final CustomerToCustomerResponseMapper customerToCustomerResponseMapper;


    public String create(CustomerRequest request) {
        var customer = customerRepository.save(customerRequestToCustomerMapper.map(request));
        return customer.getId();
    }

    public void update(CustomerRequest request) {

        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> getNotFoundException(request.id()));

        if(request.email() != null){
            customer.setEmail(request.email());
        }
        if(request.firstname() != null){
            customer.setFirstname(request.firstname());
        }
        if(request.lastname() != null){
            customer.setFirstname(request.lastname());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }

        customerRepository.save(customer);

    }

    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream()
                .map(customerToCustomerResponseMapper::map)
                .toList();
    }

    public Boolean existsById(String id) {
        return customerRepository.existsById(id);
    }

    public CustomerResponse findById(String id) {
        return customerToCustomerResponseMapper.map(
                customerRepository.findById(id).orElseThrow(() ->
                        getNotFoundException(id))
        );
    }

    public void deleteById(String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw getNotFoundException(id);
        }
    }

    private CustomerNotFoundException getNotFoundException(String id){
        return new CustomerNotFoundException(format("customer with id: %s not exists", id));
    }
}
