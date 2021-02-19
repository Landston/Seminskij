package com.Models.Services;

import com.Models.DAO.BookDAO;
import com.Models.Models.Book;
import com.Models.Models.BookStatus;
import com.Models.api.DAO.IBookDAO;
import com.Models.api.Service.IBookService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookService implements IBookService {

    private final IBookDAO bookDAO;
    private Map<String, Comparator<Book>> sort;

    public BookService(BookDAO dao) {
        this.bookDAO = dao;
        this.init();

    }

    public Set<String> getSortParams(){
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

    public List<Book> getAll() {
        return new ArrayList<>(this.bookDAO.getAll());
    }

    @Override
    public boolean writeOffBook(UUID id) {
        Book book = this.bookDAO.getEntity(id);

        if (book != null) {
            book.setStatus(BookStatus.ABSENT);
            return true;
        }

        return false;
    }

    @Override
    public void addBookToShop(String name, String genre, int year, double cost) throws NullPointerException{
        Book book = new Book();

        book.setName(name);
        book.setGenre(genre);
        book.setYear(year);
        book.setCost(cost);

        this.bookDAO.addEntity(book);
    }

    public List<Book> getSortedBooks(String condition) {
        List<Book> list = new ArrayList<>(this.bookDAO.getAll());

        list.sort((this.sort.get(condition)));

        return list;
    }

    @Override
    public List<Book> getSortedBooksThatAreNotSoldBySixMonths(String condition) {
        List<Book> sorted = new ArrayList<>();
        LocalDate date = LocalDate.now();

        sorted = this.bookDAO.getAll().stream().filter(x -> x.getDateOfAdmission().isBefore(date.minusMonths(6))).collect(Collectors.toList());

        sorted.sort(this.sort.get(condition));

        if (sorted.isEmpty()) return Collections.emptyList();

        return sorted;
    }

}
