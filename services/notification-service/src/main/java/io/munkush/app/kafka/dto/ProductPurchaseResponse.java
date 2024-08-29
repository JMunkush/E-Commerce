package io.munkush.app.kafka.dto;


import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
