package com.senla.model.dao;

import com.senla.di.annotations.Singleton;
import com.senla.model.models.Client;
import com.senla.model.serializable.Serializer;
import com.senla.model.api.dao.IClientDAO;


import java.util.*;

@Singleton
public class ClientDAO extends AbstractDAO<Client> implements IClientDAO {


    private static ClientDAO instance;

    public ClientDAO() {
        repository = new ArrayList<>();
        repository = Serializer.deserialize(Client.class);
    }

    public static ClientDAO getInstance() {
        instance = Objects.requireNonNullElse(instance, new ClientDAO());
        return instance;
    }

}
