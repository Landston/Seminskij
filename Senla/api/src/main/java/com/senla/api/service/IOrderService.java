package com.senla.api.service;



import com.senla.api.exception.service.ServiceException;
import com.senla.model.Client;
import com.senla.model.Order;
import com.senla.model.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public interface IOrderService {

    void closeOrder(UUID uuid) throws ServiceException;

    Order createOrder(UUID book, UUID client) throws ServiceException;

    void cancelOrder(UUID id) throws ServiceException;

    void orderDone(UUID id) throws ServiceException;

    List<Order> getSortedOrders(String condition) throws IOException, ServiceException;

    List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) throws ServiceException;

    List<Order> getAll() throws ServiceException;

    void addBookToOrder(UUID uuid, Book book) throws ServiceException;

    Long amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to ) throws ServiceException;

    Order getOrderByID(UUID uuid) throws ServiceException;

    double getTotalRevenue() throws ServiceException;

    void delete(UUID uuid) throws ServiceException;

    void deleteBookFromOrder(UUID uuid, Book book) throws ServiceException;

    void orderOpen(UUID uuid) throws ServiceException;

}
