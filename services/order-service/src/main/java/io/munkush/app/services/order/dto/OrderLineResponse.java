package io.munkush.app.services.order.dto;

public record OrderLineResponse(
        Long productId,
        double quantity
){

}
