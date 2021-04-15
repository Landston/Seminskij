package com.senal.ui.actions.order;

import com.senal.ui.actions.BaseAction;
import com.senal.ui.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class CreateOrderAction  extends BaseAction implements IAction {
    @Override
    public void execute() {
    try{
        System.out.println("Enter book id ");
        Scanner scanner = new Scanner(System.in);

        int count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllBooks())){
            System.out.println(count + " " + item);

            count++;
        }

        UUID bookID = UUID.fromString(scanner.nextLine());
        Object  book = facade.getBookByID(bookID);

        System.out.println("Enter client id ");

        count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllClients())){
            System.out.println(count + " " + item);

            count++;
        }

        UUID clientID = UUID.fromString(scanner.nextLine());

        this.facade.addOrder(bookID, clientID);

    } catch (Exception e){
        LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
    }
    }
}
