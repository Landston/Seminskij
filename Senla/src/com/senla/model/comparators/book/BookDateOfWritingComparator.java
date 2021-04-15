package com.senla.model.comparators.book;

import com.senla.model.models.Book;

import java.util.Comparator;

public class BookDateOfWritingComparator  implements Comparator<Book> {


    @Override
    public int compare(Book o1, Book o2) {

        Integer year1 = o1.getYear();
        Integer year2 = o2.getYear();
        return  year2.compareTo(year1);

    }
}


