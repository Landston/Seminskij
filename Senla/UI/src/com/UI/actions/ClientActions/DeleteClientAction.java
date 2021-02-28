package com.UI.actions.ClientActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class DeleteClientAction extends BaseAction implements IAction {
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
