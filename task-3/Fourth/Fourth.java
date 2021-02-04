package main.Task3.Fourth;

import main.Task3.Fourth.DAO.BookDAO;
import main.Task3.Fourth.DAO.OrderDAO;
import main.Task3.Fourth.DAO.RequestDAO;
import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.Order;
import main.Task3.Fourth.Models.Request;
import main.Task3.Fourth.Services.BookService;
import main.Task3.Fourth.Services.RequestService;
import main.Task3.Fourth.api.DAO.IRequestDAO;

import java.util.ArrayList;
import java.util.List;

public class Fourth {

    public static void main(String[] args) {


        Book book = new Book("The Hunt", "Detective", 2015);

        Book book1 = new Book("Bloody Moon", "Horror", 2000);

        List<Request>list = new ArrayList<Request>();
        IRequestDAO dao = new RequestDAO(list);

        RequestService service = new RequestService(dao);

        service.createRequest(book);

        service.createRequest(book1);

        service.createRequest(book);
        service.createRequest(book);

        service.showAllRequests();

        service.howMany(book);


    }

}
