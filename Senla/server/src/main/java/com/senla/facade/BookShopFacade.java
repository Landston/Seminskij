package com.senla.facade;

import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IBookService;
import com.senla.api.service.IClientService;
import com.senla.api.service.IOrderService;
import com.senla.api.service.IRequestService;

import com.senla.model.*;
import com.senla.model.dto.BookDTO;
import com.senla.model.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Component
public class BookShopFacade {

    private final IBookService bookService;
    private final IClientService clientService;
    private final IOrderService orderService;
    private final IRequestService requestService;

    public BookShopFacade(IBookService bookService, IClientService clientService, IOrderService orderService, IRequestService requestService) {

        this.bookService = bookService;
        this.clientService = clientService;
        this.orderService = orderService;
        this.requestService = requestService;
    }


    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //////////// BOOK ////////////
    public List<BookDTO> getAllBooks() throws ServiceException {
        try {
            return bookService.getAll().isEmpty() ? Collections.emptyList() : bookService.getAll();
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteBook(UUID id) throws ServiceException {
        try {
            this.bookService.delete(id);
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

    public BookDTO getBookByID(UUID uuid) throws ServiceException {
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
            BookDTO dto = new BookDTO(updateBookID, name, BookStatus.RESERVED, genre,cost, year, LocalDate.now());
            this.bookService.update(dto);

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

    public List<BookDTO> getSortedBooks(String condition) throws ServiceException {
        try {

            if (condition != null) return this.bookService.getSortedBooks(condition);

        } catch (NullPointerException e) {
            System.out.println("Значение Condition в сортировке книг равно null или некорректно");
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    public List<BookDTO> getStaledBooks(String condition) throws ServiceException {
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

    public List<Client> getAllClients() throws ServiceException {

        return this.clientService.getAll();

    }

    public Client createClient(String name, String mail) {
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
    public OrderDTO createOrder(UUID bookID, UUID clientID) throws Exception {
        if (bookID == null || clientID == null || !bookID.equals(UUID.fromString("\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b")) || !clientID.equals(UUID.fromString("\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b"))) throw new Exception("Whrong data");

        return orderService.createOrder(bookID, clientID);
    }

    public List<OrderDTO> getSortedOrder(String condition) throws ServiceException, IOException {

        return this.orderService.getSortedOrders(condition);
    }

    public List<OrderDTO> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) throws ServiceException {
        return this.orderService.getClosedOrdersByTime(from, to, condition);

    }

    public Long amountOfClosedOrdersByTime(LocalDate from, LocalDate to) throws ServiceException {
        return this.orderService.amountOfClosedOrdersForThePeriod(from, to);
    }



    public void addBooktoOrder(UUID id, BookDTO book) throws ServiceException {
        this.orderService.addBookToOrder(id, book);


    }

    public void deleteBookFromOrder(UUID id, BookDTO book) throws ServiceException {
        this.orderService.deleteBookFromOrder(id, book);

    }

    public List<OrderDTO> getAllOrders() throws ServiceException {
        return this.orderService.getAll();
    }

    public OrderDTO getOrderByID(UUID uuid) throws ServiceException {
        try {
            return this.orderService.getOrderByID(uuid);
        } catch (NullPointerException e) {
            System.out.println("No order with such ID");
        }
        return null;
    }

    public void deleteOrderById(UUID order) throws ServiceException {
        this.orderService.delete(order);
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
            case "DONE": {
                this.orderService.orderDone(id);
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
        BookDTO book = this.bookService.getBookById(bookID);

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

    public double totalFirmRevenue() throws ServiceException {
        return this.orderService.getTotalRevenue();
    }


}
