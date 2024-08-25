package io.munkush.app.service.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document // @Entity
@Validated
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
}
