package com.UI.actions.OrderActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

public class TotalCostCountingAction extends BaseAction implements IAction {

    @Override
    public void execute() {
        System.out.println("Total revenue: " + facade.totalFirmRevenue());
    }
}
