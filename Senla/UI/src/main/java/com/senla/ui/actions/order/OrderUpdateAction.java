package com.senla.ui.actions.order;

import com.senla.model.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class OrderUpdateAction extends BaseAction implements IAction {
    public OrderUpdateAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try{


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
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }

    }

    private void bookToChoose() {
        int count;
        count = 0;
        try {

            for (Object item : new ArrayList<>(this.facade.getAllBooks())) {
                System.out.println(count + " " + item);

                count++;
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }
}