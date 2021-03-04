package com.UI.actions.BookActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;
import com.UI.actions.OrderActions.ListClosedOrdersByTimeAction;

import java.util.Scanner;
import java.util.logging.Level;

public class BookSortingAction extends BaseAction implements IAction {

    @Override
    public void execute() {
        try {
            System.out.println(" 1: Весь список книг" +
                    "\n 2: Список книг отсортированный по Алфавиту" +
                    "\n 3: Список книг отсортированный по Цене" +
                    "\n 4: Список книг отсортированный по дате поуступления" +
                    "\n 5: Список книг отсортированный по дате написания" +
                    "\n 6: Список книг отсортированный по доступности" +
                    "\n 7: Список книг залежавшихся книг по дате поступления" +
                    "\n 8: Список книг залежавшихся книг по цене");

            Scanner scanner = new Scanner(System.in);

            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println(facade.getSortedBooks("Alphabet"));
                    break;
                }
                case 2: {
                    System.out.println(facade.getSortedBooks("ByCost"));
                    break;
                }
                case 3: {
                    System.out.println(facade.getSortedBooks("ByDateOfAdmission"));
                    break;
                }
                case 4: {
                    System.out.println(facade.getSortedBooks("ByDateOfWriting"));
                    break;
                }
                case 5: {
                    System.out.println(facade.getSortedBooks("Reserved"));
                    break;
                }
                case 6:{
                    System.out.println(facade.getBookThatAreNotSoldBySixMonths("ByDateOfAdmission"));
                    break;
                }
                case 7:{
                    System.out.println(facade.getBookThatAreNotSoldBySixMonths("ByDateOfAdmission"));
                    break;
                }
                case 8: {
                    System.out.println(facade.getBookThatAreNotSoldBySixMonths("ByCost"));
                    break;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }

    }
}
