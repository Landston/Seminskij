package com.Models.api.Service;

import com.Models.Models.Book;
import com.Models.exceptions.ServiceException;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public interface IBookService {


    void addBookToShop(String name, String genre, int year, double cost) throws ServiceException;

    boolean writeOffBook(UUID id) throws ServiceException;

    List<Book> getSortedBooks(String condition) throws ServiceException;

    List<Book> getSortedStaledBooks(String condition) throws ServiceException;


}
