package com.UI.actions.BookActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class BookUpdateAction extends BaseAction implements IAction {
    @Override
    public void execute() {
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

        int year = scanner.nextInt();

        System.out.println("Введите цену:");

        double cost = scanner.nextDouble();

        facade.updateBook(UUID.fromString(uuid), name, genre, year, cost);

    }
}
