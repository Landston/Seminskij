package com.Models.DAO;

import com.Models.Models.Order;
import com.Models.Models.Request;
import com.Models.api.DAO.IRequestDAO;

import java.util.*;

public class RequestDAO extends AbstractDAO<Request> implements IRequestDAO {

    private List<Request> requests;
    private static RequestDAO instance;

    private RequestDAO(){
        requests = new ArrayList<>();
    }

    public static RequestDAO getInstance(){
        instance = Objects.requireNonNullElse(instance,new RequestDAO());

        return instance;
    }


}
