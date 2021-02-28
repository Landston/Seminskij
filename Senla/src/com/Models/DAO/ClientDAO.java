package com.Models.DAO;

import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.api.DAO.IClientDAO;

import java.lang.ref.Cleaner;
import java.util.*;

public class ClientDAO extends AbstractDAO<Client> implements IClientDAO {


    private static ClientDAO instance;

    public static ClientDAO getInstance() {
        instance = Objects.requireNonNullElse(instance, new ClientDAO());
        return instance;
    }

    private ClientDAO() {

    }


}
