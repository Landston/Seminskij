package com.senla.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "request")
public class Request implements Serializable, AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_request")
    private  UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book")
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

    public void increaseRequestCount(){
        this.count +=1;
    }

    public void decreaseRequestCount(){
        this.count -=1;
    }

    @Override
    public String toString() {
        return "\nRequest{ " +
                "requestedBooks=" + requestedBooks +
                ", count = " + count +
                ", uuid = " + getId() +
    " status : " + requestOpenClose +"}";
    }
}
