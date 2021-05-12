package com.senla.ui.actions.order;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import org.apache.logging.log4j.Level;

import java.util.Scanner;

public class SortOrderAction extends BaseAction implements IAction {
    public SortOrderAction(BookShopFacade facade) {
        super(facade);
    }

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
                    ListClosedOrdersByTimeAction action = new ListClosedOrdersByTimeAction(facade);
                    action.execute();
                    break;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARN, e.getLocalizedMessage(), e);
        }

    }
}
