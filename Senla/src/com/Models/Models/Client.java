package com.Models.Models;



import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Client extends AEntityID implements Serializable {

    private String name;
    private String mail;

    @Override
    public String toString() {
        return "Client " +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'';
    }

    public Client(String name, String mail) {
        super();
        this.name = name;
        this.mail = mail;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
