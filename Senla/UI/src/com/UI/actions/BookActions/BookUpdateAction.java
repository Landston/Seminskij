package com.UI.actions.BookActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

public class BookUpdateAction extends BaseAction implements IAction {
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

        System.out.println("Введите название книги: ");

        String name = scanner.nextLine();

        System.out.println("Введите жанр книги: ");

        String genre = scanner.nextLine();

        System.out.println("Введите год:");
        try {
            int year = scanner.nextInt();

            System.out.println("Введите цену:");

            double cost = scanner.nextDouble();

            facade.updateBook(UUID.fromString(uuid), name, genre, year, cost);

        } catch (InputMismatchException e){
            System.out.println("Yead and Cost must be numbers!");
        }

        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
