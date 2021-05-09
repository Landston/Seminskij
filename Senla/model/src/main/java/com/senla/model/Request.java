package com.senla.model;

import java.io.Serializable;
import java.util.UUID;

public class Request extends AbstractEntity implements Serializable {

    private Book requestedBooks;
    private int count = 1;
    private boolean requestOpenClose;

    public Request(){
        super();
        this.requestOpenClose = true;
    }

    public Request(Book newBookToRequest){
        super();
        this.requestOpenClose = true;
        this.requestedBooks  = newBookToRequest;
    }

    public Request(UUID id, Book requestedBooks, int count, boolean requestOpenClose) {
        setUuid(id);
        this.requestedBooks = requestedBooks;
        this.count = count;
        this.requestOpenClose = requestOpenClose;
    }

    public boolean getRequestOpenClose() {
        return requestOpenClose;
    }

    public void setRequestedBooks(Book requestedBooks) {
        this.requestedBooks = requestedBooks;
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

    public Book getRequestedBook() {
        return requestedBooks;
    }

    public void setRequestOpenClose(boolean value){
        this.requestOpenClose = value;
    }

    @Override
    public String toString() {
        return "\nRequest{ " +
                "requestedBooks=" + requestedBooks +
                ", count = " + count +
                ", uuid = " + getUuid() +
    " status : " + getRequestOpenClose() +"}";
    }
}
