package com.senla.util;


import com.senla.model.Book;

import java.util.List;

public class OrderUtil {

    public static double countBooksTotalCost(List<Book> books) {
        if (books == null || books.isEmpty()) {
            return 0;
        }

        double cost = 0;

        for (Book bk : books) {
            cost += bk.getCost();
        }

        return cost;
    }
}
