package com.senla.ui.actions.book;

import com.senla.model.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class BookWritingOffAction extends BaseAction implements IAction {


    public BookWritingOffAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            int count = 0;

            for (Object item : new ArrayList<>(this.facade.getAllBooks())) {
                System.out.println(count + " " + item);

                count++;
            }
            System.out.println("Enter id of a Book that you want to write off");

            Scanner scanner = new Scanner(System.in);
            String uuid = scanner.nextLine();

            facade.writeOffBookFromWareHouse(UUID.fromString(uuid));

        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
