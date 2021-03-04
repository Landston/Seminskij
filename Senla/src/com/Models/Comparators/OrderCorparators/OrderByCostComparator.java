package com.Models.Comparators.OrderCorparators;

import com.Models.Models.Order;

import java.util.Comparator;

public class OrderByCostComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {

        Double price1 = o1.getTotalPrice();
        Double price2 = o2.getTotalPrice();
        return  price2.compareTo(price1);
    }
}
