package com.senla.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class Book extends AbstractEntity implements Serializable {

    private String name;
    private BookStatus status;
    private String genre;
    private double cost;
    private int year;
    private LocalDate dateOfAdmission;


    public Book(){
        super();
        Random random = new Random();
        this.dateOfAdmission =  LocalDate.now();
        this.status = BookStatus.RESERVED;



    }

    public Book(String name, String genre, int year, double cost) {
        super();
        this.dateOfAdmission = LocalDate.now();
        this.status = BookStatus.RESERVED;
        this.name = name;
        this.cost = cost;
        this.genre = genre;
        this.year = year;

    }
    public Book(String name, String genre, int year, double cost, BookStatus status) {
        super();
        this.dateOfAdmission = LocalDate.now();
        this.status = status;
        this.name = name;
        this.cost = cost;
        this.genre = genre;
        this.year = year;

    }
    public Book(UUID id, String name, String genre, int year, double cost, BookStatus status) {
        setUuid(id);
        this.dateOfAdmission = LocalDate.now();
        this.status = status;
        this.name = name;
        this.cost = cost;
        this.genre = genre;
        this.year = year;

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

    @Override
    public String toString() {
        return "Book:" +
                "name='" + name + '\'' +
                ", uuid=" + this.getUuid() +
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
