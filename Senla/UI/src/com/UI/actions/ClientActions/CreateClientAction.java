package com.UI.actions.ClientActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.Scanner;
import java.util.logging.Level;

public class CreateClientAction extends BaseAction implements IAction {
    @Override
    public void execute() {
        try{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя клиента");

        String name = scanner.nextLine();

        System.out.println("Введите mail клиента");

        String mail = scanner.nextLine();

        this.facade.addClient(name,mail);
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
