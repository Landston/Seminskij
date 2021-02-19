package com.Models.DAO;

import com.Models.Models.Request;
import com.Models.api.DAO.IRequestDAO;

import java.util.List;
import java.util.UUID;

public class RequestDAO implements IRequestDAO {

    private List<Request> requests;

    public RequestDAO(){


    }

    private void init(){


    }

    @Override
    public List<Request> getAll() {
        return requests;
    }

    @Override
    public boolean update(UUID id, Request item) {

        for (Request req : this.requests) {

            if (req.getUuid().equals(id)) {

                req = item;


                return true;

            }
        }
        return false;
    }


    @Override
    public boolean delete(UUID id) {
        for (Request req : this.requests) {

            if (id.equals(req.getUuid())) {

                this.requests.remove(req);

                return true;

            }
        }
        return false;
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

        for(Request req : this.requests){

            if(id.equals(req.getUuid())) {
                return req;

            }
        }
        return null;
    }
}
