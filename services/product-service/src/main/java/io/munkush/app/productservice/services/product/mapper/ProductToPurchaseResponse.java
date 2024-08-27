package io.munkush.app.productservice.services.product.mapper;


import io.munkush.app.productservice.services.product.Product;
import io.munkush.app.productservice.services.product.dto.ProductPurchaseResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductToPurchaseResponse implements Mapper<Product, ProductPurchaseResponse> {
    @Override
    public ProductPurchaseResponse map(Product source) {
        return ProductPurchaseResponse.builder()
                .productId(source.getId())
                .description(source.getDescription())
                .name(source.getName())
                .price(source.getPrice())
                .quantity(source.getAvailableQuantity())
                .build();
    }
}
