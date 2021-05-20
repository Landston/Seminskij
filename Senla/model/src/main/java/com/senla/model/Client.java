package com.senla.model;



import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor

public class Client implements Serializable, AbstractEntity {
    @Id
    @GeneratedValue
    private  UUID id;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    @Column(name = "name")
    private String name;
    @Column(name = "mail")
    private String mail;

    @Override
    public String toString() {
        return "Client : " + this.getId() + " "+
                "name='" + name + '\'' +
                ", mail='" + mail + "\n" ;
    }

    public Client(){

    }
    public Client(String name, String mail) {
        super();
        this.name = name;
        this.mail = mail;
    }
    public Client( UUID id, String name, String mail) {
        this.setId(id);
        this.name = name;
        this.mail = mail;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
