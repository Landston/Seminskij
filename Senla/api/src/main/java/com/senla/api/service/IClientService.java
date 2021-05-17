package com.senla.api.service;


import com.senla.api.exception.service.ServiceException;
import com.senla.model.Client;

import java.util.List;
import java.util.UUID;

public interface IClientService  {

    Client get(UUID uuid) throws ServiceException;

    Client create(String name, String mail);

    void update(UUID id, Client client) throws ServiceException;

    void delete(Client uuid) throws ServiceException;

    void add(Client client) throws ServiceException;

    List<Client> getAll() throws ServiceException;
}
