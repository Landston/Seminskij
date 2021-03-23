package com.senla.model.api.service;

import com.senla.model.models.Book;
import com.senla.model.models.Request;
import com.senla.model.exceptions.ServiceException;

import java.util.List;
import java.util.UUID;

public interface IRequestService {

    void createRequest(Book book) throws ServiceException;

    Request getRequestByBook(UUID book) throws ServiceException;

    List<Request> getSortedRequests(String condition) throws ServiceException;

    Long getNumberOfRequestsByBook(UUID uuid);

    List<Request> getAllRequests();

    void closeRequestById(UUID uuid) throws ServiceException;
}
