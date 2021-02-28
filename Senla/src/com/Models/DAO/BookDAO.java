package com.Models.DAO;

import com.Models.Models.Book;
import com.Models.api.DAO.IBookDAO;

import java.util.*;
import java.util.logging.Logger;

public class BookDAO implements IBookDAO {

    private static BookDAO instatnce;
    private List<Book> books;
    private static final Logger LOGGER = Logger.getLogger(BookDAO.class.getName());

    private BookDAO() {

        this.books = new ArrayList<>();
    }

    public static BookDAO getInstance(){

        instatnce = Objects.requireNonNullElse(instatnce, new BookDAO());
        return instatnce;

    }

    public void update(UUID id, Book item) {  // NoSuchElement Exception

        Optional<Book> book = this.books.stream()
                .filter(x -> x.getUuid().equals(id)).findFirst();
        Book it = book.get();

        this.books.remove(it);
        this.books.add(item);


    }

    public List<Book> getAll() {
        return new ArrayList<>(this.books);
    }

    public void delete(UUID uuid) {
        this.books.remove(this.getEntity(uuid));
    }

    public boolean addEntity(Book entity) {
        if (entity != null) {
            this.books.add(entity);

            return true;
        } else return false;

    }

    public Book getEntity(UUID id) {
        Optional<Book> book = this.books.stream()
                .filter(x -> x.getUuid().equals(id)).findFirst();

        return book.orElseGet(Book::new);
    }

}
