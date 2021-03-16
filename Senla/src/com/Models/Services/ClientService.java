package com.Models.Services;

import com.DI.Annotations.Singleton;
import com.Models.DAO.ClientDAO;
import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.api.Service.IClientService;
import com.Models.exceptions.DAOException;
import com.Models.exceptions.ServiceException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class ClientService implements IClientService {

    private ClientDAO clientDAO;
    private Map<String, Comparator<Book>> sort;
    private static ClientService instance;
    private static final Logger LOGGER = Logger.getLogger(ClientService.class.getName());


    public ClientService() {
        this.clientDAO = ClientDAO.getInstance();
        this.init();
    }

    private void init() {
        this.sort = new HashMap<>();

    }

    public static ClientService getInstance() {
        instance = Objects.requireNonNullElse(instance, new ClientService());

        return instance;
    }

    public Client createClient(String name, String mail) {
        Client client = new Client(name, mail);
        return client;
    }

    public void updateClient(UUID id, Client client) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id to update : %s  new book : %s", id, client));

            this.clientDAO.update(id, client);

        } catch (DAOException daoException) {
            LOGGER.log(Level.WARNING, "Update failed", daoException);
            throw new ServiceException("Update operation failed", daoException);
        }
    }

    public void deleteClient(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id to delete : %s", uuid));
            this.clientDAO.delete(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Delete failed", e);
            throw new ServiceException("Deleting operation failed", e);
        }
    }

    public void addClient(Client client) throws ServiceException {
        try {
            this.clientDAO.addEntity(client);
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Adding client failed", e);
            throw new ServiceException("Adding client operation failed", e);

        }

    }

    public List<Client> getAllClients() {
        return this.clientDAO.getAll();
    }

    public Client getClient(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id for search : %s", uuid), uuid);
            return this.clientDAO.getEntity(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Get client by id failed", e);
            throw new ServiceException("Get client by id operation failed", e);
        }
    }

}
