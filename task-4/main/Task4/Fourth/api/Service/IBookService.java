package main.Task3.Fourth.api.Service;

import main.Task3.Fourth.Models.Book;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public interface IBookService {

    void addBookToShop(Book book);

    boolean writeOffBook(UUID id);

    boolean delete(Book book);

    List<Book> getSortedBooks(Comparator<Book> condition);

    List<Book> getSortedBooksThatAreNotSoldBySixMonths( Comparator<Book> condition);


}
