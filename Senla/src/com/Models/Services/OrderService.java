package com.Models.Services;

import com.Models.DAO.OrderDAO;


import com.Models.Models.*;
import com.Models.api.DAO.IOrderDAO;
import com.Models.api.Service.IOrderService;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class OrderService implements IOrderService {

    private IOrderDAO orderDAO;
    private Map<String, Comparator<Order>> sort;

    public OrderService(OrderDAO dao) {
        this.orderDAO = dao;
        this.init();
    }

    private void init() {
        this.sort = new HashMap<>();

        this.sort.put("ByCost", Comparator.comparing(Order::getTotalPrice));
        this.sort.put("ByDateOfExecution", Comparator.comparing(Order::getDateOfExecution));
        this.sort.put("ByStatus", Comparator.comparing(Order::getStatus));

    }

    public List<Order> getSortedOrders(Comparator<Order> condition) {
        List<Order> list = new ArrayList<Order>();

        list.addAll(this.orderDAO.getAll());
        list.sort(condition);

        return list;

    }

    public List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) {
        if (from == null && to == null) return null;

        List<Order> orders = new ArrayList<>();

        orders = this.orderDAO.getAll().stream().filter(x -> x.getStatus().equals(OrderStatus.CLOSED))
                .filter(y -> y.getDateOfExecution().isAfter(from) && y.getDateOfExecution().isBefore(to)
                        || y.getDateOfExecution().equals(from) || y.getDateOfExecution().equals(to))
                .collect(Collectors.toList());
        orders.sort(this.sort.get(condition));

        return orders;
    }

    public List<Order> getAllOrdes() {return new ArrayList<>(this.orderDAO.getAll());}

    public boolean delete(Order order) {
        this.orderDAO.delete(order);

        return true;
    }

    public void closeOrder(UUID id) { this.orderDAO.getEntity(id).setStatus(OrderStatus.CLOSED); }

    @Override
    public Order createOrder(Book book, Client client) {
        Order order = new Order(new ArrayList<Book>(), client);

        order.getBooksToOrder().add(book);
        this.orderDAO.addEntity(order);

        return order;
    }

    public void addBookToOrder(UUID uuid, Book book) {
        if (book == null) return;

        Order order = this.orderDAO.getEntity(uuid);

        order.addBook(book);
    }

    @Override
    public Long amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to) {
        if (from == null && to == null) return new Long(0);

        Long count = this.orderDAO.getAll().stream().filter(x -> x.getStatus().equals(OrderStatus.CLOSED))
                .filter(y -> y.getDateOfExecution().isAfter(from) && y.getDateOfExecution().isBefore(to)
                        || y.getDateOfExecution().equals(from) || y.getDateOfExecution().equals(to))
                .count();

        return count;
    }

    public Order getOrderByClientName(String name) {
        Optional<Order> order = this.orderDAO.getAll().stream()
                .filter(x -> x.getClient().getName().equals(name))
                .findFirst();

        return order.isPresent() ? order.get() : new Order(new Client(""));
    }

    public void cancelOrder(UUID id) { this.orderDAO.getEntity(id).setStatus(OrderStatus.CANCELED); }

    @Override
    public void orderDone(UUID id) { this.orderDAO.getEntity(id).setStatus(OrderStatus.CLOSED); }


}
