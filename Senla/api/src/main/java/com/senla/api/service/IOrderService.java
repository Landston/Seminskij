package com.senla.api.service;



import com.senla.api.exception.service.ServiceException;
import com.senla.model.Client;
import com.senla.model.Order;
import com.senla.model.Book;
import com.senla.model.dto.BookDTO;
import com.senla.model.dto.OrderDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public interface IOrderService {

    OrderDTO closeOrder(UUID uuid) throws ServiceException;

    OrderDTO createOrder(UUID book, UUID client) throws ServiceException;

    OrderDTO cancelOrder(UUID id) throws ServiceException;

    OrderDTO orderDone(UUID id) throws ServiceException;

    List<OrderDTO> getSortedOrders(String condition) throws IOException, ServiceException;

    List<OrderDTO> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) throws ServiceException;

    List<OrderDTO> getAll() throws ServiceException;

    BookDTO addBookToOrder(UUID uuid, BookDTO bookDTO) throws ServiceException;

    Long amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to ) throws ServiceException;

    OrderDTO getOrderByID(UUID uuid) throws ServiceException;

    double getTotalRevenue() throws ServiceException;

    OrderDTO delete(UUID uuid) throws ServiceException;

    BookDTO deleteBookFromOrder(UUID uuid, BookDTO bookDTO) throws ServiceException;

    void orderOpen(UUID uuid) throws ServiceException;

}
