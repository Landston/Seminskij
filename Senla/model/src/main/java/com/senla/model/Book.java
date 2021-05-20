package com.senla.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Log4j
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable, AbstractEntity {


    @Id
    @GeneratedValue
    @Column(name = "id_book")
    private  UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    @Column(name = "name")
    private String name;
    @Column(name ="status")
    @Enumerated(value = EnumType.STRING)
    private BookStatus status;
    @Column(name ="genre")
    private String genre;
    @Column(name ="cost")
    private double cost;
    @Column(name ="year")
    private int year;
    @Column(name ="date_Of_Admission")
    private LocalDate dateOfAdmission;
    @ManyToMany(
            mappedBy = "booksToOrder",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)

    private Set<Order> orders;


    public Book(){

        Random random = new Random();
        this.dateOfAdmission =  LocalDate.now();
        this.status = BookStatus.RESERVED;



    }

    public Book(String name, String genre, int year, double cost) {

        this.dateOfAdmission = LocalDate.now();
        this.status = BookStatus.RESERVED;
        this.name = name;
        this.cost = cost;
        this.genre = genre;
        this.year = year;

    }
    public Book(String name, String genre, int year, double cost, BookStatus status) {

        this.dateOfAdmission = LocalDate.now();
        this.status = status;
        this.name = name;
        this.cost = cost;
        this.genre = genre;
        this.year = year;

    }
    public Book(UUID id, String name, String genre, int year, double cost, BookStatus status) {

        this.dateOfAdmission = LocalDate.now();
        this.status = status;
        this.name = name;
        this.cost = cost;
        this.genre = genre;
        this.year = year;

    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost){
        if(cost >= 0 ) this.cost = cost;
    }

    public double getCost(){return this.cost;}
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
//    public Set<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<Order> orders) {
//        this.orders = orders;
//    }


    @Override
    public String toString() {
        return "Book:" +
                "name='" + name + '\'' +
                ", uuid=" + this.getId() +
                ", status=" + status +
                ", genre='" + genre + '\'' +
                ", cost=" + cost +
                ", year=" + year +
                ", date = " + dateOfAdmission + '\n';
    }

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDate dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }


}
