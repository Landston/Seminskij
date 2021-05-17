package com.senla.dao;

import com.senla.api.exception.service.DAOException;
import com.senla.dao.util.DataBaseHandler;
import com.senla.model.AbstractEntity;
import com.senla.api.dao.IDAO;

import com.senla.model.Book;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;



public abstract class AbstractDAO<T extends AbstractEntity> implements IDAO<T> {

public Logger LOGGER = LogManager.getLogger(this.getClass().getName());

@Autowired
@PersistenceContext(type = PersistenceContextType.TRANSACTION)
public EntityManager entityManager;


public AbstractDAO() {
}



public List<Book> getAllBook() throws DAOException {

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
    Root<Book> root = query.from(Book.class);
    query.select(root);
    return entityManager.createQuery(query).getResultList();

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
    entityManager.persist(entity);
}

@Override
public T getEntityById(UUID id) throws DAOException {
    return null;
}


protected abstract T getEntity(ResultSet rs) throws SQLException;

protected abstract String getAllEntitiesQuerySQL();

protected abstract Class<T> getClazz();
}
