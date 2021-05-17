package com.senla.ui.actions.client;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import org.apache.logging.log4j.Level;

import java.util.Scanner;
import java.util.UUID;

public class DeleteClientAction extends BaseAction implements IAction {
    public DeleteClientAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try{
        System.out.println("Enter client that you want to change");

        int count = 0;

        for (Object ob : this.facade.getAllClients()) {
            System.out.println(count + " " + ob);

            count++;
        }

        System.out.println("Введите client ID");

        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();

        facade.deleteClient(facade.getClientByID(UUID.fromString(id)));
        } catch (Exception e){
            LOGGER.log(Level.WARN, e.getLocalizedMessage(), e);
        }
    }
}
