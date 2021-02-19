package com.Models.Services;

import com.Models.DAO.ClientDAO;
import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.api.Service.IClientService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClientService implements IClientService {

    private ClientDAO clientDAO;
    private Map<String, Comparator<Book>> sort;

    public ClientService(ClientDAO dao){
        this.clientDAO = dao;
        this.init();
    }

    private void init(){
        this.sort = new HashMap<>();

    }

    public Client getClient(UUID uuid){return this.clientDAO.getEntity(uuid);}

}
