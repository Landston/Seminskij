package com.UI.actions.OrderActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CreateOrderAction  extends BaseAction implements IAction {
    @Override
    public void execute() {

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
    }
}
