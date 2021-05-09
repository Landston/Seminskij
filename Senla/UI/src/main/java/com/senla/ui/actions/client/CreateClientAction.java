package com.senla.ui.actions.client;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;

import java.util.Scanner;
import java.util.logging.Level;

public class CreateClientAction extends BaseAction implements IAction {
    public CreateClientAction(BookShopFacade facade) {
        super(facade);
    }

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
