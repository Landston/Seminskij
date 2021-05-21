package com.senla.dao;

import com.senla.api.dao.IBookDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.dao.util.DataBaseHandler;
import com.senla.model.Book;
import com.senla.model.BookStatus;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDAO extends AbstractDAO<Book> implements IBookDAO {

    public BookDAO(DataBaseHandler dataBaseHandler) {
        super();
    }

    public BookDAO() {
    }

    @Override
    protected Class<Book> getClazz() {
        return Book.class;
    }



}


