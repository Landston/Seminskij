package com.senla.ui.actions;

import com.senla.facade.BookShopFacade;

import java.util.logging.Level;

public class exitAction extends BaseAction implements IAction{
    public exitAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {


        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exit action failed");

        }
    }

}
