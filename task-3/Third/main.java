package main.Task3.Third;

import main.Task3.Third.classes.LineBuilders.CorpsLineBuilder;
import main.Task3.Third.classes.LineBuilders.EngineLineBuilder;
import main.Task3.Third.classes.LineBuilders.TankLineBuilder;
import main.Task3.Third.classes.LineBuilders.TowerLineBuilder;
import main.Task3.Third.classes.Models.Tank;

public class main {

    public static void main(String[] args){

        TowerLineBuilder towerLineBuilder = new TowerLineBuilder();

        EngineLineBuilder engineLineBuilder = new EngineLineBuilder();

        CorpsLineBuilder corpsLineBuilder = new CorpsLineBuilder();


        TankLineBuilder tank = new TankLineBuilder(engineLineBuilder, corpsLineBuilder, towerLineBuilder);


        Tank created = new Tank();

        created = (Tank)tank.assebmleProduct(created);

        System.out.println(created.toString());



    }
}
