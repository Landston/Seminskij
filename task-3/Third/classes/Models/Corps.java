package main.Task3.Third.classes.Models;

import main.Task3.Third.classes.Interfaces.IProductPart;

public class Corps implements IProductPart {

    private double armorThinknessOfLowerArmorPlate;
    private double sideArmorThinkness;
    private double backArmorThinkness;

    public Corps(double LAPArmor, double sideArmor, double backArmor) {

        this.armorThinknessOfLowerArmorPlate = LAPArmor;
        this.sideArmorThinkness = sideArmor;
        this.backArmorThinkness = backArmor;

    }

    public double getArmorThinknessOfLowerArmorPlate() {
        return armorThinknessOfLowerArmorPlate;
    }

    public double getSideArmorThinkness() {
        return sideArmorThinkness;
    }

    public double getBackArmorThinkness() {
        return backArmorThinkness;
    }

    @Override
    public String toString() {
        return "Corps{" +
                "armorThinknessOfLowerArmorPlate=" + armorThinknessOfLowerArmorPlate +
                ", sideArmorThinkness=" + sideArmorThinkness +
                ", backArmorThinkness=" + backArmorThinkness +
                '}';
    }
}
