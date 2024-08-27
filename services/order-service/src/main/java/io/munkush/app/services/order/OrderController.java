package io.munkush.app.services.order;

import io.munkush.app.services.clients.CustomerServiceClient;
import io.munkush.app.services.clients.ProductServiceClient;
import io.munkush.app.services.order.dto.OrderRequest;
import io.munkush.app.services.order.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static java.lang.String.format;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {



    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Long> create(
            @RequestBody @Valid OrderRequest request
    ){

        ResponseEntity.ok(orderService.createdOrder(request));


        return null;
    }


}
