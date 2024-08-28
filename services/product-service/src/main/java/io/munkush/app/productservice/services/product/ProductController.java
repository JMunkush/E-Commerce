package io.munkush.app.productservice.services.product;

import io.munkush.app.productservice.services.product.dto.ProductPurchaseRequest;
import io.munkush.app.productservice.services.product.dto.ProductPurchaseResponse;
import io.munkush.app.productservice.services.product.dto.ProductRequest;
import io.munkush.app.productservice.services.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid ProductRequest request){
        return ResponseEntity.ok(productService.save(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchase(
            @RequestBody @Valid List<ProductPurchaseRequest> request
    ){
        return ResponseEntity.ok(productService.purchase(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> fetchById(@PathVariable("product-id") Long productId){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> fetchAll(){
        return ResponseEntity.ok(productService.findAll());
    }



}
