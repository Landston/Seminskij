package com.senla.dao;

import com.senla.api.dao.IRequestDAO;
import com.senla.api.exception.service.DAOException;

import com.senla.dao.util.DataBaseHandler;

import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.model.BookStatus;

import org.springframework.context.ApplicationContext;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class RequestDAO extends AbstractDAO<Request> implements IRequestDAO {
    @Override
    protected Class<Request> getClazz() {
        return Request.class;
    }

    public Request requestByBookId(UUID id) throws DAOException {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Request> query = builder.createQuery(getClazz());
            Root<Request> root = query.from(getClazz());
            query.select(root);
            query.where(builder.equal(root.get("book_id"), id));
            return entityManager.createQuery(query).getSingleResult();
        } catch (PersistenceException e){
            LOGGER.warn(e);

            throw new DAOException("REQUEST_DAO_EXCEPTION REQUEST BY BOOK ID METHOD", e);
        }
    }


}

