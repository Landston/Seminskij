package com.senla.dao;

import com.senla.api.exception.service.DAOException;
import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Singleton;
import com.senla.di.appconfig.ApplicationContext;
import com.senla.dao.util.DataBaseHandler;
import com.senla.model.AbstractEntity;
import com.senla.api.dao.IDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public abstract class AbstractDAO<T extends AbstractEntity> implements IDAO<T> {

public Logger LOGGER = LogManager.getLogger(this.getClass().getName());

@Auttowared
public DataBaseHandler dataBaseHandler;

public AbstractDAO() {
    double ad;
            Double fasf;

}

@Override
public List<T> getAll() throws DAOException {
    String sql = this.getAllEntitiesQuerySQL();
    Connection connection = dataBaseHandler.getConnection();

    List<T> items = new ArrayList<>();
    try{
        PreparedStatement statement = connection.prepareStatement(sql);

       ResultSet resultSet =  statement.executeQuery();

       while(resultSet.next()) {
           T item = getEntity(resultSet);
            items.add(item);
       }

       return items;
    } catch (SQLException e) {
        e.printStackTrace();
    }

    throw new DAOException("BookDAO opeartion failed");

}


@Override
public void update(UUID id, T item) throws DAOException {

}

public void setDataBaseHandler(DataBaseHandler dataBaseHandler) {
    this.dataBaseHandler = dataBaseHandler;
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

protected abstract T getEntity(ResultSet rs) throws SQLException;

protected abstract String getAllEntitiesQuerySQL();
}
