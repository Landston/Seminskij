package com.senla.service;


import com.senla.api.dao.IBookDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IBookService;
import com.senla.api.service.IRequestService;

import com.senla.model.Book;
import com.senla.model.BookStatus;
import com.senla.model.Request;

import com.senla.model.dto.BookDTO;
import com.senla.model.mapper.api.BookMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
@PropertySource( {"classpath:serviceproperties.properties"})
public class BookService implements IBookService {


    private final IBookDAO bookDAO;


    private final IRequestService requestService;


    private final BookMapper bookMapper;

    private Map<String, Comparator<Book>> sort;

    private static final Logger LOGGER = LogManager.getLogger(BookService.class.getName());

    @Value("month")
    private int month;


    @Autowired
    public BookService(IBookDAO bookDAO, IRequestService requestService, BookMapper bookMapper) {
        this.bookDAO = bookDAO;
        this.requestService = requestService;
        this.bookMapper = bookMapper;
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

    public void update(BookDTO bookDTO) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Book   : %s", bookDTO));

            Book book = bookMapper.toEntity(bookDTO);

            bookDAO.update(book);
        } catch (DAOException daoException) {
            LOGGER.log(Level.WARN, "UpdateBook failed", daoException);
            throw new ServiceException("Book update operation failed", daoException);
        }
    }

    public void delete(UUID id) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Book to delete : %s", id));

            Book book = bookDAO.getEntityById(id);

            this.bookDAO.delete(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Delete Book failed", e);

            throw new ServiceException("Deleting book operation failed", e);
        }
    }

    public List<BookDTO> getAll() throws ServiceException {
        try {
            LOGGER.info("Request for all books");

            List<Book> books = new ArrayList<>(this.bookDAO.getAll());

            return bookMapper.bookListToBookDTOList(books);
        } catch (DAOException e) {
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
            bookDAO.update(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "WritingOffBook failed", e);

            throw new ServiceException("WritingOffBook operation failed", e);
        }
        return false;
    }

    public BookDTO getBookById(UUID uuid) throws ServiceException {
        try {
            Book book = bookDAO.getEntityById(uuid);

            return bookMapper.toDto(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Get book by id failed", e);
            throw new ServiceException("Get book by id operation failed", e);
        }
    }

    @Override
    public void addBookToShop(String name, String genre, int year, double cost) throws ServiceException {
        Book book = new Book();

        LOGGER.log(Level.INFO, String.format("Add book to Shop  params. Name : %s, Genre : %s, Year : %s, Cost : %s", name, genre, year, cost));

        if (cost < 0 | year < 0) throw new ServiceException("Data is invalid");

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

    public void add(BookDTO bookDTO) throws ServiceException {
        try {
            Book book = bookMapper.toEntity(bookDTO);

            this.bookDAO.addEntity(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Adding book failed", e);
            throw new ServiceException("Adding book operation failed", e);
        }
    }

    public void addBookToWareHouse(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("AddingToWareHouse Book id is:  %s", uuid));
            Book book = bookDAO.getEntityById(uuid);

            if (book.getStatus().equals(BookStatus.ABSENT)) {
                book.setStatus(BookStatus.RESERVED);
            }
            if (requestService.getNumberOfRequestsByBook(book.getId()) != 0) {
                Request request = requestService.getRequestByBook(book.getId());

                request.setRequestOpenClose(false);
                request.setCount(0);
            }
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Adding book to WareHouse failed", e);
            throw new ServiceException(e);
        }
    }

    public List<BookDTO> getSortedBooks(String condition) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Book sorting condition : %s", condition), condition);

            List<Book> list = new ArrayList<>(this.bookDAO.getAll());

            list.sort((this.sort.get(condition)));

            return bookMapper.bookListToBookDTOList(list);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARN, "Condition for sorting Books is not acceptable", e);
            throw new ServiceException("Get sorted book operation failed", e);
        } catch (Exception e) {
            LOGGER.log(Level.WARN, "Something went wrong in SortedBooks opetion", e);
            throw new ServiceException("Something went wrong in SortedBooks opetion", e);
        }

    }

    @Override
    public List<BookDTO> getSortedStaledBooks(String condition) throws ServiceException {
        try {
            List<Book> sorted;
            LocalDate date = LocalDate.now();

            sorted = this.bookDAO.getAll().stream()
                    .filter(x -> x.getDateOfAdmission().isBefore(date.minusMonths(this.month)))
                    .collect(Collectors.toList());
            sorted.sort(this.sort.get(condition));

            if (sorted.isEmpty()) return Collections.emptyList();

            return bookMapper.bookListToBookDTOList(sorted);
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

    @Override
    public String toString() {
        return "BookService{" +
                "bookDAO=" + bookDAO +
                ", sort=" + sort +
                ", month=" + month +
                '}';
    }


}
