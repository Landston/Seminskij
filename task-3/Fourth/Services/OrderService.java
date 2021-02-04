package main.Task3.Fourth.Services;

import main.Task3.Fourth.DAO.OrderDAO;
import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.BookStatus;
import main.Task3.Fourth.Models.Order;
import main.Task3.Fourth.Models.OrderStatus;
import main.Task3.Fourth.api.Service.IOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService implements IOrderService {

    private OrderDAO orderDAO;

    public OrderService(OrderDAO dao) {

        this.orderDAO = dao;
    }

    public void closeOrder(UUID id) {

        this.orderDAO.getEntity(id).setStatus(OrderStatus.CLOSED);
    }

    public Order createOrder(Book book, String client) {

        if (BookStatus.ABSENT.equals(book.getStatus())) {
            return new Order(client);
        }
        else {
            List<Book> list = new ArrayList<Book>();
            list.add(book);
            Order order = new Order(list, client);
            return order;
        }

    }

    public void cancelOrder(UUID id) {

        this.orderDAO.getEntity(id).setStatus(OrderStatus.CANCELED);

    }

    @Override
    public void orderDone(UUID id) {

        this.orderDAO.getEntity(id).setStatus(OrderStatus.CLOSED);
    }


}
