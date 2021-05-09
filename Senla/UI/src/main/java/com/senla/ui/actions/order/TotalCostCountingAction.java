package com.senla.ui.actions.order;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

import java.util.logging.Level;

public class TotalCostCountingAction extends BaseAction implements IAction {

    public TotalCostCountingAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {

        try{
            System.out.println("Total revenue: " + facade.totalFirmRevenue());
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
        }
    }

