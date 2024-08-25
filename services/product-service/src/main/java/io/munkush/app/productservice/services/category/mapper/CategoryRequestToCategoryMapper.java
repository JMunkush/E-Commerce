package io.munkush.app.productservice.services.category.mapper;

import io.munkush.app.productservice.services.category.Category;
import io.munkush.app.productservice.services.category.dto.CategoryRequest;
import io.munkush.app.productservice.services.product.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestToCategoryMapper implements Mapper<CategoryRequest, Category> {
    @Override
    public Category map(CategoryRequest source) {
        return Category
                .builder()
                .id(source.id())
                .description(source.description())
                .name(source.name())
                .build();
    }
}
