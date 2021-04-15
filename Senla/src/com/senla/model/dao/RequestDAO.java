package com.senla.model.dao;

import com.senla.di.annotations.Singleton;
import com.senla.model.models.Request;
import com.senla.model.serializable.Serializer;
import com.senla.model.api.dao.IRequestDAO;

import java.util.*;


public class RequestDAO extends AbstractDAO<Request> implements IRequestDAO {

    private List<Request> requests;

    public RequestDAO(){
        requests = new ArrayList<>();
        requests = Serializer.deserialize(Request.class);
    }


}
