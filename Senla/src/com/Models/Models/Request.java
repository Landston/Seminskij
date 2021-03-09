package com.Models.Models;

import java.io.Serializable;

import java.util.UUID;

public class Request extends AEntityID  implements Serializable {

    private  Book requestedBooks;
    private int count = 1;
    private boolean requestOpenClose;

    public Request(){
        super();
        this.requestOpenClose = true;
    }

    public Request(Book newBookToRequest){
        super();
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

    public void setRequestOpenClose(boolean value){
        this.requestOpenClose = value;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestedBooks=" + requestedBooks +
                ", count=" + count +
                ", uuid=" + getUuid() +
                '}';
    }
}
