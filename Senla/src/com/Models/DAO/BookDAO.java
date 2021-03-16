package com.Models.DAO;

import com.Models.Models.Book;
import com.Models.Serializable.Serializer;
import com.Models.api.DAO.IBookDAO;
import com.Models.exceptions.DAOException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookDAO extends AbstractDAO<Book> implements IBookDAO {

    private static BookDAO instatnce;
    private static final Logger LOGGER = Logger.getLogger(BookDAO.class.getName());

    private BookDAO() {
        repository = new ArrayList<>();
        repository = Serializer.deserialize(Book.class);
    }

    public static BookDAO getInstance(){

        instatnce = Objects.requireNonNullElse(instatnce, new BookDAO());
        return instatnce;

    }


}
