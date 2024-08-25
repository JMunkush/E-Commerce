package io.munkush.app.productservice.services.product.mapper;


import io.munkush.app.productservice.services.category.Category;
import io.munkush.app.productservice.services.product.Product;
import io.munkush.app.productservice.services.product.dto.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestToProductMapper implements Mapper<ProductRequest, Product> {

    @Override
    public Product map(ProductRequest source) {
        return Product.builder()
                .id(source.id())
                .availableQuantity(source.availableQuantity())
                .description(source.description())
                .price(source.price())
                .name(source.name())
                .build();
    }
}
