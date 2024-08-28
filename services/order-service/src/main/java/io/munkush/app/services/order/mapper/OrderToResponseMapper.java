package io.munkush.app.services.order.mapper;


import io.munkush.app.services.order.Order;
import io.munkush.app.services.order.dto.OrderLineResponse;
import io.munkush.app.services.order.dto.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderToResponseMapper implements Mapper<Order, OrderResponse> {
    @Override
    public OrderResponse map(Order source) {
        return OrderResponse.builder()
                .id(source.getId())
                .orderLines(source.getOrderLines().stream().map(r -> new OrderLineResponse(r.getId(),
                                r.getQuantity()))
                        .toList())
                .createdAt(source.getCreatedAt())
                .lastModifiedDate(source.getLastModifiedDate())
                .paymentMethod(source.getPaymentMethod())
                .totalAmount(source.getTotalAmount())
                .reference(source.getReference())
                .build();
    }
}
