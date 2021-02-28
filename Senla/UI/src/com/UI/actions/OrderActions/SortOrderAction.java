package com.UI.actions.OrderActions;

import com.Models.BookShopFacade;
import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.Scanner;
import java.util.logging.Level;

public class SortOrderAction extends BaseAction implements IAction {
    @Override
    public void execute() {
        try {
            System.out.println(" 1: Весь список заказов" +
                    "\n 2: Cписок заказов по цене" +
                    "\n 3: Cписок заказов по дате завершения" +
                    "\n 4: Cписок заказов по статусу" +
                    "\n 5: Cписок выполненых за период заказов");

            Scanner scanner = new Scanner(System.in);

            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println(facade.getAllOrders());
                    break;
                }
                case 2: {
                    System.out.println(facade.getSortedOrder("ByCost"));
                    break;
                }
                case 3: {
                    System.out.println(facade.getSortedOrder("ByDateOfExecution"));
                    break;
                }
                case 4: {
                    System.out.println(facade.getSortedOrder("ByStatus"));
                    break;
                }
                case 5: {
                    ListClosedOrdersByTimeAction action = new ListClosedOrdersByTimeAction();
                    action.execute();
                    break;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }

    }
}
