package main.Task3.Third.classes.LineBuilders;

import main.Task3.Third.classes.Interfaces.IAssemblyLine;
import main.Task3.Third.classes.Interfaces.IProduct;

public class TankLineBuilder implements IAssemblyLine {

    private EngineLineBuilder engine;
    private CorpsLineBuilder corps;
    private TowerLineBuilder tower;

    public TankLineBuilder(EngineLineBuilder engine, CorpsLineBuilder corps, TowerLineBuilder tower) {


        this.engine = engine;
        this.corps = corps;
        this.tower = tower;
    }



    @Override
    public IProduct assebmleProduct(IProduct product) {

        product.installFirstPart(corps.buildProductPart());
        product.installSecondPart(engine.buildProductPart());
        product.installThirdPart(tower.buildProductPart());

        return product;
    }
}
