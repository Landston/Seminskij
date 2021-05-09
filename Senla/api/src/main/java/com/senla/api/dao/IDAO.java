package com.senla.api.dao;


import com.senla.api.exception.service.DAOException;

import java.util.List;
import java.util.UUID;

public abstract interface IDAO<T> {


    List<T> getAll() throws DAOException;

    void update(UUID id, T item) throws DAOException;

    void delete(UUID id) throws DAOException;

    void delete(T id) throws DAOException;

    void addEntity(T entity) throws DAOException;

    T getEntityById(UUID id) throws DAOException;

}
