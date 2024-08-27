package io.munkush.app.productservice.services.category.mapper;

import io.munkush.app.productservice.services.category.Category;
import io.munkush.app.productservice.services.category.dto.CategoryResponse;
import io.munkush.app.productservice.services.product.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryToResponseMapper implements Mapper<Category, CategoryResponse> {
    @Override
    public CategoryResponse map(Category source) {
        return CategoryResponse.builder()
                .id(source.getId())
                .description(source.getDescription())
                .name(source.getName())
                .build();
    }
}
