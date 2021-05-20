package com.senla.service;


import com.senla.api.dao.IClientDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IClientService;

import com.senla.model.Book;
import com.senla.model.Client;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class ClientService implements IClientService {
    @Autowired
    private IClientDAO clientDAO;
    private Map<String, Comparator<Book>> sort;
    private static final Logger LOGGER = LogManager.getLogger(ClientService.class.getName());


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

            this.clientDAO.update(client);

        } catch (DAOException daoException) {
            LOGGER.log(Level.INFO, "Update failed", daoException);
            throw new ServiceException("Update operation failed", daoException);
        }
    }

    public void delete(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id to delete : %s", uuid));

            Client client = clientDAO.getEntityById(uuid);

            this.clientDAO.delete(client);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Delete failed", e);
            throw new ServiceException("Deleting operation failed", e);
        }
    }

    public void add(Client client) throws ServiceException {
        try {
            this.clientDAO.addEntity(client);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Adding client failed", e);
            throw new ServiceException("Adding client operation failed", e);

        }

    }

    public List<Client> getAll() throws ServiceException {
        try {
            return this.clientDAO.getAll();
        } catch (DAOException e){
            LOGGER.log(Level.WARN, e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Client get(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id for search : %s", uuid), uuid);
            return this.clientDAO.getEntityById(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Get client by id failed", e);
            throw new ServiceException("Get client by id operation failed", e);
        }
    }

}
