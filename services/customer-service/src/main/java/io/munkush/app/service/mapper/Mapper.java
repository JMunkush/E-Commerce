package io.munkush.app.service.mapper;

public interface Mapper<F, T> {

    T map(F source);
}
