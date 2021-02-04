package main.Task3.Fourth.Models;

import main.Task3.Fourth.Services.BookService;

import java.util.UUID;

public class Book {

    private String name;
    private UUID uuid;
    private BookStatus status;
    private String genre;
    private double cost;
    private int year;

    public Book(){}

    public Book(String name, String genre, int year) {

        this.uuid = UUID.randomUUID();
        this.name = name;
        this.status = BookStatus.RESERV;
        this.genre = genre;
        this.year = year;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", uuid=" + uuid +
                ", status=" + status +
                ", genre='" + genre + '\'' +
                ", cost=" + cost +
                ", year=" + year +
                '}';
    }
}
