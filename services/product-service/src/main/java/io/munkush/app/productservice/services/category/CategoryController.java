package io.munkush.app.productservice.services.category;


import io.munkush.app.productservice.services.category.dto.CategoryRequest;
import io.munkush.app.productservice.services.category.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CategoryRequest request){
        return ResponseEntity.ok(categoryService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> fetchAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

}
