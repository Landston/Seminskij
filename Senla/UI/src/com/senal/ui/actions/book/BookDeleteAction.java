package com.senal.ui.actions.book;


import com.senal.ui.actions.BaseAction;
import com.senal.ui.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class BookDeleteAction extends BaseAction implements IAction {


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
                facade.deleteBook(UUID.fromString(uuid));

            } catch (IllegalArgumentException exception) {
                System.out.println("Wrong ID");
            }

        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
