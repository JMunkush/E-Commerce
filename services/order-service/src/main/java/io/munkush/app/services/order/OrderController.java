package io.munkush.app.services.order;

import io.munkush.app.services.clients.CustomerServiceClient;
import io.munkush.app.services.clients.ProductServiceClient;
import io.munkush.app.services.order.dto.OrderRequest;
import io.munkush.app.services.order.dto.OrderResponse;
import io.munkush.app.services.order.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Long> create(
            @RequestBody @Valid OrderRequest request
    ){

        return ResponseEntity.ok(orderService.createdOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> fetchAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> fetchById(@PathVariable("order-id") Long orderId){
        return ResponseEntity.ok(orderService.findById(orderId));
    }


}
