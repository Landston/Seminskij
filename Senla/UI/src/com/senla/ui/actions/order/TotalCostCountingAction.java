package com.senla.ui.actions.order;

import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

import java.util.logging.Level;

public class TotalCostCountingAction extends BaseAction implements IAction {

    @Override
    public void execute() {

        try{
            System.out.println("Total revenue: " + facade.totalFirmRevenue());
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
        }
    }

