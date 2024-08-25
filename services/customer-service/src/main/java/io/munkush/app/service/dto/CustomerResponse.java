package io.munkush.app.service.dto;

import io.munkush.app.service.entity.Address;
import lombok.Builder;

@Builder
public record CustomerResponse (
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
){

}
