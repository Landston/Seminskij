package com.senla.model.comparators.book;

import com.senla.model.models.Book;

import java.util.Comparator;

public class BookNameAlphabeticallyComparator implements Comparator<Book> {


    @Override
    public int compare(Book o1, Book o2) {

        if ((o1 == null && o2 == null) || o1.getName() == null || o2.getName() == null || o1.getName().isBlank() || o2.getName().isBlank())
            return 0;

        String name1 = o1.getName();
        String name2 = o2.getName();
        return name1.compareTo(name2);
    }
}
