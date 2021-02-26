package com.Models.DAO;

import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.api.DAO.IClientDAO;

import java.lang.ref.Cleaner;
import java.util.*;

public class ClientDAO implements IClientDAO {

    private List<Client> clients;
    private static ClientDAO instance;

    public static ClientDAO getInstance() {
        instance = Objects.requireNonNullElse(instance, new ClientDAO());
        return instance;
    }


    private ClientDAO() {
        clients = new ArrayList<>();
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(this.clients);
    }

    @Override
    public void update(UUID id, Client item) {
        Optional<Client> client = this.clients.stream()
                .filter(x -> x.getId().equals(id)).findFirst();

        Client it = client.get();

        this.clients.remove(it);
        this.clients.add(item);

    }

    @Override
    public void delete(UUID id) {
        this.clients.remove(this.getEntity(id));

    }

    @Override
    public boolean addEntity(Client entity) {
        if (entity != null) {
            this.clients.add(entity);

            return true;
        }
        return false;
    }

    @Override
    public Client getEntity(UUID id) {

        Optional<Client> client = this.clients.stream().filter(x -> x.getId().equals(id)).findFirst();

        return client.isPresent() ? client.get() : new Client("none", "");

    }
}
