package main.Task3.Second;


import main.Task3.Second.Models.Begonia;
import main.Task3.Second.Models.Bouquet;
import main.Task3.Second.Models.Rose;
import main.Task3.Second.Models.Worker;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {


        Rose rose = new Rose(45,"Red",false);
        Rose rose1 = new Rose(45,"White",true);
        Rose rose2 = new Rose(45,"Red",false);
        Begonia begonia = new Begonia(200, "Red",true);
        Begonia begonia1 = new Begonia(200, "Blue",true);
        Begonia begonia2 = new Begonia(200, "Green",true);

        Bouquet bouquet = new Bouquet();

        Worker worker = new Worker("Jake", "Manager");

        worker.addFlowerToBouquet(bouquet.getBouquet(), rose);
        worker.addFlowerToBouquet(bouquet.getBouquet(), rose1);
        worker.addFlowerToBouquet(bouquet.getBouquet(), rose2);
        worker.addFlowerToBouquet(bouquet.getBouquet(), begonia);
        worker.addFlowerToBouquet(bouquet.getBouquet(), begonia1);
        worker.addFlowerToBouquet(bouquet.getBouquet(), begonia2);

        System.out.println(bouquet.getUuid());

        System.out.println(worker.costOfBouquet(bouquet.getBouquet())   );



        bouquet = worker.newBouquet();

        System.out.println(bouquet.getUuid());
    }
}
