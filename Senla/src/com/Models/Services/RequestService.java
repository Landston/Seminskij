package com.Models.Services;

import com.Models.DAO.RequestDAO;
import com.Models.Models.Book;
import com.Models.Models.Request;
import com.Models.api.DAO.IRequestDAO;
import com.Models.api.Service.IRequestService;

import java.util.*;


public class RequestService implements IRequestService {

    private IRequestDAO requestDAO;
    private static RequestService instance;
    private Map<String, Comparator<Request>> sort;

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
    public void createRequest(Book book) {  // Create Request by book, or increase count of requests for book, if existed
        if (book != null) {

            this.requestDAO.getAll().stream()
                    .filter(x -> x.getRequestedBooks().equals(book))
                    .forEach(Request::increaseRequestCount);

            if (this.getNumberOfRequestsByBook(book.getUuid()) == 0) {
                Request request = new Request(book);
                requestDAO.addEntity(request);
            }

        }

    }

    public List<Request> getSortedRequests(String condition) {
        List<Request> list = this.requestDAO.getAll();

        list.sort(this.sort.get(condition));

        return  list;
    }

    public Long getNumberOfRequestsByBook(UUID uuid) {
        List<Request> list = new ArrayList<Request>(this.requestDAO.getAll());

        return list.stream()
                .filter(x -> x.getRequestedBooks().getUuid().equals(uuid))
                .count();

    }

    public void showAllRequests() { // Отображает все Request

        for (Request reg : this.requestDAO.getAll()) {
            System.out.println(reg);
        }

    }

    public List<Request> getAllRequests() {
        return this.requestDAO.getAll();
    }

    public Request getByBook(Book book) { // return Requests for book or null
        List<Request> requests = new ArrayList<>(this.requestDAO.getAll());
        Optional<Request> request = requests.stream().filter(x -> x.getRequestedBooks().equals(book)).findFirst();

        return request.orElseGet(Request::new);
    }

}

