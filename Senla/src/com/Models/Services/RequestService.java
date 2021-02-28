package com.Models.Services;

import com.Models.DAO.RequestDAO;
import com.Models.Models.Book;
import com.Models.Models.Request;
import com.Models.api.DAO.IRequestDAO;
import com.Models.api.Service.IRequestService;
import com.Models.exceptions.DAOException;
import com.Models.exceptions.ServiceException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RequestService implements IRequestService {

    private IRequestDAO requestDAO;
    private static RequestService instance;
    private Map<String, Comparator<Request>> sort;
    private static final Logger LOGGER = Logger.getLogger(RequestService.class.getName());

    public RequestService() {
        this.requestDAO = RequestDAO.getInstance();
    }

    public static RequestService getInstance() {
        instance = Objects.requireNonNullElse(instance, new RequestService());
        ;

        return instance;
    }

    public void init() {
        this.sort = new HashMap<>();

        this.sort.put("ByCount", Comparator.comparing(Request::getCount));
        this.sort.put("ByName", (Comparator.comparing(o -> o.getRequestedBooks().getName())));

    }

    @Override
    public void createRequest(Book book) throws ServiceException {  // Create Request by book, or increase count of requests for book, if existed
        try {
            LOGGER.log(Level.INFO, String.format("Create request book : %s ", book));
            this.requestDAO.getAll().stream()
                    .filter(x -> x.getRequestedBooks().equals(book))
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
        }
    }

    public Long getNumberOfRequestsByBook(UUID uuid) {
        List<Request> list = new ArrayList<Request>(this.requestDAO.getAll());

        return list.stream()
                .filter(x -> x.getRequestedBooks().getUuid().equals(uuid))
                .count();

    }


    public List<Request> getAllRequests() {
        return this.requestDAO.getAll();
    }

    public Request getByBook(Book book) throws ServiceException {
        try {// return Requests for book or null
            List<Request> requests = new ArrayList<>(this.requestDAO.getAll());
            Optional<Request> request = requests.stream().filter(x -> x.getRequestedBooks().equals(book)).findFirst();

            return request.orElseThrow();
        } catch (Exception e){
            LOGGER.log(Level.WARNING, "Get request by book failed");
            throw new ServiceException("Get request by book failed", e);

        }
    }

}

