package com.senla.model.api.service;

import com.senla.model.models.Book;
import com.senla.model.models.Client;
import com.senla.model.models.Order;
import com.senla.model.exceptions.ServiceException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IOrderService {

    void closeOrder(UUID uuid) throws ServiceException;

    Order createOrder(Book book, Client client) throws ServiceException;

    void cancelOrder(UUID id) throws ServiceException;

    void orderDone(UUID id) throws ServiceException;

    List<Order> getSortedOrders(String condition) throws IOException, ServiceException;

    List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) throws ServiceException;

    List<Order> getAllOrdes();

    void addBookToOrder(UUID uuid, Book book) throws ServiceException;

    Long amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to );

    Order getOrderByID(UUID uuid) throws ServiceException;

    double getTotalRevenue();

    void deleteOrder(UUID uuid) throws ServiceException;

    void deleteBookFromOrder(UUID uuid, Book book) throws ServiceException;

    void addOrder(Order order) throws ServiceException;

    void orderOpen(UUID uuid) throws ServiceException;

}
