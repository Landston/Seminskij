package com.senla.dao;


import com.senla.api.dao.IOrderDAO;
import com.senla.api.exception.service.DAOException;

import com.senla.dao.util.DataBaseHandler;

import com.senla.model.*;

import org.apache.logging.log4j.Level;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Repository
public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

    public OrderDAO(){
        super();
    }

    @Override
    protected Class<Order> getClazz() {
        return Order.class;
    }

    @Override
    public List<Book> getBooksForOrder(UUID order_id) {
        return null;
    }

}
