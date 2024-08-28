package io.munkush.app.services.mapper;

public interface Mapper<F, T> {

    T map(F source);
}
