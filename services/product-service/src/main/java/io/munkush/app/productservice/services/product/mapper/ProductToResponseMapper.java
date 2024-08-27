package io.munkush.app.productservice.services.product.mapper;

import io.munkush.app.productservice.services.product.Product;
import io.munkush.app.productservice.services.product.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductToResponseMapper implements Mapper<Product, ProductResponse> {
    @Override
    public ProductResponse map(Product source) {
        return ProductResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .availableQuantity(source.getAvailableQuantity())
                .description(source.getDescription())
                .categoryName(source.getCategory().getName())
                .categoryId(source.getId())
                .categoryDescription(source.getCategory().getDescription())
                .build();
    }
}
