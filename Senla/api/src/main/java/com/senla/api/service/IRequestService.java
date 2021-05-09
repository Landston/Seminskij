package com.senla.api.service;

import com.senla.api.exception.service.ServiceException;
import com.senla.model.Book;
import com.senla.model.Request;

import java.util.List;
import java.util.UUID;


public interface IRequestService {

    void createRequest(Book book) throws ServiceException;

    Request getRequestByBook(UUID book) throws ServiceException;

    List<Request> getSortedRequests(String condition) throws ServiceException;

    Long getNumberOfRequestsByBook(UUID uuid) throws ServiceException;

    List<Request> getAllRequests() throws ServiceException;

    void closeRequestById(UUID uuid) throws ServiceException;
}
