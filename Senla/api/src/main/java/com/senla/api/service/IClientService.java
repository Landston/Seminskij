package com.senla.api.service;


import com.senla.api.exception.service.ServiceException;
import com.senla.model.Client;
import com.senla.model.dto.ClientDTO;

import java.util.List;
import java.util.UUID;

public interface IClientService  {

    ClientDTO getEntity(UUID uuid) throws ServiceException;

    ClientDTO createEntity(String name, String mail);

    ClientDTO updateEntity(UUID id, ClientDTO client) throws ServiceException;

    ClientDTO deleteEntity(UUID uuid) throws ServiceException;

    ClientDTO addEntity(ClientDTO client) throws ServiceException;

    List<ClientDTO> getAllClients() throws ServiceException;
}
