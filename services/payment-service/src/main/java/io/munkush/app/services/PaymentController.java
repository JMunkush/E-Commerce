package io.munkush.app.services;

import io.munkush.app.services.dto.PaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {


    private final PaymentService paymentService;
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid PaymentRequest request){
        return ResponseEntity.ok(paymentService.create(request));
    }

}
