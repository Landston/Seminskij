package main.Task3.Third.classes.Models;

import main.Task3.Third.classes.Interfaces.IProduct;
import main.Task3.Third.classes.Interfaces.IProductPart;

public class Tank implements IProduct {

    private Engine engine;
    private Corps corps;
    private Tower tower;


    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Corps getCorps() {
        return corps;
    }

    public void setCorps(Corps corps) {
        this.corps = corps;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }



    public void installFirstPart(IProductPart corps){ // DISGUSTING

        this.corps = (Corps)corps;
    }

    public void installSecondPart(IProductPart engine){

        this.engine = (Engine)engine;
    }

    public void installThirdPart(IProductPart tower){

        this.tower = (Tower)tower;
    }

    @Override
    public String toString() {
        return "Tank{" + '\n'+
                "engine=" + engine +'\n'+
                "corps=" + corps +'\n'+
                "tower=" + tower +
                '}';
    }
}
