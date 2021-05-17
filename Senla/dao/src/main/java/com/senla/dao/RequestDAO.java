package com.senla.dao;

import com.senla.api.dao.IRequestDAO;
import com.senla.api.exception.service.DAOException;

import com.senla.dao.util.DataBaseHandler;

import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.model.BookStatus;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class RequestDAO extends AbstractDAO<Request> implements IRequestDAO {

    @Override
    protected Request getEntity(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected String getAllEntitiesQuerySQL() {
        return null;
    }

    @Override
    protected Class<Request> getClazz() {
        return null;
    }

    @Override
    public List<Request> getAll() throws DAOException {
        return null;
    }
}

