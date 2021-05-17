package com.senla.dao;

import com.senla.api.exception.service.DAOException;
import com.senla.dao.BookDAO;
import com.senla.dao.Constants;
import com.senla.dao.configuration.JpaConfiguration;
import com.senla.model.Book;
import com.senla.model.BookStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.UUID;

public class TestClass {

    private static final String GET_ALL_QUERY = "SELECT * FROM " + Constants.BOOKS_TABLE + " ;";


    public static void main(String[] args) throws DAOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.senla.dao");

        BookDAO dao = (BookDAO) applicationContext.getBean("bookDAO");

        Book book = new Book();
        book.setCost(150);
        book.setStatus(BookStatus.RESERVED);
        book.setGenre("Detective");
        book.setName("James Bond 007");
        book.setYear(2000);

        dao.addEntity(book);

        System.out.println(dao.getAllBook());
        System.out.println();
    }
}
