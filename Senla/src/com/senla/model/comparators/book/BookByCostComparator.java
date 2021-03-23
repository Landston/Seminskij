package com.senla.model.comparators.book;

import com.senla.model.models.Book;

import java.util.Comparator;

public class BookByCostComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {

        Double price1 = o1.getCost();
        Double price2 = o2.getCost();

        if ((o1 == null && o2 == null) || price1 == null || price2 == null)
            return 0;


        return  price1.compareTo(price2);
    }
}
