package com.UI.actions.OrderActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class OrderUpdateAction extends BaseAction implements IAction {
    @Override
    public void execute() {

        System.out.println("Choose action:  " +
                "\n - add Book" +
                "\n - remove Book");
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        String answer = scanner.nextLine();

        switch (answer){
            case "Add Book" : {
                System.out.println("Enter id of a Order that you want to change");

                for (Object item : new ArrayList<>(this.facade.getAllOrders())){
                    System.out.println(count + " " + item);

                    count++;
                }

                String orderID = scanner.nextLine();

                bookToChoose();
                facade.addBooktoOrder(UUID.fromString(orderID), facade.getBookByID(UUID.fromString(scanner.nextLine())));

                break;
            }
            case "Remove Book" : {
                System.out.println("Enter id of a Order that you want to change");

                for (Object item : new ArrayList<>(this.facade.getAllOrders())){
                    System.out.println(count + " " + item);

                    count++;
                }

                String orderID = scanner.nextLine();

                bookToChoose();
                facade.deleteBookFromOrder(UUID.fromString(orderID), facade.getBookByID(UUID.fromString(scanner.nextLine())));

                break;
            }
        }


    }

    private void bookToChoose() {
        int count;
        count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllBooks())){
            System.out.println(count + " " + item);

            count++;
        }
    }
}