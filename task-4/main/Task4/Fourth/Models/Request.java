package main.Task3.Fourth.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Request {

    private final Book requestedBooks;
    private int count = 1;
    private final UUID uuid;

    public Request(Book newBookToRequest){

        this.uuid = UUID.randomUUID();
        this.requestedBooks  = newBookToRequest;


    }


    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count = count;
    }

    public void increaseRequestCount(){

        this.count +=1;
    }
    public void decreaseRequestCount(){

        this.count -=1;
    }

    public Book getRequestedBooks() {
        return requestedBooks;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestedBooks=" + requestedBooks +
                ", count=" + count +
                ", uuid=" + uuid +
                '}';
    }
}
