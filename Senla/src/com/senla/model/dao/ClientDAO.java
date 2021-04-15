package com.senla.model.dao;

import com.senla.di.annotations.Singleton;
import com.senla.model.models.Client;
import com.senla.model.serializable.Serializer;
import com.senla.model.api.dao.IClientDAO;


import java.util.*;


public class ClientDAO extends AbstractDAO<Client> implements IClientDAO {

    public ClientDAO() {
        repository = new ArrayList<>();
        repository = Serializer.deserialize(Client.class);
    }



}
