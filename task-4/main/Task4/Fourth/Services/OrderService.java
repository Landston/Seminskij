package main.Task3.Fourth.Services;

import main.Task3.Fourth.Comparators.BookComparators.BookDateOfWritingComparator;
import main.Task3.Fourth.DAO.OrderDAO;
import main.Task3.Fourth.Models.*;
import main.Task3.Fourth.api.DAO.IOrderDAO;
import main.Task3.Fourth.api.Service.IOrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class OrderService implements IOrderService {

    private IOrderDAO orderDAO;

    public OrderService(OrderDAO dao) {

        this.orderDAO = dao;
    }

    public List<Order> getSortedOrders(Comparator<Order> condition) {

        List<Order> list = new ArrayList<Order>();
        list.addAll(this.orderDAO.getAll());
        list.sort(condition);
        return list;

    }

    public List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, Comparator<Order> condition) {

        if (from == null && to == null) return null;

        List<Order> list = new ArrayList<Order>();

        for (Order ord : this.orderDAO.getAll()) {

            if (ord.getStatus().equals(OrderStatus.CLOSED)) {

                LocalDate orderClosedDate = ord.getDateOfExecution();


                if (orderClosedDate.isAfter(from) && orderClosedDate.isBefore(to) || orderClosedDate.equals(from) || orderClosedDate.equals(to)) {
                    list.add(ord);
                }

            }

        }
        list.sort(condition);
        return list;
    }

    public List<Order> getAllOrdes() {

        return this.orderDAO.getAll();
    }

    public boolean delete(Order order) {

        this.orderDAO.delete(order);
        return true;
    }

    public void closeOrder(UUID id) {

        this.orderDAO.getEntity(id).setStatus(OrderStatus.CLOSED);
    }

    public void addBookToOrder(UUID uuid, Book book) {

        if (book == null) {
            return;
        }
        Order order = this.orderDAO.getEntity(uuid);
        order.addBook(book);

    }

    @Override
    public int amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to) {

        if (from == null && to == null) return 0;
        int count = 0;

        for (Order ord : this.orderDAO.getAll()) {

            if (ord.getStatus().equals(OrderStatus.CLOSED)) {

                LocalDate orderClosedDate = ord.getDateOfExecution();
                if (orderClosedDate.isAfter(from) && orderClosedDate.isBefore(to) || orderClosedDate.equals(from) || orderClosedDate.equals(to)) {
                    count++;
                }


            }

        }
        return count;
    }

    public Order createOrder(Book book, Client client) {

        if (BookStatus.ABSENT.equals(book.getStatus())) {
            return new Order(client);
        } else {
            List<Book> list = new ArrayList<Book>();
            list.add(book);

            Order order = new Order(list, client);

            return order;
        }

    }

    public Order getOrderByClientName(String name) {

        for (Order ord : this.orderDAO.getAll()) {

            if (name.equals(ord.getClient().getName())) return ord;
        }
        return null;
    }

    public void cancelOrder(UUID id) {

        this.orderDAO.getEntity(id).setStatus(OrderStatus.CANCELED);

    }

    @Override
    public void orderDone(UUID id) {

        this.orderDAO.getEntity(id).setStatus(OrderStatus.CLOSED);
    }


}
