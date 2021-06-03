package com.senla.service;


import com.senla.api.dao.IBookDAO;
import com.senla.api.dao.IRequestDAO;
import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import com.senla.api.service.IRequestService;

import com.senla.model.Book;
import com.senla.model.Request;

import com.senla.model.dto.BookDTO;
import com.senla.model.dto.RequestDTO;
import com.senla.model.mapper.api.BookMapper;
import com.senla.model.mapper.api.MapperForRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class RequestService implements IRequestService {

    private final IRequestDAO requestDAO;
    private final IBookDAO bookDAO;
    private final BookMapper bookMapper;
    private  final MapperForRequest requestMapper;
    private Map<String, Comparator<Request>> sort;

    private static final Logger LOGGER = LogManager.getLogger(OrderService.class.getName());

    @Autowired
    public RequestService(IRequestDAO requestDAO, IBookDAO bookDAO, BookMapper bookMapper, MapperForRequest requestMapper) {
        this.requestDAO = requestDAO;
        this.bookDAO = bookDAO;
        this.bookMapper = bookMapper;
        this.requestMapper = requestMapper;
        this.init();
    }

    public void init() {
        this.sort = new HashMap<>();

        this.sort.put("ByCount", Comparator.comparing(Request::getCount));
        this.sort.put("ByName", (Comparator.comparing(o -> o.getRequestedBooks().getName())));

    }

    @Override
    public void createRequest(BookDTO bookDTO) throws ServiceException {  // Create Request by book, or increase count of requests for book, if existed
        try {
            LOGGER.log(Level.INFO, String.format("Create request book : %s ", bookDTO));

            Request existRequest = requestDAO.requestByBookId(bookDTO.getId());

            if (existRequest == null){
                Book book = bookMapper.toEntity(bookDTO);

                existRequest = new Request(book);
                requestDAO.addEntity(existRequest);
                return;
            }
            existRequest.increaseRequestCount();

        } catch (DAOException e) {

            LOGGER.log(Level.WARN, ("Create request failed"));
            throw new ServiceException("Create request operation failed", e);
        }
    }

    public List<RequestDTO> getSortedRequests(String condition) throws ServiceException {
        try {
            LOGGER.log(Level.INFO, String.format("Condition to sort is : %s", condition), condition);

            List<Request> list = this.requestDAO.getAll();

            list.sort(this.sort.get(condition));

            return requestMapper.bunchRequestToRequestDTO(list);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARN, "Request sorting condition is not available", e);
            throw new ServiceException("Request sorting operation failed", e);
        } catch (DAOException e){
            LOGGER.log(Level.WARN, "Request sorting condition is not available", e);
            throw new ServiceException("Request sorting operation failed", e);
        }
    }

    public Long getNumberOfRequestsByBook(UUID uuid) throws ServiceException {
        try {
            Request request = requestDAO.requestByBookId(uuid);


            return (long) request.getCount();
        } catch (DAOException e){
            LOGGER.log(Level.WARN, "Get number of Requested books is not available", e);
            throw new ServiceException("Get number of Requested  failed", e);
        }
    }

    public List<RequestDTO> getAllRequests() throws ServiceException {
        try {
            return requestMapper.bunchRequestToRequestDTO(requestDAO.getAll());
        }catch (DAOException e){
            LOGGER.log(Level.WARN, "Get all Request is not available", e);
            throw new ServiceException("Get all Request failed", e);
        }
    }

    public RequestDTO getRequestByBook(UUID uuid) throws ServiceException {
        try {
            Book book = bookDAO.getEntityById(uuid);
            Request existRequest = requestDAO.requestByBookId(uuid);

            if(existRequest == null){
                return   new RequestDTO(bookMapper.toDto(book));
            }
            return  requestMapper.toDto(existRequest);
        } catch (DAOException e){
            LOGGER.log(Level.WARN, "Get  Request by Book is not available", e);
            throw new ServiceException("Get Request by Book failed", e);
        }
    }

    public RequestDTO closeRequestById(UUID uuid) throws ServiceException, DAOException {
        Request request = requestDAO.getEntityById(uuid);

        request.setRequestOpenClose(false);
        request.setCount(0);
        requestDAO.update(request);

        return requestMapper.toDto(request);
    }

    @Override
    public RequestDTO update(RequestDTO DTO) throws ServiceException {
        try {
            Request request = requestMapper.toEntity(DTO);

            requestDAO.update(request);

            return DTO;
        } catch (DAOException  e){
            throw new ServiceException(e);
        }
    }

/*
    public Request getRequestByBook(UUID uuid) throws ServiceException {
        try {
            List<Request> requests = new ArrayList<>(this.requestDAO.getAll());
            Optional<Request> request = requests.stream().filter(x -> x.getRequestedBooks().getUuid().equals(uuid)).findFirst();

            if (request.isEmpty()) {
                Request newRequest = new Request(bookService.getBookById(uuid));
                requestDAO.addEntity(newRequest);
                return newRequest;
            } else return request.get();

        } catch (DAOException e) {
            LOGGER.log(Level.WARN, "Get request by Book id failed", e);
            throw new ServiceException("Get request by book failed", e);
        } catch (Exception e) {
            LOGGER.log(Level.WARN, "Get request by book failed");
            throw new ServiceException("Get request by book failed", e);

        }

    }
*/
}

