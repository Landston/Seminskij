package com.senla.ui.actions.order;

import com.senla.facade.BookShopFacade;
import com.senla.ui.actions.BaseAction;
import com.senla.ui.actions.IAction;
import org.apache.logging.log4j.Level;

import java.time.LocalDate;
import java.util.Scanner;

public class ListClosedOrdersByTimeAction extends BaseAction implements IAction {
    public ListClosedOrdersByTimeAction(BookShopFacade facade) {
        super(facade);
    }

    @Override
    public void execute() {
        try{
        System.out.println("Enter one statement:  " +
                "\n - Sorted  by date  in period" +
                "\n - Sorted by cost  in period" +
                "\n - Amount of closed Orders in period");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        switch (answer) {
            case "Sorted by date books in period": {
                System.out.println("Enter date: From");

                LocalDate from = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter date: to");

                LocalDate to = LocalDate.parse(scanner.nextLine());

                System.out.println(facade.getClosedOrdersByTime(from, to, "ByDateOfExecution"));

                break;
            }
            case "Sorted by cost books in period" : {
                System.out.println("Enter date: From");

                LocalDate from = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter date: to");

                LocalDate to = LocalDate.parse(scanner.nextLine());

                System.out.println(facade.getClosedOrdersByTime(from, to, "ByCost"));

                break;
            }
            case "Amount of closed Orders in period" : {
                System.out.println("Enter date: From");

                LocalDate from = LocalDate.parse(scanner.nextLine());

                System.out.println("Enter date: to");

                LocalDate to = LocalDate.parse(scanner.nextLine());

                System.out.println(facade.amountOfClosedOrdersByTime(from, to));

                break;
            }
        }
        } catch (Exception e){
            LOGGER.log(Level.WARN, e.getLocalizedMessage(), e);
        }
    }
}
