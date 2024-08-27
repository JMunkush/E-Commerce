package io.munkush.app.services.order.mapper;

public interface Mapper<F, T> {


    T map(F source);
}
