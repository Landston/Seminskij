package com.Models.api.DAO;

import java.util.List;
import java.util.UUID;

public abstract interface IDAO<T> {


    List<T> getAll();

    void update(UUID id, T item);

    void delete(UUID id);

    boolean addEntity(T entity);

    T getEntity(UUID id);

}
