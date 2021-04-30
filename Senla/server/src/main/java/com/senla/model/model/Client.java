package com.senla.model.model;



import java.io.Serializable;
import java.util.UUID;


public class Client extends AbstractEntity implements Serializable {

    private String name;
    private String mail;

    @Override
    public String toString() {
        return "Client : " + this.getUuid() + " "+
                "name='" + name + '\'' +
                ", mail='" + mail + "\n" ;
    }

    public Client(String name, String mail) {
        super();
        this.name = name;
        this.mail = mail;
    }
    public Client( UUID id, String name, String mail) {
        this.setUuid(id);
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
