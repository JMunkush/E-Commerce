package io.munkush.app.services.order.mapper;

import io.munkush.app.services.order.Order;
import io.munkush.app.services.order.dto.OrderRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RequestToOrderMapper implements Mapper <OrderRequest, Order> {
    @Override
    public Order map(OrderRequest source) {
        return Order.builder()
                .id(source.id())
                .reference(source.reference())
                .createdAt(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .totalAmount(source.amount())
                .paymentMethod(source.paymentMethod())
                .build();
    }
}
