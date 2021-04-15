package com.senla.model.api.service;

import com.senla.model.models.Client;
import com.senla.model.exceptions.ServiceException;

import java.util.List;
import java.util.UUID;

public interface IClientService  {

    Client get(UUID uuid) throws ServiceException;

    Client create(String name, String mail);

    void update(UUID id, Client client) throws ServiceException;

    void delete(UUID uuid) throws ServiceException;

    void add(Client client) throws ServiceException;

    List<Client> getAll();
}
