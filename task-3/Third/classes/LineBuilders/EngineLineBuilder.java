package main.Task3.Third.classes.LineBuilders;

import main.Task3.Third.classes.Interfaces.ILineStep;
import main.Task3.Third.classes.Interfaces.IProductPart;
import main.Task3.Third.classes.Models.Engine;

public class EngineLineBuilder implements ILineStep {


    @Override
    public IProductPart buildProductPart() {

        return new Engine("THE BEST", 400, 8    );

    }
}
