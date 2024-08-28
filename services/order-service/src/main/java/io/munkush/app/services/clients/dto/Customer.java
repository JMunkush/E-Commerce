package io.munkush.app.services.clients.dto;

import lombok.Builder;

@Builder
public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) { }
