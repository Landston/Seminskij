package com.senla.service;


import com.senla.api.dao.IClientDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IClientService;

import com.senla.model.Book;
import com.senla.model.Client;

import com.senla.model.dto.BookDTO;
import com.senla.model.dto.ClientDTO;
import com.senla.model.mapper.api.ClientMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;


@Service
@Transactional
public class ClientService implements IClientService {
    @Autowired
    private IClientDAO clientDAO;
    @Autowired
    private ClientMapper clientMapper;
    private Map<String, Comparator<Book>> sort;
    private static final Logger LOGGER = LogManager.getLogger(ClientService.class.getName());


    public ClientService() {

        this.init();
    }

    private void init() {
        this.sort = new HashMap<>();

    }

    public ClientDTO createEntity(ClientDTO dto) throws ServiceException {
        try {
            Client client = clientMapper.toEntity(dto);

            clientDAO.addEntity(client);

            return clientMapper.toDto(client);
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public ClientDTO updateEntity(ClientDTO clientDTO) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id to update : %s  new client : %s", clientDTO.getId(), clientDTO));
            Client client = clientDAO.getEntityById(clientDTO.getId());

            client.setMail(clientDTO.getMail());
            client.setName(clientDTO.getName());

            this.clientDAO.update(client);

            return clientMapper.toDto(client);
        } catch (DAOException daoException) {
            LOGGER.log(Level.INFO, "Update failed", daoException);
            throw new ServiceException("Update operation failed", daoException);
        }
    }

    public ClientDTO deleteEntity(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id to delete : %s", uuid));

            Client client = clientDAO.getEntityById(uuid);

            this.clientDAO.delete(client);

            return clientMapper.toDto(client);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Delete failed", e);
            throw new ServiceException("Deleting operation failed", e);
        }
    }

    public ClientDTO addEntity(ClientDTO clientDTO) throws ServiceException {
        try {
            Client client = clientMapper.toEntity(clientDTO);

            clientDAO.addEntity(client);

            return clientMapper.toDto(client);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Adding client failed", e);
            throw new ServiceException("Adding client operation failed", e);

        }

    }

    public List<ClientDTO> getAllClients() throws ServiceException {
        try {
            return this.clientDAO.getAll().stream().map(x -> clientMapper.toDto(x)).collect(Collectors.toList());
        } catch (DAOException e){
            LOGGER.log(Level.WARN, e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public ClientDTO getEntity(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Client id for search : %s", uuid), uuid);
            return clientMapper.toDto(this.clientDAO.getEntityById(uuid));

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Get client by id failed", e);
            throw new ServiceException("Get client by id operation failed", e);
        }
    }

}
