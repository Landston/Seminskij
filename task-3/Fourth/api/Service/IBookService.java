package main.Task3.Fourth.api.Service;

import main.Task3.Fourth.Models.Book;

import java.util.UUID;

public interface IBookService {

    void addBookToShop(Book book);

    boolean writeOffBook(UUID id);




}
