package main.Task3.Fourth.Services;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.Request;
import main.Task3.Fourth.api.DAO.IRequestDAO;
import main.Task3.Fourth.api.Service.IRequestService;


public class RequestService implements IRequestService {

    private IRequestDAO requestDAO;

    public RequestService(IRequestDAO requestDAO) {

        this.requestDAO = requestDAO;
    }

    @Override
    public void createRequest(Book book) {  // Create Request by book, or increase count of requests for book, if existed


        if (book != null) {
            boolean flag = false;
            for (Request req : requestDAO.getAll()) {

                if (book.equals(req.getRequestedBooks())) {

                    req.increaseRequestCount();

                    flag = true;
                }
            }
        System.out.println();
            if (flag == false) {
                Request request = new Request(book);
                requestDAO.addEntity(request);
            }

        }

    }

    public void showAllRequests() { // Отображает все Request

        for (Request reg : this.requestDAO.getAll()) {
            System.out.println(reg);
        }

    }


    public Request getByBook(Book book) { // return Requests for book or null

        for (Request req : this.requestDAO.getAll()) {

            if (req.getRequestedBooks() == book) {
                return req;

            }
        }
        return null;

    }


    public void howManyRequestsForBook(Book book) {  // Useless method


        System.out.println(this.getByBook(book).getCount());
    }

}

