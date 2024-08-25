package io.munkush.app.service;

import io.munkush.app.service.dto.CustomerRequest;
import io.munkush.app.service.dto.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody @Valid CustomerRequest request
    ){
        return ResponseEntity.ok(customerService.create(request));
    }


    @PutMapping
    public ResponseEntity<String> update(
            @RequestBody @Valid CustomerRequest request
    ){
        customerService.update(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("THE CUSTOMER UPDATED");
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> fetchAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String id){
        return ResponseEntity.ok(customerService.existsById(id));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> fetchById(@PathVariable("customer-id") String id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<String> deleteById(@PathVariable("customer-id") String id){
        customerService.deleteById(id);
        return ResponseEntity.ok("THE CUSTOMER DELETED");
    }
}
