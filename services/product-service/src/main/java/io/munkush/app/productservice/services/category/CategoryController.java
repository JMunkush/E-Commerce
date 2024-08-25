package io.munkush.app.productservice.services.category;


import io.munkush.app.productservice.services.category.dto.CategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CategoryRequest request){

        return ResponseEntity.ok(categoryService.save(request));

    }

}
