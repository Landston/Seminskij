package com.Models.api.Service;

import com.Models.Models.Book;
import com.Models.Models.Client;
import com.Models.Models.Order;
import com.Models.exceptions.ServiceException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
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




}
