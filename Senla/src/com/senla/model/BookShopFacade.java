package com.senla.model;

import com.senla.di.annotations.Auttowared;
import com.senla.di.annotations.Singleton;
import com.senla.model.models.Book;
import com.senla.model.models.Client;
import com.senla.model.models.Order;
import com.senla.model.models.Request;
import com.senla.model.serializable.Serializer;
import com.senla.model.services.BookService;
import com.senla.model.services.ClientService;
import com.senla.model.services.OrderService;
import com.senla.model.services.RequestService;
import com.senla.model.api.service.IBookService;
import com.senla.model.api.service.IClientService;
import com.senla.model.api.service.IOrderService;
import com.senla.model.api.service.IRequestService;
import com.senla.model.exceptions.DAOException;
import com.senla.model.exceptions.ServiceException;


import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Singleton
public class BookShopFacade {

    @Auttowared
    private  IBookService bookService ;
    @Auttowared
    private  IClientService clientService ;
    @Auttowared
    private  IOrderService orderService;
    @Auttowared
    private  IRequestService requestService ;

    public BookShopFacade() {

    }


    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //////////// BOOK ////////////
    public List<Book> getAllBooks() {
        return bookService.getAll().isEmpty() ? Collections.emptyList() : bookService.getAll();
    }

    public void deleteBook(UUID uuid) throws ServiceException {
        try {
            this.bookService.deleteBook(uuid);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public void addBookToShop(String name, String genre, int year, double cost) throws ServiceException {
        if (name == null || genre == null || name.equals("") || genre.equals("")) {
            System.out.println("Invalid date");

            return;
        }

        this.bookService.addBookToShop(name, genre, year, cost);
    }

    public Book getBookByID(UUID uuid) throws ServiceException {
        try {
            return this.bookService.getBookById(uuid);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public void updateBook(UUID updateBookID, String name, String genre, int year, double cost) {
        if (name == null || genre == null || name.equals("") || genre.equals("")) {
            System.out.println("Invalid date");

            return;
        }
        try {
            this.bookService.updateBook(updateBookID, new Book(name, genre, year, cost));

        } catch (ServiceException serviceException) {
            System.out.println("Entered wrong id");

        }
    }

    public void addBookToWareHouse(UUID uuid) throws ServiceException {
        if (this.bookService.getBookById(uuid).getName() != null)
            this.bookService.addBookToWareHouse(uuid);  // возвращает пустой Book, если нет совпадений
    }

    public void writeOffBookFromWareHouse(UUID uuid) throws ServiceException {
        if (this.bookService.getBookById(uuid).getName() != null)
            this.bookService.writeOffBook(uuid);  // возвращает пустой Book, если нет совпадений


    }

    public List<Book> getSortedBooks(String condition) throws ServiceException {
        try {

            if (condition != null) return this.bookService.getSortedBooks(condition);

        } catch (NullPointerException e) {
            System.out.println("Значение Condition в сортировке книг равно null или некорректно");
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    public List<Book> getStaledBooks(String condition) throws ServiceException {
        try {
            if (condition != null) return this.bookService.getSortedStaledBooks(condition);

        } catch (NullPointerException e) {
            System.out.println("Значение Condition в сортировке книг равно null или некорректно");

            return Collections.emptyList();
        }

        return Collections.emptyList();
    }


// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //////////// CLIENT ////////////

    public void updateClient(UUID clienUpdateID, Client client) throws ServiceException {
        this.clientService.update(clienUpdateID, client);
    }

    public void deleteClient(UUID uuid) throws ServiceException {
        this.clientService.delete(uuid);
    }

    public List<Client> getAllClients()  {
        return this.clientService.getAll();

    }

    public Client createClient(String name, String mail)  {
            return this.clientService.create(name, mail);
    }

    public void addClient(String name, String mail) throws ServiceException {
        this.clientService.add(this.createClient(name, mail));
    }

    public Client getClientByID(UUID uuid) throws ServiceException {
        try {
            Client client = this.clientService.get(uuid);

            if (client.getName() != "none") return client;

            else {
                System.out.println("Нет клиентов с таким ID, создать нового?");

                Scanner scanner = new Scanner(System.in);
                String answer = scanner.nextLine();

                switch (answer) {
                    case "Да": {
                        System.out.println("Введите имя Клиента");

                        String name = scanner.nextLine();

                        System.out.println("Введите mail Клиента");

                        return this.createClient(name, scanner.nextLine());
                    }
                    case "Нет": {
                        return null;
                    }
                }
            }


        } catch (NullPointerException e) {
            System.out.println("Нет клиентов с таким ID");


        }
        return new Client("", "");
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //////////// ORDER ////////////
    public Order createOrder(UUID bookID, UUID clientID) throws ServiceException {
        Client client = this.getClientByID(clientID);

        Order order = new Order(this.getBookByID(bookID), client);

        if (order.getClient() == null) return null;

        return order;
    }

    public List<Order> getSortedOrder(String condition) throws ServiceException, IOException {

        return this.orderService.getSortedOrders(condition);
    }

    public List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) throws ServiceException {
        return this.orderService.getClosedOrdersByTime(from, to, condition);

    }

    public Long amountOfClosedOrdersByTime(LocalDate from, LocalDate to) throws ServiceException {
        return this.orderService.amountOfClosedOrdersForThePeriod(from, to);
    }

    public void addOrder(UUID bookID, UUID clientID) throws ServiceException {
        Order order = this.createOrder(bookID, clientID);

        if (order.getClient() == null || order.getBooksToOrder() == null) return;

        this.orderService.addOrder(order);
    }

    public void addBooktoOrder(UUID id, Book book) throws ServiceException {
        this.orderService.addBookToOrder(id, book);


    }

    public void deleteBookFromOrder(UUID id, Book book) throws ServiceException {
        this.orderService.deleteBookFromOrder(id, book);

    }

    public List<Order> getAllOrders() throws ServiceException {
        return this.orderService.getAllOrdes();
    }

    public Order getOrderByID(UUID uuid) throws ServiceException {
        try {
            return this.orderService.getOrderByID(uuid);
        } catch (NullPointerException e) {
            System.out.println("No order with such ID");
        }
        return null;
    }

    public void deleteOrderById(UUID uuid) throws ServiceException {
        this.orderService.deleteOrder(uuid);
    }

    public void changeOrder(UUID id, String status) throws ServiceException {
        switch (status) {
            case "OPEN": {
                this.orderService.orderOpen(id);
                break;
            }
            case "CLOSED": {
                this.orderService.closeOrder(id);
                break;
            }
            case "CANCELED": {
                this.orderService.cancelOrder(id);
                break;
            }
            default:
                System.out.println("No such status");
                ;
        }
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //////////// Requests ////////////


    public void createRequest(UUID bookID) throws ServiceException {
        Book book = this.bookService.getBookById(bookID);

        this.requestService.createRequest(book);
    }

    public List<Request> getAllRequests() throws ServiceException {
        return this.requestService.getAllRequests();

    }

    public List<Request> getSortedRequests(String condition) throws ServiceException {
        return this.requestService.getSortedRequests(condition);
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //////////// Other ////////////

    public double totalFirmRevenue() {
        return this.orderService.getTotalRevenue();
    }

    public void serialization() throws DAOException {

        Serializer.serialize(this.bookService.getAll(),
                this.clientService.getAll(),
                this.orderService.getAllOrdes(),
                this.requestService.getAllRequests());
    }
}
