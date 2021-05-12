package com.senla.ui.actions;

import com.senla.facade.BookShopFacade;
import org.apache.logging.log4j.Level;

public class exitAction extends BaseAction implements IAction{
    public exitAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {


        }catch (Exception e){
            LOGGER.log(Level.WARN, "Exit action failed");

        }
    }

}
