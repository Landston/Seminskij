package com.senla.model.dao;

import com.senla.di.annotations.Singleton;
import com.senla.model.models.Book;
import com.senla.model.api.dao.IBookDAO;
import com.senla.model.serializable.Serializer;


import java.util.*;
import java.util.logging.Logger;

@Singleton
public class BookDAO extends AbstractDAO<Book> implements IBookDAO {


    private static BookDAO instatnce;
    private static final Logger LOGGER = Logger.getLogger(BookDAO.class.getName());


    public BookDAO() {
        repository = new ArrayList<>();
        repository = Serializer.deserialize(Book.class);
    }

    public static BookDAO getInstance(){

        instatnce = Objects.requireNonNullElse(instatnce, new BookDAO());
        return instatnce;

    }


}
