package com.Models.DAO;

import com.Models.Models.Order;
import com.Models.Models.Request;
import com.Models.api.DAO.IRequestDAO;

import java.util.*;

public class RequestDAO implements IRequestDAO {

    private List<Request> requests;
    private static RequestDAO instance;

    private RequestDAO(){
        requests = new ArrayList<>();
    }

    public static RequestDAO getInstance(){
        instance = Objects.requireNonNullElse(instance,new RequestDAO());

        return instance;
    }

    @Override
    public List<Request> getAll() {
        return new ArrayList<>(this.requests);
    }

    @Override
    public void update(UUID id, Request item) {
        Optional<Request> request = this.requests.stream()
                .filter(x -> x.getUuid().equals(id)).findFirst();
        Request it = request.get();

        this.requests.remove(it);
        this.requests.add(item);
    }


    @Override
    public void delete(UUID id) {
        this.requests.remove(this.getEntity(id));
    }

    @Override
    public boolean addEntity(Request entity) {
        if (entity != null) {
            this.requests.add(entity);

            return true;
        }

        return false;
    }

    @Override
    public Request getEntity(UUID id) {

        Optional<Request> request = this.requests.stream()
                .filter(x -> x.getUuid().equals(id)).findFirst();

        return request.orElseGet(Request::new);
    }
}
