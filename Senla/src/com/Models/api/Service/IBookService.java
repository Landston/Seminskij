package com.Models.api.Service;

import com.Models.Models.Book;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public interface IBookService {


    void addBookToShop(String name, String genre, int year, double cost);

    boolean writeOffBook(UUID id);

    List<Book> getSortedBooks(String condition);

    List<Book> getSortedBooksThatAreNotSoldBySixMonths( String condition);


}
