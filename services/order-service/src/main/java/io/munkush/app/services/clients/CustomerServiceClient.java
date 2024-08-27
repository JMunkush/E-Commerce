package io.munkush.app.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "customer-service"
)
public interface CustomerServiceClient {
    @GetMapping("/exists/{customer-id}")
    ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String id);
}
