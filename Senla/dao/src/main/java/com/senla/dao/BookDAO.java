package com.senla.dao;

import com.senla.api.dao.IBookDAO;
import com.senla.dao.util.DataBaseHandler;
import com.senla.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO extends AbstractDAO<Book> implements IBookDAO {

    public BookDAO() { }

    @Override
    protected Class<Book> getClazz() {
        return Book.class;
    }


}


