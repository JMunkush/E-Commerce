package io.munkush.app.kafka.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
