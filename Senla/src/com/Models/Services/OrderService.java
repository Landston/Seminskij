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
    private static OrderService instance;

    public OrderService() {
        this.orderDAO = OrderDAO.getInstance();
        this.init();
    }

    public static OrderService getInstance() {
        instance = Objects.requireNonNullElse(instance, new OrderService());

        return instance;
    }

    private void init() {
        this.sort = new HashMap<>();

        this.sort.put("ByCost", Comparator.comparing(Order::getTotalPrice));
        this.sort.put("ByDateOfExecution", Comparator.comparing(Order::getDateOfExecution));
        this.sort.put("ByStatus", Comparator.comparing(Order::getStatus));

    }

    public List<Order> getSortedOrders(String condition)  {
        try {
            List<Order> list = new ArrayList<Order>(this.orderDAO.getAll());

            list.sort(sort.get(condition));

            return list;
        } catch (NullPointerException e) {
  //          logger.log("Sorting condition is invalid", e);
            return Collections.emptyList();
        }

    }

    public Order getOrderByID(UUID uuid) {
        try {
            return this.orderDAO.getEntity(uuid);

        } catch (Exception e) {

        }

        return new Order();
    }

    public List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) {
        if (from == null && to == null) return Collections.emptyList();

       try {
           List<Order> orders;

           orders = this.orderDAO.getAll().stream().filter(x -> x.getStatus().equals(OrderStatus.CLOSED))
                   .filter(y -> y.getDateOfExecution().isAfter(from) && y.getDateOfExecution().isBefore(to)
                           || y.getDateOfExecution().equals(from) || y.getDateOfExecution().equals(to))
                   .collect(Collectors.toList());
           orders.sort(this.sort.get(condition));

           return orders;
       } catch (NullPointerException nullPointerException) {


           return Collections.emptyList();
       }

    }

    public double getTotalRevenue() {
        double revenue = 0;
        for (Order ord : new ArrayList<Order>(this.orderDAO.getAll())) {

            revenue += ord.getTotalPrice();
        }
        return revenue;
    }

    public List<Order> getAllOrdes() {
        return new ArrayList<>(this.orderDAO.getAll());
    }

    public boolean delete(Order order) {
        this.orderDAO.delete(order);

        return true;
    }

    public void deleteOrder(UUID uuid) {

        this.orderDAO.delete(this.getOrderByID(uuid));

    }

    public void closeOrder(UUID id) {
        Order order = this.orderDAO.getEntity(id);

        order.setStatus(OrderStatus.CLOSED);
        order.setDateOfExecution(LocalDate.now());
    }

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

    public void deleteBookFromOrder(UUID uuid, Book book) {
        Order order = this.orderDAO.getEntity(uuid);

        order.removeBook(book);
    }

    @Override
    public Long amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to) {
        if (from == null || to == null) return new Long(0);

        Long count = this.orderDAO.getAll().stream().filter(x -> x.getStatus().equals(OrderStatus.CLOSED))
                .filter(y -> y.getDateOfExecution().isAfter(from) && y.getDateOfExecution().isBefore(to)
                        || y.getDateOfExecution().equals(from) || y.getDateOfExecution().equals(to))
                .count();

        return count;
    }

    public void updateOrder(UUID id, Book book, Client client) {

        Order order = this.orderDAO.getEntity(id);

    }

    public Order getOrderByClientName(String name) {
        Optional<Order> order = this.orderDAO.getAll().stream()
                .filter(x -> x.getClient().getName().equals(name))
                .findFirst();

        return order.orElseGet(() -> new Order(new Client("", "")));
    }

    public void addOrder(Order order) {
        this.orderDAO.addEntity(order);
    }

    public void cancelOrder(UUID id) {
        this.orderDAO.getEntity(id).setStatus(OrderStatus.CANCELED);
    }

    @Override
    public void orderDone(UUID id) {
        this.orderDAO.getEntity(id).setStatus(OrderStatus.CLOSED);
        this.orderDAO.getEntity(id).setDateOfExecution(LocalDate.now());
    }

    public void orderOpen(UUID id) {
        this.orderDAO.getEntity(id).setStatus(OrderStatus.OPEN);
    }


}
