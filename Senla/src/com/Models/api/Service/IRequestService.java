package com.Models.api.Service;

import com.Models.Models.Book;
import com.Models.Models.Request;
import com.Models.exceptions.ServiceException;

public interface IRequestService {

    void createRequest(Book book) throws ServiceException;

    Request getByBook(Book book) throws ServiceException;



}
