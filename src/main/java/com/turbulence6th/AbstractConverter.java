package com.turbulence6th;

public abstract class AbstractConverter<E, D> {

    protected abstract Creator<E> createEntity();
    protected abstract Creator<D> createDto();
    protected abstract MapCaller<E, D> fillEntity();
    protected abstract MapCaller<D, E> fillDto();

    public final E convertToEntity(D dto) {
        MapCaller<E, D> mapCaller = fillEntity();
        E entity = createEntity().create();

        Mapper<E, D> mapper = new Mapper<>(entity, dto);
        mapCaller.call(mapper);

        return entity;
    }

    public final D convertToDto(E entity) {
        MapCaller<D, E> mapCaller = fillDto();
        D dto = createDto().create();

        Mapper<D, E> mapper = new Mapper<>(dto, entity);
        mapCaller.call(mapper);

        return dto;
    }
}