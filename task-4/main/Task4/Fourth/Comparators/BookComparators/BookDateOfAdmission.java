package main.Task3.Fourth.Comparators.BookComparators;

import main.Task3.Fourth.Models.Book;

import java.time.LocalDate;
import java.util.Comparator;

public class BookDateOfAdmission implements Comparator<Book> {


    @Override
    public int compare(Book o1, Book o2) {

        LocalDate date1 = o1.getDateOfAdmission();
        LocalDate date2 = o2.getDateOfAdmission();

        if ((o1 == null && o2 == null) || date1 == null || date2 == null)
            return 0;

        return date2.compareTo(date1);

    }
}
