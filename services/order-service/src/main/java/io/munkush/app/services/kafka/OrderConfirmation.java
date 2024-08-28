package io.munkush.app.services.kafka;

import io.munkush.app.services.clients.dto.ProductPurchaseRequest;
import io.munkush.app.services.clients.dto.ProductPurchaseResponse;
import io.munkush.app.services.order.PaymentMethod;
import io.munkush.app.services.order.dto.CustomerResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse> products
) {
}
