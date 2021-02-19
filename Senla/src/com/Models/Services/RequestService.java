package com.Models.Services;

import com.Models.Models.Book;
import com.Models.Models.Request;
import com.Models.api.DAO.IRequestDAO;
import com.Models.api.Service.IRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RequestService implements IRequestService {

    private IRequestDAO requestDAO;

    public RequestService(IRequestDAO requestDAO) {
        this.requestDAO = requestDAO;
    }

    @Override
    public void createRequest(Book book) {  // Create Request by book, or increase count of requests for book, if existed
        if (book != null) {

            this.requestDAO.getAll().stream()
                    .filter(x -> x.getRequestedBooks().equals(book))
                    .forEach(Request::increaseRequestCount);

            if (this.getNumberOfRequestsByBook(book.getUuid()) == 0 ) {
                Request request = new Request(book);
                requestDAO.addEntity(request);
            }

        }

    }

    public Long getNumberOfRequestsByBook(UUID uuid){
       List<Request> list= new ArrayList<Request>(this.requestDAO.getAll());

       return list.stream().filter(x -> x.getRequestedBooks().getUuid().equals(uuid)).count();

    }

    public void showAllRequests() { // Отображает все Request

        for (Request reg : this.requestDAO.getAll()) {
            System.out.println(reg);
        }

    }


    public Request getByBook(Book book) { // return Requests for book or null
        List<Request> requests = new ArrayList<>(this.requestDAO.getAll());
        Optional<Request> request = requests.stream().filter(x -> x.getRequestedBooks().equals(book)).findFirst();

        return request.orElseGet(Request::new);
    }

}

