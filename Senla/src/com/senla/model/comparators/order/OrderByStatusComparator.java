package com.senla.model.comparators.order;

import com.senla.model.models.Order;

import java.util.Comparator;

public class OrderByStatusComparator implements Comparator<Order> {


    @Override
    public int compare(Order o1, Order o2) {

        if ((o1 == null && o2 == null) || o1.getStatus() == null || o2.getStatus() == null )
            return 0;

        int status1 = o1.getStatus().ordinal();
        int status2 = o2.getStatus().ordinal();

        if (status1 == status2) return 0;

        if (status1 < status2) return -1;

        if (status1 > status2) return 1;

        return 0;
    }
}
