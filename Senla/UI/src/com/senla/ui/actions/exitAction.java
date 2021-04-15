package com.senla.ui.actions;

import java.util.logging.Level;

public class exitAction extends BaseAction implements IAction{
    @Override
    public void execute() {
        try {


        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Exit action failed");

        }
    }

}
