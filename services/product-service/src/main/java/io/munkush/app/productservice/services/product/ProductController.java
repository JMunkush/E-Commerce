package io.munkush.app.productservice.services.product;

import io.munkush.app.productservice.services.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.save(request));
    }

}
