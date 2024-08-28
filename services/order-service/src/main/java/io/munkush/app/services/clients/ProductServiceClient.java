package io.munkush.app.services.clients;

import io.munkush.app.services.clients.dto.ProductPurchaseRequest;
import io.munkush.app.services.clients.dto.ProductPurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "product-service",
        path = "/api/v1/products"
)
public interface ProductServiceClient {
    @PostMapping("/purchase")
    ResponseEntity<List<ProductPurchaseResponse>> purchase(
            @RequestBody List<ProductPurchaseRequest> request
    );

}
