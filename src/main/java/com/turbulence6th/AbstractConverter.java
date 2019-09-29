package com.turbulence6th;

import java.util.List;
import java.util.stream.Collectors;

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

    public final List<E> convertToEntityList(List<D> dtos) {
        return dtos.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public final D convertToDto(E entity) {
        MapCaller<D, E> mapCaller = fillDto();
        D dto = createDto().create();

        Mapper<D, E> mapper = new Mapper<>(dto, entity);
        mapCaller.call(mapper);

        return dto;
    }

    public final List<D> convertToDtoList(List<E> entities) {
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}