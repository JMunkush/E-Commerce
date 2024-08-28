package io.munkush.app.services.clients;

import io.munkush.app.services.clients.dto.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        path = "/api/v1/payments"
)
public interface PaymentServiceClient {
    @PostMapping
    ResponseEntity<Long> create(@RequestBody @Valid PaymentRequest request);
}
