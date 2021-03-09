package com.Models.Services;

import com.Models.DAO.BookDAO;
import com.Models.DAO.RequestDAO;
import com.Models.Models.Book;
import com.Models.Models.BookStatus;
import com.Models.Serializable.PropertyHanlder;
import com.Models.api.DAO.IBookDAO;
import com.Models.api.DAO.IRequestDAO;
import com.Models.api.Service.IBookService;
import com.Models.exceptions.DAOException;
import com.Models.exceptions.ServiceException;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookService implements IBookService {

    private IBookDAO bookDAO;
    private static BookService instance;
    private Map<String, Comparator<Book>> sort;
    private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());
    private static RequestService requestService;

    private BookService() {
        this.bookDAO = BookDAO.getInstance();
        requestService= RequestService.getInstance();
        this.init();

    }

    public static BookService getInstance() {
        instance = Objects.requireNonNullElse(instance, new BookService());

        return instance;
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
            LOGGER.log(Level.WARNING, "UpdateBook failed", daoException);
            throw new ServiceException("Book update operation failed", daoException);
        }
    }

    public void deleteBook(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Book id to delete : %s", uuid));
            this.bookDAO.delete(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "DeleteBook failed", e);
            throw new ServiceException("Deleting book operation failed", e);
        }
    }

    public List<Book> getAll() {
        return new ArrayList<>(this.bookDAO.getAll());
    }

    @Override
    public boolean writeOffBook(UUID id) throws ServiceException {
        LOGGER.log(Level.INFO, String.format("Writing off Book id is: %s", id), id);

        try {
            Book book = this.bookDAO.getEntity(id);

            if (book.getStatus().equals(BookStatus.RESERV)) {
                book.setStatus(BookStatus.ABSENT);



            }
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "WritingOffBook failed", e);
            throw new ServiceException("WritingOffBook operation failed", e);
        }

        return false;
    }

    public Book getBookById(UUID uuid) throws ServiceException {
        try {
            return this.bookDAO.getEntity(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Get book by id failed", e);
            throw new ServiceException("Get book by id operation failed", e);
        }
    }

    @Override
    public void addBookToShop(String name, String genre, int year, double cost) throws ServiceException {
        Book book = new Book();

        LOGGER.info( String.format("Add book to Shop  params. Name : %s, Genre : %s, Year : %s, Cost : %s", name, genre, year, cost));

        if(cost < 0) throw new ServiceException("Cost is negative");

        book.setName(name);
        book.setGenre(genre);
        book.setYear(year);
        book.setCost(cost);

        try {
            this.bookDAO.addEntity(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Adding book failed", e);
            throw new ServiceException("Adding book  operation failed", e);
        }
    }

    public void add(Book book) throws ServiceException {
        try {
            this.bookDAO.addEntity(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Adding book failed", e);
            throw new ServiceException("Adding book operation failed", e);
        }
    }

    public void addBookToWareHouse(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("AddingToWareHouse Book id is:  %s", uuid));
            Book book = this.getBookById(uuid);

            if (book.getStatus().equals(BookStatus.ABSENT)) {
                book.setStatus(BookStatus.RESERV);
            }
        }
        catch (ServiceException e){
            LOGGER.log(Level.WARNING, "Adding book to WareHouse failed", e);
            throw new ServiceException(e);
        }
    }

    public List<Book> getSortedBooks(String condition) throws ServiceException {
     try {
         LOGGER.log(Level.INFO, String.format("Book sorting condition : %s", condition), condition);
         List<Book> list = new ArrayList<>(this.bookDAO.getAll());

         list.sort((this.sort.get(condition)));

         return list;

     } catch (IllegalArgumentException e){
         LOGGER.log(Level.WARNING, "Condition for sorting Books is not acceptable", e);
         throw new ServiceException("Get sorted book operation failed", e);
     } catch (Exception e){
         LOGGER.log(Level.WARNING, "Something went wrong in SortedBooks opetion", e);
         throw  new ServiceException("Something went wrong in SortedBooks opetion", e);
     }

    }

    @Override
    public List<Book> getSortedStaledBooks(String condition) throws ServiceException {
       try{
        List<Book> sorted = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Optional<String> properties = PropertyHanlder.getProperties("month");
        int monthNumber = Integer.parseInt(properties.orElseThrow());

        sorted = this.bookDAO.getAll().stream().filter(x -> x.getDateOfAdmission().isBefore(date.minusMonths(monthNumber))).collect(Collectors.toList());

        sorted.sort(this.sort.get(condition));

        if (sorted.isEmpty()) return Collections.emptyList();

        return sorted;
       } catch (IllegalArgumentException e){
           LOGGER.log(Level.WARNING, "Condition for sorting Books is not acceptable", e);
           throw new ServiceException("Get sorted book operation failed", e);

       } catch (NoSuchElementException e){
           LOGGER.log(Level.WARNING, "Property month is inaccessible", e);
           throw new ServiceException("Get sorted book operation failed", e);

       }
       catch (Exception e){
           LOGGER.log(Level.WARNING, "Something went wrong in SortedBooks opetion", e);
           throw  new ServiceException("Something went wrong in SortedBooks opetion", e);

       }
    }

}
