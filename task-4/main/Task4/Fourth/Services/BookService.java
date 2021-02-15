package main.Task3.Fourth.Services;

import main.Task3.Fourth.DAO.BookDAO;
import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.BookStatus;
import main.Task3.Fourth.api.DAO.IBookDAO;
import main.Task3.Fourth.api.Service.IBookService;

import java.time.LocalDate;
import java.util.*;

public class BookService  implements IBookService {

    private  IBookDAO bookDAO;

    public BookService(BookDAO dao){

        this.bookDAO = dao;

    }

    public List<Book> getAll(){

        return this.bookDAO.getAll();
    }
    @Override
    public boolean writeOffBook(UUID id) {

        Book book = this.bookDAO.getEntity(id);

        if(book != null ) {

            book.setStatus(BookStatus.ABSENT);

            return true;
        }
        return false;
            }

    public boolean delete(Book book){

        this.bookDAO.delete(book.getUuid());
        return true;
    }

    @Override
    public void addBookToShop(Book book) {

        if(book != null){

            if(book.getName()!= null){
                this.bookDAO.addEntity(book);
            }
        }
    }

    public List<Book> getSortedBooks(Comparator<Book> condition){

        List<Book> list = new ArrayList<Book>();
        list.addAll(this.bookDAO.getAll());
        list.sort(condition);
        return list;
        }

    @Override
    public List<Book> getSortedBooksThatAreNotSoldBySixMonths(Comparator<Book> condition) {

        List<Book> sorted = new ArrayList<>();

        LocalDate date = LocalDate.now();

        for(Book bk : this.bookDAO.getAll()){
            LocalDate bookDate = bk.getDateOfAdmission();

            System.out.println(bookDate);

            if (bookDate.isBefore(date.minusMonths(6))) {
                sorted.add(bk);
            }
        }
        sorted.sort(condition);
        if(sorted.isEmpty() || sorted == null) return Collections.emptyList();

        return sorted;


    }


}
