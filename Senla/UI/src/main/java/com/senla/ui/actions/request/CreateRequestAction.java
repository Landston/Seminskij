package com.senla.ui.actions.request;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class CreateRequestAction extends BaseAction implements IAction {


    public CreateRequestAction(BookShopFacade facade) {
        super(facade);
    }

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
            LOGGER.log(Level.WARN, e.getLocalizedMessage(), e);
        }

    }
}
