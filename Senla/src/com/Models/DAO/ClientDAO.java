package com.Models.DAO;

import com.Models.Models.Client;
import com.Models.api.DAO.IClientDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientDAO  implements IClientDAO  {

    private List<Client> clients;

    public ClientDAO(List<Client> clients) {
        this.clients = clients;
    }

    public ClientDAO() {
       clients = new ArrayList<>();
    }

    @Override
    public List<Client> getAll() {
        return new ArrayList<>(this.clients);
    }

    @Override
    public boolean update(UUID id, Client item) {
        for(Client bk : this.clients){
            if(id.equals(bk.getId())) {
                bk = item;

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        for(Client bk : this.clients){
            if(id.equals(bk.getId())) {
                this.clients.remove(bk);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addEntity(Client entity) {
        if (entity != null) {
            this.clients.add(entity);

            return true;
        }
        return  false;
    }

    @Override
    public Client getEntity(UUID id) {

        Optional<Client> client = this.clients.stream().filter(x -> x.getId().equals(id)).findFirst();

        return client.isPresent() ? client.get(): new Client("");

    }
}
