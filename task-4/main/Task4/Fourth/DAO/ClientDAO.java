package main.Task3.Fourth.DAO;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.Client;
import main.Task3.Fourth.Models.Order;
import main.Task3.Fourth.api.DAO.IClientDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientDAO  implements IClientDAO  {

    private List<Client> clients;

    public ClientDAO(List<Client> clients) {
        this.clients = clients;
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
            System.out.println("Client has added " + entity.toString());
            return true;
        }
        return  false;
    }

    @Override
    public Client getEntity(UUID id) {

        for (Client bk : this.clients) {
            if (id.equals(bk.getId())) return bk;

        }
        return null;
    }
}
