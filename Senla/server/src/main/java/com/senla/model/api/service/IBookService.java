package com.senla.model.api.service;

import com.senla.di.annotation.Singleton;
import com.senla.model.exception.DAOException;
import com.senla.model.model.Book;
import com.senla.model.exception.ServiceException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IBookService {


    void addBookToShop(String name, String genre, int year, double cost) throws ServiceException;

    boolean writeOffBook(UUID id) throws ServiceException;

    List<Book> getSortedBooks(String condition) throws ServiceException;

    List<Book> getSortedStaledBooks(String condition) throws ServiceException;

    Set<String> getSortParams();

    void updateBook(UUID id, Book book) throws ServiceException;

    void deleteBook(UUID uuid) throws ServiceException;

    List<Book> getAll() throws ServiceException;

    Book getBookById(UUID uuid) throws ServiceException;

    void add(Book book) throws ServiceException;

    void addBookToWareHouse(UUID uuid) throws ServiceException;
}
