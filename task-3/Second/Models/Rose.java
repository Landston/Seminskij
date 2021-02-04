package main.Task3.Second.Models;

import main.Task3.Second.Models.Flower;
import main.Task3.Second.main;

public class Rose extends Flower {


   private boolean hasNeedls;

   public Rose( double cost, String color, boolean needls){

       super(cost, color);

       this.hasNeedls = needls;

       this.setName("Hortence");
   }


    public boolean isHasNeedl() {
        return hasNeedls;
    }

    public void setHasNeedl(boolean hasNeedl) {
        this.hasNeedls = hasNeedl;
    }


}
