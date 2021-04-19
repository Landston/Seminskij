package com.senla.ui.actions.client;

import com.senla.model.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

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

        facade.deleteClient(UUID.fromString(id));
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
