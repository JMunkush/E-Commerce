package io.munkush.app.productservice.services.category;

import io.munkush.app.productservice.services.category.dto.CategoryRequest;
import io.munkush.app.productservice.services.category.dto.CategoryResponse;
import io.munkush.app.productservice.services.category.mapper.CategoryRequestToCategoryMapper;
import io.munkush.app.productservice.services.category.mapper.CategoryToResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToResponseMapper categoryToResponseMapper;
    private final CategoryRequestToCategoryMapper categoryRequestToCategoryMapper;
    public Long save(CategoryRequest request) {
        return categoryRepository.save(categoryRequestToCategoryMapper.map(request)).getId();
    }


    public List<CategoryResponse> findAll(){
        return categoryRepository.findAll().stream()
                .map(categoryToResponseMapper::map).toList();
    }
}
