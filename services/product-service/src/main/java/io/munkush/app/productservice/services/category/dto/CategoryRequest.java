package io.munkush.app.productservice.services.category.dto;


import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
                              Long id,

                              @NotNull(message = "Category name is required")
                              String name,

                              @NotNull(message = "Category description is required")
                              String description
                              ) {
}
