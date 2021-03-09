package com.UI.actions.BookActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.util.Scanner;
import java.util.logging.Level;

public class BookSortingAction extends BaseAction implements IAction {

    @Override
    public void execute() {
        try {
            System.out.println("""
                    1: Возвращение в стандартное меню\s
                    2: Весь список книг
                    3: Список книг отсортированный по Алфавиту
                    4: Список книг отсортированный по Цене
                    5: Список книг отсортированный по Дате поступления
                    6: Список книг отсортированный по Дате написания
                    7: Список книг отсортированный по Доступности
                    8: Список книг залежавшихся книг по Дате поступления
                    9: Список книг залежавшихся книг по Цене""".indent(1));

            Scanner scanner = new Scanner(System.in);

            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Возвращение в стандартное меню");
                    break;
                }
                case 2: {
                    System.out.println(facade.getAllBooks());
                    break;
                }
                case 3: {
                    System.out.println(facade.getSortedBooks("Alphabet"));
                    break;
                }
                case 4: {
                    System.out.println(facade.getSortedBooks("ByCost"));
                    break;
                }
                case 5: {
                    System.out.println(facade.getSortedBooks("ByDateOfAdmission"));
                    break;
                }
                case 6: {
                    System.out.println(facade.getSortedBooks("ByDateOfWriting"));
                    break;
                }
                case 7: {
                    System.out.println(facade.getSortedBooks("Reserved"));
                    break;
                }
                case 8:{
                    System.out.println(facade.getStaledBooks("ByDateOfAdmission"));
                    break;
                }
                case 9: {
                    System.out.println(facade.getStaledBooks("ByCost"));
                    break;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }

    }
}
