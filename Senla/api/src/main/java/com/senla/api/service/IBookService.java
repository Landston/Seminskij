package com.senla.api.service;


import com.senla.api.exception.service.ServiceException;
import com.senla.model.Book;
import com.senla.model.dto.BookDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IBookService {


    void addBookToShop(String name, String genre, int year, double cost) throws ServiceException;

    boolean writeOffBook(UUID id) throws ServiceException;

    List<BookDTO> getSortedBooks(String condition) throws ServiceException;

    List<BookDTO> getSortedStaledBooks(String condition) throws ServiceException;

    Set<String> getSortParams();

    void update(BookDTO bookDTO) throws ServiceException;

    void delete(UUID uuid) throws ServiceException;

    List<BookDTO> getAll() throws ServiceException;

    BookDTO getBookById(UUID uuid) throws ServiceException;

    BookDTO add(BookDTO bookDTO) throws ServiceException;

    void addBookToWareHouse(UUID uuid) throws ServiceException;
}
