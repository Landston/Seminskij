package com.senla.model;

import com.senla.model.utill.OrderUtil;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.core.appender.ScriptAppenderSelector;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "orders")
public class Order implements Serializable, AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_order")
    private  UUID id;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_books",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_book")
    )
    private List<Book> booksToOrder;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "date_of_execution")
    private LocalDate dateOfExecution;


    public Order(UUID id, Client client, OrderStatus status, double totalPrice, LocalDate dateOfExecution) {
        setId(id);
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
        this.dateOfExecution = LocalDate.now();
        this.totalPrice = OrderUtil.countBooksTotalCost(books);

    }

    public Order(Book book, Client client) {
        super();
        this.client = client;
        this.booksToOrder = new ArrayList<>();
        this.booksToOrder.add(book);
        this.status = OrderStatus.OPEN;
        this.dateOfExecution = LocalDate.now();
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
        this.dateOfExecution = LocalDate.now();
        this.status = OrderStatus.OPEN;
    }

    @Override
    public String toString() {
        return "Order:" +
                "booksToOrder=" + booksToOrder.toString() +
                "\nuiId=" + getId() +
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

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

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
