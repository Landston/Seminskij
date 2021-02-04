package main.Task3.Third.classes.Models;

import main.Task3.Third.classes.Interfaces.IProductPart;

public class Tower implements IProductPart {

    private double armorThinkness;
    private String name;


    public Tower(double armorThinkness, String name) {
        this.armorThinkness = armorThinkness;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public double getArmorThinkness() {
        return armorThinkness;
    }

    @Override
    public String toString() {
        return "Tower{" +
                "armorThinkness=" + armorThinkness +
                ", name='" + name + '\'' +
                '}';
    }
}
