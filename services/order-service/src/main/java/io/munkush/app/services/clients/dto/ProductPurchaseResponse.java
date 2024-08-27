package io.munkush.app.services.clients.dto;


import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
