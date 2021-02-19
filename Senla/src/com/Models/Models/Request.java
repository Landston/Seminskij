package com.Models.Models;

import java.io.Serializable;

import java.util.UUID;

public class Request implements Serializable {

    private  Book requestedBooks;
    private int count = 1;
    private  final UUID uuid;

    public Request(){
        this.uuid = UUID.randomUUID();
    }

    public Request(Book newBookToRequest){

        this.uuid = UUID.randomUUID();
        this.requestedBooks  = newBookToRequest;


    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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
