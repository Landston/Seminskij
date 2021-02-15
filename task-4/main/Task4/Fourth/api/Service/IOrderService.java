package main.Task3.Fourth.api.Service;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.Client;
import main.Task3.Fourth.Models.Order;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public interface IOrderService {

    void closeOrder(UUID uuid);

    Order createOrder(Book book, Client client);

    void cancelOrder(UUID id);

    void orderDone(UUID id);

    List<Order> getSortedOrders(Comparator<Order> condition);

    List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, Comparator<Order> condition);

    List<Order> getAllOrdes();

    void addBookToOrder(UUID uuid, Book book);

    int amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to );




}
