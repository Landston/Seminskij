package com.Models.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class Book implements Serializable {

    private String name;
    private UUID uuid;
    private BookStatus status;
    private String genre;
    private double cost;
    private int year;
    private LocalDate dateOfAdmission;

    public Book(){
        Random random = new Random();
        this.dateOfAdmission =  LocalDate.now();
        this.uuid = UUID.randomUUID();
        this.status = BookStatus.RESERV;



    }

    public Book(String name, String genre, int year, double cost) {
        this.uuid = UUID.randomUUID();
        this.dateOfAdmission = LocalDate.now();
        this.status = BookStatus.RESERV;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid){
        this.uuid = uuid;
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
                ", uuid=" + uuid +
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
