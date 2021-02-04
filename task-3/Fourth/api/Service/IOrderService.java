package main.Task3.Fourth.api.Service;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.Order;

import java.util.UUID;

public interface IOrderService {

    void closeOrder(UUID uuid);

    Order createOrder(Book book, String client);

    void cancelOrder(UUID id);

    void orderDone(UUID id);




}
