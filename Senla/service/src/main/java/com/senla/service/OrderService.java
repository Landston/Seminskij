package com.senla.service;


import com.senla.api.dao.IBookDAO;
import com.senla.api.dao.IClientDAO;
import com.senla.api.dao.IOrderDAO;
import com.senla.api.dao.IRequestDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IOrderService;
import com.senla.api.service.IRequestService;

import com.senla.model.Book;
import com.senla.model.Order;
import com.senla.model.BookStatus;
import com.senla.model.Client;
import com.senla.model.OrderStatus;

import com.senla.model.dto.BookDTO;
import com.senla.model.dto.OrderDTO;
import com.senla.model.mapper.api.BookMapper;
import com.senla.model.mapper.api.OrderMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private IOrderDAO orderDAO;
    @Autowired
    private IClientDAO clientDAO;
    @Autowired
    private IBookDAO bookDAO;
    @Autowired
    private IRequestService requestService;

    @Autowired
    private IRequestDAO requestDAO;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BookMapper bookMapper;

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

    public List<OrderDTO> getSortedOrders(String condition) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Condition to sort is : %s", condition), condition);

            List<Order> list = new ArrayList<Order>(this.orderDAO.getAll());

            list.sort(sort.get(condition));

            return orderMapper.orderBunchToOrderDTOBunch(list);
        } catch (IllegalArgumentException | DAOException e) {
            LOGGER.log(Level.WARN, "Order sorting condition is not available", e);
            throw new ServiceException("Order sorting operation failed", e);
        }
    }

    public OrderDTO getOrderByID(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Order id for search : %s", uuid), uuid);

            Order order = this.orderDAO.getEntityById(uuid);

            return orderMapper.toDto(order);

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Get order by id failed", e);
            throw new ServiceException("Get order by id operation failed", e);
        }
    }

    public List<OrderDTO> getClosedOrdersByTime(LocalDate from, LocalDate to, String condition) throws ServiceException {  // Get amount of months from property
        try {
            LOGGER.log(Level.INFO, String.format("Book sorting condition : %s", condition), condition);

            List<Order> orders;

            orders = this.orderDAO.getAll().stream().filter(x -> x.getStatus().equals(OrderStatus.CLOSED))
                    .filter(y -> y.getDateOfExecution().isAfter(from) && y.getDateOfExecution().isBefore(to))
                    .filter(y -> y.getDateOfExecution().equals(from) || y.getDateOfExecution().equals(to))
                    .collect(Collectors.toList());
            orders.sort(this.sort.get(condition));

            return orderMapper.orderBunchToOrderDTOBunch(orders);
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

    public List<OrderDTO> getAll() throws ServiceException {
        try {
            return orderMapper.orderBunchToOrderDTOBunch(this.orderDAO.getAll());
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order Service opeation failed", e);
            throw new ServiceException("Order Service opeation failed", e);
        }
    }

    public OrderDTO delete(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Delete order with id : %s"), uuid);

            Order order = orderDAO.getEntityById(uuid);

            this.orderDAO.delete(order);

            return orderMapper.toDto(order);
        } catch (Exception e) {
            throw new ServiceException("delete operation failed", e);
        }
    }

    public OrderDTO closeOrder(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Close order uuid : %s", uuid), uuid);

            Order order = this.orderDAO.getEntityById(uuid);

            if (order.getStatus().equals(OrderStatus.PAUSED)) {
                throw new ServiceException("ORDER IS PAUSED");

            }
            order.setStatus(OrderStatus.CLOSED);
            order.setDateOfExecution(LocalDate.now());

            return orderMapper.toDto(order);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order close faild");
            throw new ServiceException("Order close opeartion faild", e);
        }
    }

    @Override
    public OrderDTO createOrder(UUID bookId, UUID clientId) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Create order book : %s. Client : %s", bookId, clientId));

            Book book = bookDAO.getEntityById(bookId);
            Client client = clientDAO.getEntityById(clientId);

            Order order = new Order(book, client);

            if (book.getStatus().equals(BookStatus.ABSENT)) {
                requestService.createRequest(bookId);

                order.setStatus(OrderStatus.PAUSED);
            }

            order.getBooksToOrder().add(book);
            order.setTotalPrice(order.countTotalCost());

            this.orderDAO.addEntity(order);

            return orderMapper.toDto(order);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order Create faild");
            throw new ServiceException("Order Create opeartion faild", e);
        }
    }

    public BookDTO addBookToOrder(UUID uuid, BookDTO bookDTO) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("AddBook to Order uuid : %s", uuid));

            Order order = this.orderDAO.getEntityById(uuid);
            Book book = bookMapper.toEntity(bookDTO);

            order.addBook(book);
            orderDAO.update(order);

            return bookMapper.toDto(book);
        } catch (Exception e) {
            LOGGER.log(Level.WARN, "AddBook to Order failed", e);
            throw new ServiceException("AddBook to Order failed", e);
        }
    }

    public BookDTO deleteBookFromOrder(UUID uuid, BookDTO bookDTO) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Order uuid : %s. Book to delete : %s", uuid, bookDTO));

            Order order = this.orderDAO.getEntityById(uuid);
            Book book = bookMapper.toEntity(bookDTO);

            order.removeBook(book);

            orderDAO.update(order);

            return bookMapper.toDto(book);
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


    public OrderDTO cancelOrder(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Cancel order uuid : %s", uuid), uuid);

            Order order = this.orderDAO.getEntityById(uuid);

            order.setStatus(OrderStatus.CANCELED);
            orderDAO.update(order);

            return orderMapper.toDto(order);
        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Order cancel faild");
            throw new ServiceException("Order close opeartion faild", e);
        }
    }

    @Override
    public OrderDTO orderDone(UUID uuid) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Done order uuid : %s", uuid), uuid);
            Order order = this.orderDAO.getEntityById(uuid);
            List<Book> books = order.getBooksToOrder();

            Optional<Book> absentBook = books.stream().filter(x -> x.getStatus().equals(BookStatus.ABSENT)).findFirst();
            if (absentBook.isEmpty()) {
                LOGGER.log(Level.INFO, "Order has ABSENT books");

                throw new ServiceException("Order has ABSENT book(s)");
            }

            order.setStatus(OrderStatus.CLOSED);
            order.setDateOfExecution(LocalDate.now());

            orderDAO.update(order);

            return orderMapper.toDto(order);
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

    @Override
    public OrderDTO update(OrderDTO orderDTOForUpdate) throws ServiceException {
        try {
            Order order = orderDAO.getEntityById(orderDTOForUpdate.getId());

            order = orderMapper.orderDtoToOrder(order, orderDTOForUpdate);
            orderDAO.update(order);

            return orderMapper.toDto(order);
        } catch (DAOException e){
            LOGGER.log(Level.WARN, e);
            throw new ServiceException(e);
        }
    }




}
