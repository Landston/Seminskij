package main.Task3.Fourth.DAO;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.Order;
import main.Task3.Fourth.Models.Request;
import main.Task3.Fourth.api.DAO.IRequestDAO;

import javax.lang.model.type.MirroredTypeException;
import java.util.List;
import java.util.UUID;

public class RequestDAO implements IRequestDAO {

    private List<Request> requests;

    public RequestDAO(List<Request> requests){

        this.requests = requests;
    }

    @Override
    public List<Request> getAll() {
        return requests;
    }

    @Override
    public boolean update(UUID id, Request item) {

        for (Request req : this.requests) {

            if (id.equals(req.getUuid())) {

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
