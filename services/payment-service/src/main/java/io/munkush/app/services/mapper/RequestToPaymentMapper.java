package io.munkush.app.services.mapper;

import io.munkush.app.services.Payment;
import io.munkush.app.services.dto.PaymentRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RequestToPaymentMapper implements Mapper<PaymentRequest, Payment> {
    @Override
    public Payment map(PaymentRequest source) {
        return Payment.builder()
                .orderId(source.orderId())
                .paymentMethod(source.paymentMethod())
                .amount(source.amount())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }
}
