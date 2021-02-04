package main.Task3.Third.classes.LineBuilders;

import main.Task3.Third.classes.Interfaces.ILineStep;
import main.Task3.Third.classes.Interfaces.IProductPart;
import main.Task3.Third.classes.Models.Corps;

public class CorpsLineBuilder implements ILineStep {




    @Override
    public IProductPart buildProductPart() {

        return new Corps(140,100,80)    ;

    }


}
