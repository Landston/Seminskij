package com.senla.api.dao;


import com.senla.api.exception.service.DAOException;

import java.util.List;
import java.util.UUID;

public abstract interface IDAO<T> {


    List<T> getAll() throws DAOException;

    void update(T item) throws DAOException;

    void delete(T entity) throws DAOException;

    void addEntity(T entity) throws DAOException;

    T getEntityById(UUID id) throws DAOException;

}
