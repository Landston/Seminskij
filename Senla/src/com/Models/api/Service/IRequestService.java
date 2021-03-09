package com.Models.api.Service;

import com.Models.Models.Book;
import com.Models.Models.Request;
import com.Models.exceptions.ServiceException;

import java.util.UUID;

public interface IRequestService {

    void createRequest(Book book) throws ServiceException;

    Request getRequestByBook(UUID book) throws ServiceException;



}
