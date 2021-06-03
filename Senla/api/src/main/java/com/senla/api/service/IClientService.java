package com.senla.api.service;


import com.senla.api.exception.service.ServiceException;
import com.senla.model.Client;
import com.senla.model.dto.ClientDTO;

import java.util.List;
import java.util.UUID;

public interface IClientService  {

    ClientDTO getEntity(UUID uuid) throws ServiceException;

    ClientDTO createEntity(ClientDTO dto) throws ServiceException;

    ClientDTO updateEntity(ClientDTO clientDTO) throws ServiceException;

    ClientDTO deleteEntity(UUID uuid) throws ServiceException;

    ClientDTO addEntity(ClientDTO clientDTO) throws ServiceException;

    List<ClientDTO> getAllClients() throws ServiceException;
}
