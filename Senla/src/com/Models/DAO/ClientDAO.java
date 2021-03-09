package com.Models.DAO;

import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.Serializable.Serializer;
import com.Models.api.DAO.IClientDAO;

import java.lang.ref.Cleaner;
import java.util.*;

public class ClientDAO extends AbstractDAO<Client> implements IClientDAO {


    private static ClientDAO instance;

    private ClientDAO() {
        repository = new ArrayList<>();
        repository = Serializer.deserialize(Client.class);
    }

    public static ClientDAO getInstance() {
        instance = Objects.requireNonNullElse(instance, new ClientDAO());
        return instance;
    }

}
