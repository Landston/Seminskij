package com.Models.DAO;


import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.Models.Order;
import com.Models.Serializable.Serializer;
import com.Models.api.DAO.IOrderDAO;

import java.util.*;

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
