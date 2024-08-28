package io.munkush.app.productservice.services.handle;

import io.munkush.app.productservice.services.category.exception.CategoryNotFoundException;
import io.munkush.app.productservice.services.product.exception.ProductNotFoundException;
import io.munkush.app.productservice.services.product.exception.ProductPurchaseException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(CategoryNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getLocalizedMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getLocalizedMessage());
    }
    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<String> handlePurchase(ProductPurchaseException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getLocalizedMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleNotValid(MethodArgumentNotValidException e){

        String result = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        return ResponseEntity.badRequest()
                .body(result);
    }


}
