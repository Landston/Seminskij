package com.UI.actions.OrderActions;

import com.UI.actions.BaseAction;
import com.UI.actions.IAction;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;

public class ListClosedOrdersByTimeAction extends BaseAction implements IAction {
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
            LOGGER.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
