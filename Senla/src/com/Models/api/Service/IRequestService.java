package com.Models.api.Service;

import com.Models.Models.Book;
import com.Models.Models.Request;

public interface IRequestService {

    void createRequest(Book book);

    void showAllRequests();

    Request getByBook(Book book);



}
