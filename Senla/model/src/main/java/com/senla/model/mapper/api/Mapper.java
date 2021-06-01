package com.senla.model.mapper.api;

import com.senla.model.dto.OrderDTO;

public interface Mapper<T, K> {

    K toDto(T entity);

    T toEntity(K dto);
}
