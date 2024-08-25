package io.munkush.app.productservice.services.category;

import io.munkush.app.productservice.services.category.dto.CategoryRequest;
import io.munkush.app.productservice.services.category.mapper.CategoryRequestToCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryRequestToCategoryMapper categoryRequestToCategoryMapper;
    public Long save(CategoryRequest request) {
        return categoryRepository.save(categoryRequestToCategoryMapper.map(request)).getId();
    }
}
