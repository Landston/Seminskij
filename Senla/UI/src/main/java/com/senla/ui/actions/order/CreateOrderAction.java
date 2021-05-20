package com.senla.ui.actions.order;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CreateOrderAction  extends BaseAction implements IAction {
    public CreateOrderAction(BookShopFacade facade) {
        super(facade);
    }

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

        this.facade.createOrder(bookID, clientID);

    } catch (Exception e){
        LOGGER.log(Level.WARN, e.getLocalizedMessage(), e);
    }
    }
}
