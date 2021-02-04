package main.Task3.Second.Models;

import java.util.ArrayList;
import java.util.List;

public class Worker {

    private String name;
    private String position;

    public Worker(String name, String position) {
        this.name = name;
        this.position = position;
    }


    public void addFlowerToBouquet(List<Flower> bouquet, Flower flower){

        bouquet.add(flower);



    }

    public Bouquet newBouquet(){



        return new Bouquet();

    }

    public double costOfBouquet(List<Flower> bouquet){

        double cost = 0;
        for (Flower flower: bouquet) {

            cost +=flower.getCost();

        }

        return cost;

    }

}
