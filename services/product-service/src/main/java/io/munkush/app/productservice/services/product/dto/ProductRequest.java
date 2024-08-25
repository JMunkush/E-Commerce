package io.munkush.app.productservice.services.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest (
        Long id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available quantity should be positive")
        int availableQuantity,
        @Positive(message = "Price quantity should be positive")
        BigDecimal price,
        @NotNull(message = "categoryId is required")
        Long categoryId
) {


}
