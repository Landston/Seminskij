package com.senla.controller;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IBookService;
import com.senla.controller.config.Config;
import com.senla.dao.BookDAO;
import com.senla.model.Book;
import com.senla.model.BookStatus;
import com.senla.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainController {

    public static void main(String[] args) throws DAOException, ServiceException {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        IBookService service = applicationContext.getBean("bookService", IBookService.class);

        Book book = new Book();
        book.setCost(150);
        book.setStatus(BookStatus.RESERVED);
        book.setGenre("Detective");
        book.setName("James Bond 007");
        book.setYear(2000);

        service.add(book);

        System.out.println();
        System.out.println();
    }
}
