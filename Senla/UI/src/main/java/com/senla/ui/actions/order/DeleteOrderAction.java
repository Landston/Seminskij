package com.senla.ui.actions.order;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class DeleteOrderAction extends BaseAction implements IAction {
    public DeleteOrderAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter id of a Order that you want to delete");

            int count = 0;

            for (Object item : new ArrayList<>(this.facade.getAllOrders())) {
                System.out.println(count + " " + item);

                count++;
            }

            Scanner scanner = new Scanner(System.in);
            String uuid = scanner.nextLine();

            facade.deleteOrderById(facade.getOrderByID(UUID.fromString(uuid)));
        } catch (Exception e){
            LOGGER.log(Level.WARN, e.getLocalizedMessage(), e);
        }
    }
}