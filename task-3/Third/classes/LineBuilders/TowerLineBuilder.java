package main.Task3.Third.classes.LineBuilders;

import main.Task3.Third.classes.Interfaces.ILineStep;
import main.Task3.Third.classes.Interfaces.IProductPart;
import main.Task3.Third.classes.Models.Tower;

public class TowerLineBuilder implements ILineStep {



    @Override
    public IProductPart buildProductPart() {
        return new Tower(160, "STAFVAR");
    }
}
