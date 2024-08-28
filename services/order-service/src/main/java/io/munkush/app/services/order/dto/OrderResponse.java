package io.munkush.app.services.order.dto;

import io.munkush.app.services.order.PaymentMethod;
import io.munkush.app.services.orderline.OrderLine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedDate,
        List<OrderLineResponse> orderLines
) {
}
