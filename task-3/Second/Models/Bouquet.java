package main.Task3.Second.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bouquet {

    private UUID uuid;
    private List<Flower> bouquet;

    public Bouquet(){

        this.uuid = UUID.randomUUID();
        bouquet = new ArrayList<Flower>();

    }


    public List<Flower> getBouquet() {
        return bouquet;
    }

    public void setBouquet(List<Flower> bouquet) {

        this.bouquet = bouquet;

    }


    public UUID getUuid() {
        return uuid;
    }
}
