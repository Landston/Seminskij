package com.senla.dao;

import com.senla.api.dao.IClientDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.dao.util.DataBaseHandler;

import com.senla.model.Client;
import org.apache.logging.log4j.Level;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.*;

@Repository
public class ClientDAO extends AbstractDAO<Client> implements IClientDAO {


    @Override
    protected Client getEntity(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected String getAllEntitiesQuerySQL() {
        return null;
    }

    @Override
    protected Class<Client> getClazz() {
        return null;
    }

    @Override
    public List<Client> getAll() throws DAOException {
        return null;
    }
}
