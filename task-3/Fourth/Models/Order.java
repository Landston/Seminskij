package main.Task3.Fourth.Models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {

    private List<Book> booksToOrder;
    private UUID uiId;
    private String client;
    private OrderStatus status;
    private double totalPrice;


    public Order(List<Book> book, String client){

        this.uiId = UUID.randomUUID();
        this.client = client;
        this.booksToOrder = book;
        this.status = OrderStatus.OPEN;

    }

    public Order(String clinet){

        this.uiId = UUID.randomUUID();
        this.client = clinet;
        this.status = OrderStatus.OPEN;

    }


    public List<Book> getBookToOrder() {
        return booksToOrder;
    }

    public void setBookToOrder(List<Book> booksToOrder) {

        this.booksToOrder = booksToOrder;

    }

    public UUID getUiId() {
        return uiId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
