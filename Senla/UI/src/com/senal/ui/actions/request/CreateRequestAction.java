package com.senal.ui.actions.request;

import com.senal.ui.actions.BaseAction;
import com.senal.ui.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class CreateRequestAction extends BaseAction implements IAction {


    @Override
    public void execute() {
        try{
        int count = 0;

        for (Object item : new ArrayList<>(this.facade.getAllBooks())){

            System.out.println(count + " " + item);

            count++;
        }
        System.out.println("Enter id of a Book that you want to update");

        Scanner scanner = new Scanner(System.in);
        String uuid = scanner.nextLine();
        try {
            this.facade.createRequest(UUID.fromString(uuid));

        } catch (IllegalArgumentException e){
            System.out.println("Wrong id entered");
        }

        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }

    }
}
