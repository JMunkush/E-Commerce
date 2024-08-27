package io.munkush.app.services.clients.dto;


public record ProductPurchaseRequest(
        Long productId,
        int quantity
) {

}
