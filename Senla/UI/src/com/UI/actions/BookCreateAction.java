package com.UI.actions;

import com.Models.BookShopFacade;
import java.util.Scanner;

public class BookCreateAction extends BaseAction implements IAction{



    @Override
    public void execute() {

        BookShopFacade facade = BookShopFacade.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название книги: ");
        String name = scanner.nextLine();
        System.out.println("Введите жанр книги: ");
        String gonre = scanner.nextLine();
        System.out.println("Введите год:");
        int year = scanner.nextInt();
        System.out.println("Введите цену:");
        double cost = scanner.nextDouble();
        facade.addBookToShop(name, gonre, year, cost);
        System.out.println("adding action done");


    }
}
