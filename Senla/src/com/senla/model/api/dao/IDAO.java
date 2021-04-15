package com.senla.model.api.dao;

import com.senla.model.exceptions.DAOException;

import java.util.List;
import java.util.UUID;

public abstract interface IDAO<T> {


    List<T> getAll();

    void update(UUID id, T item) throws DAOException;

    void delete(UUID id) throws DAOException;

    void addEntity(T entity) throws DAOException;

    T getEntity(UUID id) throws DAOException;

}
