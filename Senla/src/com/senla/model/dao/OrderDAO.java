package com.senla.model.dao;


import com.senla.di.annotations.Singleton;
import com.senla.model.models.Order;
import com.senla.model.serializable.Serializer;
import com.senla.model.api.dao.IOrderDAO;

import java.util.*;

@Singleton
public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

    private static OrderDAO instance;

    private OrderDAO() {
        repository = new ArrayList<>();
        repository = Serializer.deserialize(Order.class);
    }

    public static OrderDAO getInstance() {
        instance = Objects.requireNonNullElse(instance, new OrderDAO());
        return instance;
    }

    public void delete(Order order) {
        this.repository.remove(order);
    }



}
