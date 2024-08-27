package io.munkush.app.services.orderline.mapper;

import io.munkush.app.services.clients.dto.ProductPurchaseRequest;
import io.munkush.app.services.order.mapper.Mapper;
import io.munkush.app.services.orderline.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class RequestToOrderLineMapper implements Mapper<ProductPurchaseRequest, OrderLine> {
    @Override
    public OrderLine map(ProductPurchaseRequest source) {
        return OrderLine.builder()
                .productId(source.productId())
                .quantity(source.quantity())
                .build();
    }
}
