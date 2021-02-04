package main.Task3.Fourth.DAO;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.api.DAO.IBookDAO;

import java.util.List;
import java.util.UUID;

public class BookDAO implements IBookDAO {

    private List<Book> books;

    public BookDAO(List<Book> books) {

        this.books = books;

    }

    public boolean update(UUID id, Book item) {

        for(Book bk : this.books){

            if(id.equals(bk.getUuid())) {
                bk = item;
            }

        }
        return false;

    }

    public List<Book> getAll() {
        return this.books;
    }

    public boolean delete(UUID uuid) {

        for(Book bk : this.books){

            if(uuid.equals(bk.getUuid())) {
                this.books.remove(bk);
                return true;
            }

        }
        return false;
    }

    public boolean addEntity(Book book) {

        if (book != null) {

            this.books.add(book);
            System.out.println("Book has added " + book.toString());
            return true;
        } else return false;

    }

    public Book getEntity(UUID id) {


        for (Book bk : this.books) {
            if (id.equals(bk.getUuid())) return bk;

        }
        return null;

    }


}
