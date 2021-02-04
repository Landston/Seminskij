package main.Task3.Second.Models;

public class Begonia  extends Flower{


    private boolean circumcised;

    public Begonia( double cost, String color, boolean circumcised){

        super(cost, color);

        this.setName("Begonia");
        this.circumcised = circumcised;


    }

}
