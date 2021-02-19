package com.Models.Comparators.OrderCorparators;

import com.Models.Models.Order;

import java.time.LocalDate;
import java.util.Comparator;

public class OrderByDateOfExecutionComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {


        if ((o1 == null && o2 == null) || o1.getDateOfExecution() == null || o2.getDateOfExecution() == null )
            return 0;

        LocalDate date1 = o1.getDateOfExecution();
        LocalDate date2 = o2.getDateOfExecution();

        return date2.compareTo(date1);
    }
}
