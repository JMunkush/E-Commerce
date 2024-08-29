package io.munkush.app.kafka.dto;

import lombok.Builder;

@Builder
public record Address(String street,
                      String houseNumber,
                      String zipCode) {


}
