package com.Models.api.Service;

import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.Models.Order;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public interface IOrderService {

    void closeOrder(UUID uuid);

    Order createOrder(Book book, Client client);

    void cancelOrder(UUID id);

    void orderDone(UUID id);

    List<Order> getSortedOrders(String condition) throws IOException;

    List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition);

    List<Order> getAllOrdes();

    void addBookToOrder(UUID uuid, Book book);

    Long amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to );




}
