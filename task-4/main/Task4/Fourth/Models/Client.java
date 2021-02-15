package main.Task3.Fourth.Models;

import java.util.UUID;

public class Client {

    private String name;
    private String mail;
    private final UUID id;

    public Client(String name) {
        this.name = name;
        this.id = UUID.randomUUID();

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
