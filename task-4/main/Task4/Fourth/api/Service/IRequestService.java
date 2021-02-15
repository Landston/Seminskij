package main.Task3.Fourth.api.Service;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.Request;

public interface IRequestService {

    void createRequest(Book book);

    void showAllRequests();

    Request getByBook(Book book);



}
