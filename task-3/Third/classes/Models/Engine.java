package main.Task3.Third.classes.Models;

import main.Task3.Third.classes.Interfaces.IProductPart;

public class Engine implements IProductPart {

    private String name;
    private double engineHP;
    private int amountOfCylinders;


    public Engine(String name, double engineHP, int amountOfCylinders) {
        this.name = name;
        this.engineHP = engineHP;
        this.amountOfCylinders = amountOfCylinders;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                ", engineHP=" + engineHP +
                ", amountOfCylinders=" + amountOfCylinders +
                '}';
    }
}
