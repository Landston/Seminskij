package com.Models.Comparators.BookComparators;

import com.Models.Models.Book;

import java.util.Comparator;

public class BookReservedComparator implements Comparator<Book> {


    @Override
    public int compare(Book o1, Book o2) {

        if ((o1 == null && o2 == null) || o1.getStatus() == null || o2.getStatus()== null)
            return 0;

        int status1 = o1.getStatus().ordinal();
        int status2 = o2.getStatus().ordinal();

        if (status1 == status2) return 0;

        if (status1 < status2) return -1;

        if (status1 > status2) return 1;

        return 0;
    }
}
