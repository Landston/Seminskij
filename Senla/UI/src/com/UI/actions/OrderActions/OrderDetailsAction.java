package com.UI.actions.OrderActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class OrderDetailsAction extends BaseAction implements IAction {

    @Override
    public void execute() {
        try{
        System.out.println("Enter id of a Order that you want to see");

        int count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllOrders())){
            System.out.println(count + " " + item);

            count++;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println( facade.getOrderByID(UUID.fromString( scanner.nextLine())));
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
