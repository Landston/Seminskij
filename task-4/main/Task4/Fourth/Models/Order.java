package main.Task3.Fourth.Models;

import main.Task3.Fourth.Utils.OrderUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order implements Serializable {

    private List<Book> booksToOrder;
    private UUID uiId;
    private Client client;
    private OrderStatus status;
    private double totalPrice;
    private LocalDate dateOfExecution;


    public Order(List<Book> books, Client client) {

        Objects.requireNonNull(books, "Books added to Order shoud not be null");
        Objects.requireNonNull(client, "Client added to Order shoud not be null");
        this.uiId = UUID.randomUUID();
        this.client = client;
        this.booksToOrder = books;
        this.status = OrderStatus.OPEN;
        this.dateOfExecution = LocalDate.now();
        this.totalPrice = OrderUtil.countBooksTotalCost(books);

    }

    public Order(Client client) {

        this.booksToOrder = new ArrayList<>();
        this.uiId = UUID.randomUUID();
        this.client = client;
        this.status = OrderStatus.OPEN;
        this.dateOfExecution = LocalDate.now();
        this.totalPrice = 0;

    }

    @Override
    public String toString() {
        return "Order{" +
                "booksToOrder=" + booksToOrder +
                "\nuiId=" + uiId +
                "\nclient=" + client +
                "\nstatus=" + status +
                "\ntotalPrice=" + totalPrice +
                "\ndateOfExecution=" + dateOfExecution +
                '}';
    }

    public void addBook(Book book){ this.booksToOrder.add(book);
        this.totalPrice += book.getCost();

    }

    public double countTotalCost(){
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
