package com.senla.dao;

import com.senla.api.exception.service.DAOException;
import com.senla.model.AbstractEntity;
import com.senla.api.dao.IDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;


public abstract class AbstractDAO<T extends AbstractEntity> implements IDAO<T> {

    public Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    @Autowired
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;


    public AbstractDAO() {
    }

    @Override
    public List<T> getAll() throws DAOException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClazz());
        Root<T> root = query.from(getClazz());
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void update(T item) throws DAOException {
        entityManager.merge(item);
    }


    @Override
    public void delete(T entity) throws DAOException {
        entityManager.remove(entity);

    }

    @Override
    public void addEntity(T entity) throws DAOException {
       try {
           entityManager.persist(entity);
       } catch (Exception e){
           throw new DAOException("DAO_EXCEPTION, ADD_ENTITY",e);
       }
    }

    @Override
    public T getEntityById(UUID id) throws DAOException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getClazz());
        Root<T> root = query.from(getClazz());
        query.select(root);
        query.where(builder.equal(root.get("id"),id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public boolean findEntity(UUID uuid) throws DAOException {
        try{
           T entity =  entityManager.find(getClazz(), uuid);

            return entity != null;
        } catch (IllegalArgumentException e){
            LOGGER.warn(e);

            throw new DAOException(e);
        }
    }
    protected abstract Class<T> getClazz();
}
