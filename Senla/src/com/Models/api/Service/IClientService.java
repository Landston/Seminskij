package com.Models.api.Service;

import com.Models.Models.Client;
import com.Models.exceptions.ServiceException;

import java.util.UUID;

public interface IClientService  {

    Client getClient(UUID uuid) throws ServiceException;


}
