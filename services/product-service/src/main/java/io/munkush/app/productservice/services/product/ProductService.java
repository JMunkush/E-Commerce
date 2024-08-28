package io.munkush.app.productservice.services.product;

import io.munkush.app.productservice.services.category.CategoryRepository;
import io.munkush.app.productservice.services.category.exception.CategoryNotFoundException;
import io.munkush.app.productservice.services.product.exception.ProductNotFoundException;
import io.munkush.app.productservice.services.product.exception.ProductPurchaseException;
import io.munkush.app.productservice.services.product.dto.ProductPurchaseRequest;
import io.munkush.app.productservice.services.product.dto.ProductPurchaseResponse;
import io.munkush.app.productservice.services.product.dto.ProductRequest;
import io.munkush.app.productservice.services.product.dto.ProductResponse;
import io.munkush.app.productservice.services.product.mapper.ProductRequestToProductMapper;
import io.munkush.app.productservice.services.product.mapper.ProductToPurchaseResponse;
import io.munkush.app.productservice.services.product.mapper.ProductToResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRequestToProductMapper productRequestToProductMapper;
    private final ProductToPurchaseResponse productToPurchaseResponse;
    private final ProductToResponseMapper productToResponseMapper;
    public Long save(ProductRequest request) {


        if (!categoryRepository.existsById(request.categoryId())) {
            throw new ProductNotFoundException(format("category with id: %s not found", request.categoryId()));
        }

        var category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> getCategoryNotFoundException(request.categoryId()));

        var product = productRequestToProductMapper.map(request);
        product.setCategory(category);

        var savedProduct = productRepository.save(product);

        return savedProduct.getId();


    }

    private CategoryNotFoundException getCategoryNotFoundException(Long categoryId){
        return new CategoryNotFoundException(format("category with id: %s not found", categoryId));
    }

    @Transactional
    public List<ProductPurchaseResponse> purchase(List<ProductPurchaseRequest> request) {

        // Извлекаем только список id ( List<Long> )
        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        // Ищем список продуктов по списку айдишек
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        // Проверяем ( Количество продуктов пользователя должен быть равен с количеством с базы )
        // Если не равен, то:
        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exist");
        }

        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        for (int i = 0; i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);

            if(productRequest.quantity() > product.getAvailableQuantity()){
                throw new ProductPurchaseException(format("На базе существует %s штук",
                        product.getAvailableQuantity()));
            } else {
                var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
                product.setAvailableQuantity(newAvailableQuantity);
                productRepository.save(product);
                purchasedProducts.add(productToPurchaseResponse.map(product));
            }

        }

        return purchasedProducts;

    }

    public ProductResponse findById(Long id) {
        return productToResponseMapper.map(
                productRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException(format("product with id: %s not found", id)))
        );
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream().map(productToResponseMapper::map).toList();
    }
}
