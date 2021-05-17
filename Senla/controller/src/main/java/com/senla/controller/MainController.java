package com.senla.controller;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IBookService;
import com.senla.controller.config.Config;
import com.senla.model.Book;
import com.senla.model.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class MainController {

    public static void main(String[] args) throws DAOException, ServiceException {


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        IBookService service = applicationContext.getBean("bookService", IBookService.class);

     /*   Book book = new Book();
        book.setCost(150);
        book.setStatus(BookStatus.RESERVED);
        book.setGenre("Detective");
        book.setName("James Bond 007");
        book.setYear(2000);

        service.add(book);
*/
        Book book = service.getBookById(UUID.fromString("9bd3ec21-4b2f-43ac-b2d3-93141e84695f"));


        service.delete(book);

        System.out.println();
    }
}
