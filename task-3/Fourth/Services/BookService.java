package main.Task3.Fourth.Services;

import main.Task3.Fourth.DAO.BookDAO;
import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.BookStatus;
import main.Task3.Fourth.api.DAO.IBookDAO;
import main.Task3.Fourth.api.Service.IBookService;

import java.util.UUID;

public class BookService  implements IBookService {

    private IBookDAO bDao;

    public BookService(BookDAO dao){

        this.bDao = dao;

    }

    @Override
    public boolean writeOffBook(UUID id) {

        Book book = this.bDao.getEntity(id);

        if(book != null ) return true;

        return false;
            }


    @Override
    public void addBookToShop(Book book) {

        if(book != null){

            if(book.getName()!= null){

                this.bDao.addEntity(book);

            }
        }




    }






}
