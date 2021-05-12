package com.senla.ui.actions.book;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import org.apache.logging.log4j.Level;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookCreateAction extends BaseAction implements IAction {

    public BookCreateAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите название книги: ");

            String name = scanner.nextLine();

            System.out.println("Введите жанр книги: ");

            String gonre = scanner.nextLine();

            System.out.println("Введите год:");

            try {
                int year = scanner.nextInt();
                
                System.out.println("Введите цену:");


                double cost = scanner.nextDouble();

                facade.addBookToShop(name, gonre, year, cost);

            } catch (InputMismatchException e) {
                System.out.println("Yead and Cost must be numbers!");
            }
        } catch (Exception e){
            LOGGER.log(Level.WARN, e.getLocalizedMessage(), e);

        }
    }
}
