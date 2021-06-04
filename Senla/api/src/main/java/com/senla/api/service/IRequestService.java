package com.senla.api.service;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.model.Book;
import com.senla.model.Request;
import com.senla.model.dto.BookDTO;
import com.senla.model.dto.RequestDTO;

import java.security.Provider;
import java.util.List;
import java.util.UUID;


public interface IRequestService {

    RequestDTO createRequest(UUID id) throws ServiceException;

    RequestDTO getRequestByBook(UUID book) throws ServiceException;

    List<RequestDTO> getSortedRequests(String condition) throws ServiceException;

    Long getNumberOfRequestsByBook(UUID uuid) throws ServiceException;

    List<RequestDTO> getAllRequests() throws ServiceException;

    RequestDTO closeRequestById(UUID uuid) throws ServiceException, DAOException;

    RequestDTO update(RequestDTO DTO) throws ServiceException;
}
