package com.senla.ui.actions.book;

import com.senla.model.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class BookAddToWhareHouseAction extends BaseAction implements IAction {

    public BookAddToWhareHouseAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {

        try {


            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter id of a Book that you want to add to WareHouse");

            int count = 0;

            for (Object item : new ArrayList<>(this.facade.getAllBooks())) {
                System.out.println(count + " " + item);

                count++;
            }
            try {
                facade.addBookToWareHouse(UUID.fromString(scanner.nextLine()));

            } catch (IllegalArgumentException exception) {
                System.out.println("Wrong ID");

            }

        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
