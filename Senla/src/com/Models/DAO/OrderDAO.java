package com.Models.DAO;


import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.Models.Order;
import com.Models.api.DAO.IOrderDAO;

import java.util.*;

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

    private List<Order> orders;
    private static OrderDAO instance;

    private OrderDAO() {
        orders = new ArrayList<>();

    }

    public static OrderDAO getInstance() {
        instance = Objects.requireNonNullElse(instance, new OrderDAO());
        return instance;
    }

    public void delete(Order order) {
        this.orders.remove(order);
    }



}
