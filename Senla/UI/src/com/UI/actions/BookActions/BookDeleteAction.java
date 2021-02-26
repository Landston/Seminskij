package com.UI.actions.BookActions;


import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookDeleteAction extends BaseAction implements IAction {


    @Override
    public void execute() {
        System.out.println("Enter id of a Book that you want to delete");

        int count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllBooks())){
            System.out.println(count + " " + item);

            count++;
        }

        Scanner scanner = new Scanner(System.in);
        String uuid = scanner.nextLine();

        facade.deleteBook(UUID.fromString(uuid)); // IllegalArgumentException

    }
}
