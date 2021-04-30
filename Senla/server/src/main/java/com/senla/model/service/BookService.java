package com.senla.model.service;

import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.ConfigProperty;
import com.senla.di.annotation.Singleton;
import com.senla.di.handler.AuttowiredHandler;
import com.senla.model.api.dao.IBookDAO;
import com.senla.model.api.service.IRequestService;
import com.senla.model.model.Book;

import com.senla.model.model.BookStatus;
import com.senla.model.api.service.IBookService;
import com.senla.model.exception.DAOException;
import com.senla.model.exception.ServiceException;
import com.senla.model.model.Request;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class BookService implements IBookService {

    @Auttowared
    private IBookDAO bookDAO;
    @Auttowared
    private IRequestService requestService;

    private Map<String, Comparator<Book>> sort;

    private static final Logger LOGGER = LogManager.getLogger(BookService.class.getName());


    @ConfigProperty(propertyName = "month")
    private int month;


    public BookService() {
        this.init();
    }



    public Set<String> getSortParams() {
        return new HashSet<>(this.sort.keySet());
    }

    private void init() {
        this.sort = new HashMap<>();

        this.sort.put("Alphabet", (o1, o2) -> o1.getName().compareTo(o2.getName()));
        this.sort.put("ByCost", (o1, o2) -> {
            Double price1 = o1.getCost();
            Double price2 = o2.getCost();
            return price1.compareTo(price2);
        });
        this.sort.put("ByDateOfAdmission", (o1, o2) -> {
            LocalDate date = o1.getDateOfAdmission();
            return date.compareTo(o2.getDateOfAdmission());
        });
        this.sort.put("ByDateOfWriting", Comparator.comparing(Book::getYear));
        this.sort.put("Reserved", Comparator.comparing(Book::getStatus));

    }

    public void updateBook(UUID id, Book book) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Book id to update : %s  new book : %s", id, book));

            this.bookDAO.update(id, book);
        } catch (DAOException daoException) {
            LOGGER.log(Level.WARN, "UpdateBook failed", daoException);
            throw new ServiceException("Book update operation failed", daoException);
        }
    }

    public void deleteBook(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Book id to delete : %s", uuid));
            this.bookDAO.delete(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "DeleteBook failed", e);
            throw new ServiceException("Deleting book operation failed", e);
        }
    }

    public List<Book> getAll() throws ServiceException {
        try {
            return new ArrayList<>(this.bookDAO.getAll());
        } catch (DAOException e){
            LOGGER.log(Level.WARN, e.getMessage(), e);
            throw new ServiceException("Get all operation failed", e);
        }
    }

    @Override
    public boolean writeOffBook(UUID id) throws ServiceException {
        LOGGER.log(Level.INFO, String.format("Writing off Book id is: %s", id), id);

        try {
            Book book = this.bookDAO.getEntityById(id);

            if (book.getStatus().equals(BookStatus.RESERVED)) {
                book.setStatus(BookStatus.ABSENT);
            }
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "WritingOffBook failed", e);
            throw new ServiceException("WritingOffBook operation failed", e);
        }

        return false;
    }

    public Book getBookById(UUID uuid) throws ServiceException {
        try {
            return this.bookDAO.getEntityById(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Get book by id failed", e);
            throw new ServiceException("Get book by id operation failed", e);
        }
    }

    @Override
    public void addBookToShop(String name, String genre, int year, double cost) throws ServiceException {
        Book book = new Book();

        LOGGER.log(Level.INFO ,String.format("Add book to Shop  params. Name : %s, Genre : %s, Year : %s, Cost : %s", name, genre, year, cost));

        if (cost < 0) throw new ServiceException("Cost is negative");

        book.setName(name);
        book.setGenre(genre);
        book.setYear(year);
        book.setCost(cost);

        try {
            this.bookDAO.addEntity(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Adding book failed", e);
            throw new ServiceException("Adding book  operation failed", e);
        }
    }

    public void add(Book book) throws ServiceException {
        try {
            this.bookDAO.addEntity(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Adding book failed", e);
            throw new ServiceException("Adding book operation failed", e);
        }
    }

    public void addBookToWareHouse(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("AddingToWareHouse Book id is:  %s", uuid));
            Book book = this.getBookById(uuid);

            if (book.getStatus().equals(BookStatus.ABSENT)) {
                book.setStatus(BookStatus.RESERVED);
            }
                if(requestService.getNumberOfRequestsByBook(book.getUuid()) != 0){
                    Request request = requestService.getRequestByBook(book.getUuid());

                    request.setRequestOpenClose(false);
                    request.setCount(0);
                }
        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, "Adding book to WareHouse failed", e);
            throw new ServiceException(e);
        }
    }

    public List<Book> getSortedBooks(String condition) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Book sorting condition : %s", condition), condition);
            List<Book> list = new ArrayList<>(this.bookDAO.getAll());

            list.sort((this.sort.get(condition)));

            return list;

        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARN, "Condition for sorting Books is not acceptable", e);
            throw new ServiceException("Get sorted book operation failed", e);
        } catch (Exception e) {
            LOGGER.log(Level.WARN, "Something went wrong in SortedBooks opetion", e);
            throw new ServiceException("Something went wrong in SortedBooks opetion", e);
        }

    }

    @Override
    public List<Book> getSortedStaledBooks(String condition) throws ServiceException {
        try {
            List<Book> sorted = new ArrayList<>();
            LocalDate date = LocalDate.now();
            /*    Optional<String> properties = PropertyHandler.getProperties("month");*/



            sorted = this.bookDAO.getAll().stream().filter(x -> x.getDateOfAdmission().isBefore(date.minusMonths(this.month))).collect(Collectors.toList());

            sorted.sort(this.sort.get(condition));

            if (sorted.isEmpty()) return Collections.emptyList();

            return sorted;
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARN, "Condition for sorting Books is not acceptable", e);
            throw new ServiceException("Get sorted book operation failed", e);

        } catch (NoSuchElementException e) {
            LOGGER.log(Level.WARN, "Property month is inaccessible", e);
            throw new ServiceException("Get sorted book operation failed", e);

        } catch (Exception e) {
            LOGGER.log(Level.WARN, "Something went wrong in SortedBooks opetion", e);
            throw new ServiceException("Something went wrong in SortedBooks opetion", e);

        }
    }

    public int getMonth(){
        return this.month;
    }
    @Override
    public String toString() {
        return "BookService{" +
                "bookDAO=" + bookDAO +
                ", sort=" + sort +
                ", month=" + month +
                '}';
    }


}
