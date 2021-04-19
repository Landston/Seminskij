package com.senla.model.dao;

import com.senla.di.appconfig.ApplicationContext;
import com.senla.model.dao.util.DataBaseHandler;
import com.senla.model.model.AEntityID;
import com.senla.model.api.dao.IDAO;
import com.senla.model.exception.DAOException;

import java.sql.Connection;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AbstractDAO<T extends AEntityID>   implements IDAO<T> {

    public Logger LOGGER = Logger.getLogger(this.getClass().getName());

    private Connection connection;


    @Override
    public List<T> getAll() throws DAOException {
        return null;
    }

    @Override
    public void update(UUID id, T item) throws DAOException {

    }

    @Override
    public void delete(UUID id) throws DAOException {

    }

    @Override
    public void delete(T id) throws DAOException {

    }

    @Override
    public void addEntity(T entity) throws DAOException {

    }

    @Override
    public T getEntityById(UUID id) throws DAOException {
        return null;
    }

    public Connection getConnection() {
        return ApplicationContext.getInstance().getObject(DataBaseHandler.class).getConnection();
    }
}
