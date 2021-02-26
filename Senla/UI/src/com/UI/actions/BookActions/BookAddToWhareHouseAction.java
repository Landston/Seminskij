package com.UI.actions.BookActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookAddToWhareHouseAction extends BaseAction implements IAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter id of a Book that you want to add to WareHouse");

        int count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllBooks())){
            System.out.println(count + " " + item);

            count++;
        }

        facade.addBookToWareHouse(UUID.fromString(scanner.nextLine()));



    }
}
