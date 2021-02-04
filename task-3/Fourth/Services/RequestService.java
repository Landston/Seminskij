package main.Task3.Fourth.Services;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.Request;
import main.Task3.Fourth.api.DAO.IRequestDAO;
import main.Task3.Fourth.api.Service.IRequestService;


public class RequestService implements IRequestService {

    private IRequestDAO requestDAO;

    public RequestService(IRequestDAO requestDAO){

        this.requestDAO = requestDAO;
    }

    @Override
    public void createRequest(Book book) {

        if (book != null) {
            boolean flag = false;
            for (Request req : requestDAO.getAll()) {

                if (book.equals(req.getRequestedBooks())) {

                    req.increaseRequestCount();

                    flag = true;
                }
            }

            if (flag == false) {
                Request request = new Request(book);
                requestDAO.addEntity(request);
            }

        }

    }

    public void showAllRequests(){

        for( Request reg : this.requestDAO.getAll())
        {
            System.out.println(reg);
        }

        }

        public void howMany(Book book){


            System.out.println(this.requestDAO.getByBook(book).getCount());
        }

    }

