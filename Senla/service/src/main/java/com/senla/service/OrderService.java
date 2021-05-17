package com.senla.service;


import com.senla.api.dao.IOrderDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IOrderService;
import com.senla.api.service.IRequestService;

import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.BookStatus;
import com.senla.model.Client;
import com.senla.model.OrderStatus;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private IRequestService requestService;
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class.getName());
    private Map<String, Comparator<Order>> sort;


    public OrderService() {
        this.init();
    }

    private void init() {
        this.sort = new HashMap<>();

        this.sort.put("ByCost", Comparator.comparing(Order::getTotalPrice));
        this.sort.put("ByDateOfExecution", Comparator.comparing(Order::getDateOfExecution));
        this.sort.put("ByStatus", Comparator.comparing(Order::getStatus));
    }

    public List<Order> getSortedOrders(String condition) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Condition to sort is : %s", condition), condition);

            List<Order> list = new ArrayList<Order>(this.orderDAO.getAll());

            list.sort(sort.get(condition));

            return list;
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARN, "Order sorting condition is not available", e);
            throw new ServiceException("Order sorting operation failed", e);
        }
        catch (DAOException e){
            LOGGER.log(Level.WARN, "Order sorting condition is not available", e);
            throw new ServiceException("Order sorting operation failed", e);
        }
    }

    public Order getOrderByID(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Order id for search : %s", uuid), uuid);
            return this.orderDAO.getEntityById(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Get order by id failed", e);
            throw new ServiceException("Get order by id operation failed", e);
        }

    }

    public List<Order> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) throws ServiceException {  // Get amount of months from property
        try {
            LOGGER.log(Level.INFO, String.format("Book sorting condition : %s", condition), condition);

            List<Order> orders;

            orders = this.orderDAO.getAll().stream().filter(x -> x.getStatus().equals(OrderStatus.CLOSED))
                    .filter(y -> y.getDateOfExecution().isAfter(from) && y.getDateOfExecution().isBefore(to))
                    .filter(y -> y.getDateOfExecution().equals(from) || y.getDateOfExecution().equals(to))
                    .collect(Collectors.toList());
            orders.sort(this.sort.get(condition));

            return orders;
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARN, "Get closed order by Time failed");
            throw new ServiceException("Get closed order by Time operation failed", e);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Get order by id failed", e);
            throw new ServiceException("Get order by id operation failed", e);
        }
    }

    public double getTotalRevenue() throws ServiceException {
        try {
            double revenue = 0;

            for (Order ord : new ArrayList<Order>(this.orderDAO.getAll())) {
                revenue += ord.getTotalPrice();
            }
            return revenue;
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order Service opeation failed", e);
            throw new ServiceException("Order Service opeation failed", e);
        }
    }

    public List<Order> getAll() throws ServiceException {
        try {
            return new ArrayList<>(this.orderDAO.getAll());
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order Service opeation failed", e);
            throw new ServiceException("Order Service opeation failed", e);
        }
    }

    public void delete(Order order) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Delete order : %s"), order);

            this.orderDAO.delete(order);

        } catch (Exception e) {
            throw new ServiceException("delete operation failed", e);
        }
    }


    public void closeOrder(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Close order uuid : %s", uuid), uuid);

            Order order = this.orderDAO.getEntityById(uuid);

            order.setStatus(OrderStatus.CLOSED);
            order.setDateOfExecution(LocalDate.now());
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order close faild");
            throw new ServiceException("Order close opeartion faild", e);
        }
    }

    @Override
    public Order createOrder(Book book, Client client) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Create order book : %s. Client : %s", book, client));

            Order order = new Order(new ArrayList<Book>(), client);

            if(book.getStatus().equals(BookStatus.ABSENT)){
                requestService.createRequest(book);
            }

            order.getBooksToOrder().add(book);
            order.setTotalPrice(order.countTotalCost());

            this.orderDAO.addEntity(order);

            return order;
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order Create faild");
            throw new ServiceException("Order Create opeartion faild", e);
        }
    }

    public void addBookToOrder(UUID uuid, Book book) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("AddBook to Order uuid : %s", uuid));

            Order order = this.orderDAO.getEntityById(uuid);

            order.addBook(book);
        } catch (Exception e) {
            LOGGER.log(Level.WARN, "AddBook to Order failed", e);
            throw new ServiceException("AddBook to Order failed", e);
        }
    }

    public void deleteBookFromOrder(UUID uuid, Book book) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Order uuid : %s. Book to delete : %s", uuid, book));

            Order order = this.orderDAO.getEntityById(uuid);

            order.removeBook(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Delete book from Order opeartion failed");
            throw new ServiceException("\"Delete book from Order opeartion failed\"", e);
        }
    }

    @Override
    public Long amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to) throws ServiceException {
        if (from == null || to == null) return new Long(0);
        try {
            return this.orderDAO.getAll().stream().filter(x -> x.getStatus().equals(OrderStatus.CLOSED))
                    .filter(y -> y.getDateOfExecution().isAfter(from) && y.getDateOfExecution().isBefore(to))
                    .filter(y -> y.getDateOfExecution().equals(from) || y.getDateOfExecution().equals(to))
                    .count();
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order Service opeation failed", e);
            throw new ServiceException("Order Service opeation failed", e);
        }
    }

    /*  public void updateOrder(UUID id, Book book, Client client) {

          Order order = this.orderDAO.getEntity(id);

      }

      public Order getOrderByClientName(String name) {
          Optional<Order> order = this.orderDAO.getAll().stream()
                  .filter(x -> x.getClient().getName().equals(name))
                  .findFirst();

          return order.orElseGet(() -> new Order(new Client("", "")));
      }
  */
    public void addOrder(Order order) throws ServiceException {
        try {
            this.orderDAO.addEntity(order);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Adding order failed", e);

            throw new ServiceException("Adding order operation failed", e);
        }

    }

    public void cancelOrder(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Cancel order uuid : %s", uuid), uuid);

            this.orderDAO.getEntityById(uuid).setStatus(OrderStatus.CANCELED);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order cancel faild");
            throw new ServiceException("Order close opeartion faild", e);
        }
    }

    @Override
    public void orderDone(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Done order uuid : %s", uuid), uuid);

            List<Book> books = new ArrayList<>(this.orderDAO.getEntityById(uuid).getBooksToOrder());

           Optional<Book> book = books.stream().filter(x -> x.getStatus().equals(BookStatus.ABSENT)).findFirst();
            if(book.isEmpty()){
                LOGGER.log(Level.INFO, "Order has ABSENT books");

                throw new ServiceException("Order has ABSENT book(s)");
            }

            this.orderDAO.getEntityById(uuid).setStatus(OrderStatus.CLOSED);
            this.orderDAO.getEntityById(uuid).setDateOfExecution(LocalDate.now());

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order Done faild");
            throw new ServiceException("Order Done opeartion faild", e);
        }
    }

    public void orderOpen(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Open order uuid : %s", uuid), uuid);

            this.orderDAO.getEntityById(uuid).setStatus(OrderStatus.OPEN);

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order Open faild");
            throw new ServiceException("Order Open opeartion faild", e);
        }
    }


}
