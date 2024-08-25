package io.munkush.app.productservice.services.product.mapper;

public interface Mapper<F, T> {
    T map(F source);
}
