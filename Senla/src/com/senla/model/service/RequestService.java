package com.senla.model.service;

import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Singleton;
import com.senla.model.api.service.IBookService;
import com.senla.model.model.Book;
import com.senla.model.model.Request;
import com.senla.model.api.dao.IRequestDAO;
import com.senla.model.api.service.IRequestService;
import com.senla.model.exception.DAOException;
import com.senla.model.exception.ServiceException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class RequestService implements IRequestService {

    @Auttowared
    private IRequestDAO requestDAO;


    private Map<String, Comparator<Request>> sort;

    private static final Logger LOGGER = Logger.getLogger(RequestService.class.getName());

    public RequestService() {

        this.init();
    }


    public void init() {
        this.sort = new HashMap<>();

        this.sort.put("ByCount", Comparator.comparing(Request::getCount));
        this.sort.put("ByName", (Comparator.comparing(o -> o.getRequestedBook().getName())));

    }

    @Override
    public void createRequest(Book book) throws ServiceException {  // Create Request by book, or increase count of requests for book, if existed
        try {
            LOGGER.log(Level.INFO, String.format("Create request book : %s ", book));

            this.requestDAO.getAll().stream()
                    .filter(item -> item.getRequestedBook().getUuid().equals(book.getUuid()))
                    .forEach(Request::increaseRequestCount);

            if (this.getNumberOfRequestsByBook(book.getUuid()) == 0) {
                Request request = new Request(book);
                requestDAO.addEntity(request);
            }


        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, ("Create request failed"));
            throw new ServiceException("Create request operation failed", e);
        }
    }

    public List<Request> getSortedRequests(String condition) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Condition to sort is : %s", condition), condition);

            List<Request> list = this.requestDAO.getAll();

            list.sort(this.sort.get(condition));

            return list;
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Request sorting condition is not available", e);
            throw new ServiceException("Request sorting operation failed", e);
        } catch (DAOException e){
            LOGGER.log(Level.WARNING, "Request sorting condition is not available", e);
            throw new ServiceException("Request sorting operation failed", e);
        }
    }

    public Long getNumberOfRequestsByBook(UUID uuid) throws ServiceException {
        try {
            List<Request> list = new ArrayList<Request>(this.requestDAO.getAll());

            return list.stream()
                    .filter(x -> x.getRequestedBook().getUuid().equals(uuid))
                    .count();
        } catch (DAOException e){
            LOGGER.log(Level.WARNING, "Get number of Requested books is not available", e);
            throw new ServiceException("Get number of Requested  failed", e);
        }
    }

    public List<Request> getAllRequests() throws ServiceException {
        try {
            return this.requestDAO.getAll();
        }catch (DAOException e){
            LOGGER.log(Level.WARNING, "Get all Request is not available", e);
            throw new ServiceException("Get all Request failed", e);
        }
    }

    public Request getRequestByBook(UUID uuid) throws ServiceException {
        try {
            List<Request> requests = new ArrayList<>(this.requestDAO.getAll());
            Optional<Request> request = requests.stream().filter(x -> x.getRequestedBook().getUuid().equals(uuid)).findFirst();

            return request.get();
        } catch (DAOException e){
            LOGGER.log(Level.WARNING, "Get  Request by Book is not available", e);
            throw new ServiceException("Get Request by Book failed", e);
        }
    }

    public void closeRequestById(UUID uuid) throws ServiceException {
        Request request = this.getRequestByBook(uuid);

        request.setRequestOpenClose(false);
    }

/*
    public Request getRequestByBook(UUID uuid) throws ServiceException {
        try {
            List<Request> requests = new ArrayList<>(this.requestDAO.getAll());
            Optional<Request> request = requests.stream().filter(x -> x.getRequestedBooks().getUuid().equals(uuid)).findFirst();

            if (request.isEmpty()) {
                Request newRequest = new Request(bookService.getBookById(uuid));
                requestDAO.addEntity(newRequest);
                return newRequest;
            } else return request.get();

        } catch (DAOException e) {
            LOGGER.log(Level.WARNING, "Get request by Book id failed", e);
            throw new ServiceException("Get request by book failed", e);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Get request by book failed");
            throw new ServiceException("Get request by book failed", e);

        }

    }
*/
}

