package com.senla.model.dao;

import com.senla.model.models.AEntityID;
import com.senla.model.api.dao.IDAO;
import com.senla.model.exceptions.DAOException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T extends AEntityID>  implements IDAO<T> {

    public List<T> repository = new ArrayList<>();
    private Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Override
    public List<T> getAll() {
        return new ArrayList<>(this.repository);
    }

    @Override
    public void update(UUID id, T item) throws DAOException {
        try {
            Optional<T> first = this.repository.stream()
                    .filter(x -> x.getUuid().equals(id)).findFirst();
            T it = first.orElseThrow();

            this.repository.remove(it);
            this.repository.add(item);

        } catch (Exception e){
            LOGGER.log(Level.WARNING, String.format("UPDATE_ERROR", id));
            throw new DAOException(String.format("UPDATE_ERROR", id));
        }
    }

    @Override
    public void delete(UUID id) throws DAOException {
        try {
            this.repository.remove(this.getEntity(id));

        } catch (Exception e){
            LOGGER.log(Level.WARNING, String.format( "GET_ENTITY_BY_ID_ERROR", id));
            throw new DAOException("DELETE_ERROR");

        }
    }

    @Override
    public void addEntity(T entity) throws DAOException {
            try {
                this.repository.add(entity);
            } catch (Exception e){
                LOGGER.log(Level.WARNING, String.format( "ADD_ENTITY_ERROR", entity));
                throw new DAOException("ADD_ENTITY_ERROR");

            }
    }

    @Override
    public T getEntity(UUID id) throws DAOException {

       try {
           Optional<T> item = this.repository.stream()
                   .filter(x -> x.getUuid().equals(id)).findFirst();

           return item.orElseThrow();
       } catch (NoSuchElementException e){
           LOGGER.log(Level.WARNING, String.format( "GET_ENTITY_BY_ID_ERROR", id));
           throw new DAOException("GET_ENTITY_BY_ID_ERROR");

       }
    }
}
