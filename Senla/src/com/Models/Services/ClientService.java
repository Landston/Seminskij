package com.Models.Services;

import com.Models.DAO.ClientDAO;
import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.api.Service.IClientService;

import java.util.*;

public class ClientService implements IClientService {

    private ClientDAO clientDAO;
    private Map<String, Comparator<Book>> sort;
    private static ClientService instance;


    public ClientService(){
        this.clientDAO = ClientDAO.getInstance();
        this.init();
    }

    public static ClientService getInstance(){
        instance =  Objects.requireNonNullElse(instance, new ClientService());

        return instance;
    }

    public Client createClient(String name, String mail){
        Client client = new Client(name, mail);
        return client;

    }

    public void updateClient(UUID id, Client client){
        this.clientDAO.update(id, client);
    }

    public void deleteClient(UUID uuid){
        this.clientDAO.delete(uuid);
    }

    public void addClient(Client client){
        this.clientDAO.addEntity(client);

    }


    private void init(){
        this.sort = new HashMap<>();

    }

    public List<Client> getAllClients(){

      return this.clientDAO.getAll();
    }

    public Client getClient(UUID uuid){return this.clientDAO.getEntity(uuid);}

}
