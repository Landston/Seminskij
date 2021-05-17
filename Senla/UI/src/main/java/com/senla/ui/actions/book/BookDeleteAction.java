package com.senla.ui.actions.book;


import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookDeleteAction extends BaseAction implements IAction {


    public BookDeleteAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter id of a Book that you want to delete");

            int count = 0;

            for (Object item : new ArrayList<>(this.facade.getAllBooks())) {
                System.out.println(count + " " + item);

                count++;
            }

            Scanner scanner = new Scanner(System.in);
            String uuid = scanner.nextLine();

            try {
                facade.deleteBook(facade.getBookByID(UUID.fromString(uuid)));

            } catch (IllegalArgumentException exception) {
                System.out.println("Wrong ID");
            }

        } catch (Exception e){
            LOGGER.log(Level.WARN, e.getLocalizedMessage(), e);
        }
    }
}
