package com.senla.model.dao;

import com.senla.di.annotations.Singleton;
import com.senla.model.models.Request;
import com.senla.model.serializable.Serializer;
import com.senla.model.api.dao.IRequestDAO;

import java.util.*;

@Singleton
public class RequestDAO extends AbstractDAO<Request> implements IRequestDAO {

    private List<Request> requests;
    private static RequestDAO instance;

    private RequestDAO(){
        requests = new ArrayList<>();
        requests = Serializer.deserialize(Request.class);
    }

    public static RequestDAO getInstance(){
        instance = Objects.requireNonNullElse(instance,new RequestDAO());

        return instance;
    }


}
