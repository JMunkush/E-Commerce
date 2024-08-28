package io.munkush.app.services.order.dto;

import lombok.Builder;

@Builder
public record Address(String street,
                      String houseNumber,
                      String zipCode) {


}
