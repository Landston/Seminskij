package com.UI.actions.OrderActions;


import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class OrderChangeStatusAction extends BaseAction implements IAction {
    @Override
    public void execute() {
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

    }
}