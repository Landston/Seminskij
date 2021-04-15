package com.senal.ui.actions.order;

import com.senal.ui.actions.BaseAction;
import com.senal.ui.actions.IAction;

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

