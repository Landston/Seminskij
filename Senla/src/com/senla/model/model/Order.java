package com.senla.model.model;

import com.senla.model.util.OrderUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order extends AEntityID implements Serializable {

    private List<Book> booksToOrder;
    private Client client;
    private OrderStatus status;
    private double totalPrice;
    private LocalDate dateOfExecution;

    public Order(UUID id, Client client, OrderStatus status, double totalPrice, LocalDate dateOfExecution) {
        setUuid(id);
        this.client = client;
        this.status = status;
        this.totalPrice = totalPrice;
        this.dateOfExecution = dateOfExecution;

    }

    public Order(List<Book> books, Client client) {
        super();
        this.client = client;
        this.booksToOrder = books;
        this.status = OrderStatus.OPEN;
        this.dateOfExecution = LocalDate.of(0, 1, 1);
        this.totalPrice = OrderUtil.countBooksTotalCost(books);

    }

    public Order(Book book, Client client) {
        super();
        this.client = client;
        this.booksToOrder = new ArrayList<>();
        this.booksToOrder.add(book);
        this.status = OrderStatus.OPEN;
        this.dateOfExecution = LocalDate.of(0, 1, 1);
        this.totalPrice = OrderUtil.countBooksTotalCost(booksToOrder);

    }

    public Order(Client client) {
        super();
        this.booksToOrder = new ArrayList<>();
        this.client = client;
        this.dateOfExecution = LocalDate.of(0, 1, 1);
        this.status = OrderStatus.OPEN;
        this.totalPrice = 0;

    }

    public Order() {
        super();
        this.dateOfExecution = LocalDate.of(0, 1, 1);
        this.status = OrderStatus.OPEN;
    }

    @Override
    public String toString() {
        return "Order:" +
                "booksToOrder=" + booksToOrder.toString() +
                "\nuiId=" + getUuid() +
                "\n" + client.toString() +
                "\nstatus=" + status +
                "\ntotalPrice=" + totalPrice +
                "\ndateOfExecution=" + dateOfExecution;
    }

    public void addBook(Book book) {
        this.booksToOrder.add(book);
        this.totalPrice += book.getCost();

    }

    public void removeBook(Book book) {
        if(this.getBooksToOrder().size() != 0) {
            this.booksToOrder.remove(book);
            this.totalPrice -= book.getCost();
        }

    }

    public double countTotalCost() {
        return OrderUtil.countBooksTotalCost(booksToOrder);

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Book> getBooksToOrder() {
        return booksToOrder;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setDateOfExecution(LocalDate dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }

    public LocalDate getDateOfExecution() {
        return dateOfExecution;
    }

    public void setBooksToOrder(List<Book> booksToOrder) {
        this.booksToOrder = booksToOrder;
    }


    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
