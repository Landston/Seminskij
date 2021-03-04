package com.UI.actions.OrderActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

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

