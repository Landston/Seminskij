package com.Models.DAO;


import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.Models.Order;
import com.Models.api.DAO.IOrderDAO;

import java.util.*;

public class OrderDAO implements IOrderDAO {

    private List<Order> orders;
    private Map<String, Comparator<Order>> sort;
    private static OrderDAO instance;

    private OrderDAO() {
        orders = new ArrayList<>();
        this.init();
    }

    public static OrderDAO getInstance() {
        instance = Objects.requireNonNullElse(instance, new OrderDAO());

        return instance;
    }

    private void init() {
        this.sort = new HashMap<>();

        this.sort.put("ByCost", Comparator.comparing(Order::getTotalPrice));
        this.sort.put("ByDateOfExecution", Comparator.comparing(Order::getDateOfExecution));
        this.sort.put("ByStatus", Comparator.comparing(Order::getDateOfExecution));
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(this.orders);
    }

    @Override
    public void update(UUID id, Order item) {

        Optional<Order> order = this.orders.stream()
                .filter(x -> x.getUuId().equals(id)).findFirst();

        Order it = order.get();

        this.orders.remove(it);
        this.orders.add(item);

    }

    public void delete(Order order) {
        this.orders.remove(order);
    }

    @Override
    public void delete(UUID uuid) {
        this.orders.remove(this.getEntity(uuid));
    }

    @Override
    public boolean addEntity(Order entity) {
        if (entity != null) {
            this.orders.add(entity);

            return true;
        }

        return false;
    }

    @Override
    public Order getEntity(UUID id) throws NullPointerException {

        Optional<Order> order = this.orders.stream()
                .filter(x -> x.getUuId().equals(id)).findFirst();

        return order.orElseGet(null);
    }
}
