package main.Task3.Second.Models;

import main.Task3.Second.main;

public abstract class Flower {


    private String color;

    private String name;

    private double cost;

    public Flower( double cost, String color){


        this.cost = cost;
        this.color = color;


    }


    public double getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


