package com.UI.actions.BookActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookWritingOffAction extends BaseAction implements IAction {


    @Override
    public void execute() {
        int count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllBooks())){
            System.out.println(count + " " + item);

            count++;
        }
        System.out.println("Enter id of a Book that you want to write off");

        Scanner scanner = new Scanner(System.in);
        String uuid = scanner.nextLine();

        facade.writeOffBookFromWareHouse(UUID.fromString(uuid));

    }
}
