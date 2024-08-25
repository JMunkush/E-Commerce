package io.munkush.app.productservice.services.product;

import io.munkush.app.productservice.services.category.Category;
import io.munkush.app.productservice.services.category.CategoryRepository;
import io.munkush.app.productservice.services.category.exception.CategoryNotFoundException;
import io.munkush.app.productservice.services.product.dto.ProductPurchaseRequest;
import io.munkush.app.productservice.services.product.dto.ProductPurchaseResponse;
import io.munkush.app.productservice.services.product.dto.ProductRequest;
import io.munkush.app.productservice.services.product.mapper.ProductRequestToProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ProductRequestToProductMapper productRequestToProductMapper;

    public Long save(ProductRequest request) {


        if (categoryRepository.existsById(request.categoryId())) {

            var category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> getCategoryNotFoundException(request.categoryId()));

            var product = productRequestToProductMapper.map(request);
            product.setCategory(category);

            productRepository.save(product);

        }

        return null;
    }




    private CategoryNotFoundException getCategoryNotFoundException(Long categoryId){
        return new CategoryNotFoundException(format("category with id: %s not found", categoryId));
    }

}
