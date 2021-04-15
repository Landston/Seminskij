package com.senla.model.service;

import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Singleton;
import com.senla.model.api.dao.IClientDAO;
import com.senla.model.model.Book;
import com.senla.model.model.Client;

import com.senla.model.api.service.IClientService;
import com.senla.model.exception.DAOException;
import com.senla.model.exception.ServiceException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class ClientService implements IClientService {

    @Auttowared
    private IClientDAO clientDAO;

    private Map<String, Comparator<Book>> sort;

    private static final Logger LOGGER = Logger.getLogger(ClientService.class.getName());


    public ClientService() {

        this.init();
    }

    private void init() {
        this.sort = new HashMap<>();

    }

    public Client create(String name, String mail) {
        Client client = new Client(name, mail);

        return client;
    }

    public void update(UUID id, Client client) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id to update : %s  new book : %s", id, client));

            this.clientDAO.update(id, client);

        } catch (DAOException daoException) {
            LOGGER.log(Level.WARNING, "Update failed", daoException);
            throw new ServiceException("Update operation failed", daoException);
        }
    }

    public void delete(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id to delete : %s", uuid));
            this.clientDAO.delete(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Delete failed", e);
            throw new ServiceException("Deleting operation failed", e);
        }
    }

    public void add(Client client) throws ServiceException {
        try {
            this.clientDAO.addEntity(client);
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Adding client failed", e);
            throw new ServiceException("Adding client operation failed", e);

        }

    }

    public List<Client> getAll() throws ServiceException {
        try {
            return this.clientDAO.getAll();
        } catch (DAOException e){
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Client get(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id for search : %s", uuid), uuid);
            return this.clientDAO.getEntityById(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Get client by id failed", e);
            throw new ServiceException("Get client by id operation failed", e);
        }
    }

}
