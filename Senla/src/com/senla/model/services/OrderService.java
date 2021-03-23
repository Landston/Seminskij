package com.senla.model.services;

import com.senla.di.annotations.Auttowared;
import com.senla.di.annotations.Singleton;
import com.senla.model.models.Book;
import com.senla.model.models.Client;
import com.senla.model.models.Order;
import com.senla.model.models.OrderStatus;
import com.senla.model.dao.OrderDAO;


import com.senla.model.api.dao.IOrderDAO;
import com.senla.model.api.service.IOrderService;
import com.senla.model.exceptions.DAOException;
import com.senla.model.exceptions.ServiceException;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
public class OrderService implements IOrderService {

    @Auttowared
    private IOrderDAO orderDAO;
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
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
            LOGGER.log(Level.WARNING, "Order sorting condition is not available", e);
            throw new ServiceException("Order sorting operation failed", e);
        }
    }

    public Order getOrderByID(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Order id for search : %s", uuid), uuid);
            return this.orderDAO.getEntity(uuid);

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Get order by id failed", e);
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
            LOGGER.log(Level.WARNING, "Get closed order by Time failed");
            throw new ServiceException("Get closed order by Time operation failed", e);
        }
    }

    public double getTotalRevenue() {
        double revenue = 0;

        for (Order ord : new ArrayList<Order>(this.orderDAO.getAll())) {
            revenue += ord.getTotalPrice();
        }
        return revenue;
    }

    public List<Order> getAllOrdes() {
        return new ArrayList<>(this.orderDAO.getAll());
    }

    public boolean delete(Order order) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Delete order : "), order);

            this.orderDAO.delete(order);
            return true;
        } catch (Exception e) {
            throw new ServiceException("delete operation failed", e);
        }
    }

    public void deleteOrder(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Delete order uuid : %s", uuid), uuid);

            this.orderDAO.delete(this.orderDAO.getEntity(uuid));
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Order deleting faild");
            throw new ServiceException("Order deleting opeartion faild", e);
        }
    }

    public void closeOrder(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Close order uuid : %s", uuid), uuid);

            Order order = this.orderDAO.getEntity(uuid);

            order.setStatus(OrderStatus.CLOSED);
            order.setDateOfExecution(LocalDate.now());
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Order close faild");
            throw new ServiceException("Order close opeartion faild", e);
        }
    }

    @Override
    public Order createOrder(Book book, Client client) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Create order book : %s. Client : %s", book, client));

            Order order = new Order(new ArrayList<Book>(), client);

            order.getBooksToOrder().add(book);
            this.orderDAO.addEntity(order);

            return order;
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Order Create faild");
            throw new ServiceException("Order Create opeartion faild", e);
        }
    }

    public void addBookToOrder(UUID uuid, Book book) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("AddBook to Order uuid : %s", uuid));

            Order order = this.orderDAO.getEntity(uuid);

            order.addBook(book);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "AddBook to Order failed", e);
            throw new ServiceException("AddBook to Order failed", e);
        }
    }

    public void deleteBookFromOrder(UUID uuid, Book book) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Order uuid : %s. Book to delete : %s", uuid, book));

            Order order = this.orderDAO.getEntity(uuid);

            order.removeBook(book);
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Delete book from Order opeartion failed");
            throw new ServiceException("\"Delete book from Order opeartion failed\"", e);
        }
    }

    @Override
    public Long amountOfClosedOrdersForThePeriod(LocalDate from, LocalDate to) {
        if (from == null || to == null) return new Long(0);

        return this.orderDAO.getAll().stream().filter(x -> x.getStatus().equals(OrderStatus.CLOSED))
                .filter(y -> y.getDateOfExecution().isAfter(from) && y.getDateOfExecution().isBefore(to))
                .filter(y -> y.getDateOfExecution().equals(from) || y.getDateOfExecution().equals(to))
                .count();
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
            LOGGER.log(Level.WARNING, "Adding order failed", e);
            throw new ServiceException("Adding order operation failed", e);

        }

    }

    public void cancelOrder(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Cancel order uuid : %s", uuid), uuid);

            this.orderDAO.getEntity(uuid).setStatus(OrderStatus.CANCELED);
        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Order cancel faild");
            throw new ServiceException("Order close opeartion faild", e);
        }
    }

    @Override
    public void orderDone(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Done order uuid : %s", uuid), uuid);

            this.orderDAO.getEntity(uuid).setStatus(OrderStatus.CLOSED);
            this.orderDAO.getEntity(uuid).setDateOfExecution(LocalDate.now());

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Order Done faild");
            throw new ServiceException("Order Done opeartion faild", e);
        }
    }

    public void orderOpen(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Open order uuid : %s", uuid), uuid);

            this.orderDAO.getEntity(uuid).setStatus(OrderStatus.OPEN);

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Order Open faild");
            throw new ServiceException("Order Open opeartion faild", e);
        }
    }


}
