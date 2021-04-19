package com.senla.ui.actions.order;


import com.senla.model.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class OrderChangeStatusAction extends BaseAction implements IAction {
    public OrderChangeStatusAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try{
        System.out.println("Enter id of a Order that you want to change status");

        int count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllOrders())){
            System.out.println(count + " " + item);

            count++;
        }

        Scanner scanner = new Scanner(System.in);
        String uuid = scanner.nextLine();

        System.out.println("Enter one of three status"
         + "\n 1: OPEN + \n 2: CLOSED + \n + 3: CANCELED");

        String status = scanner.nextLine();

        this.facade.changeOrder(UUID.fromString(uuid), status);

        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
            System.out.println(e.getMessage());
        }

    }
}