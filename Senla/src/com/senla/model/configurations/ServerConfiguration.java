package com.senla.model.configurations;


import com.senla.model.api.dao.IBookDAO;
import com.senla.model.api.dao.IClientDAO;
import com.senla.model.api.dao.IOrderDAO;
import com.senla.model.api.dao.IRequestDAO;
import com.senla.model.api.service.IBookService;
import com.senla.model.api.service.IClientService;
import com.senla.model.api.service.IOrderService;
import com.senla.model.api.service.IRequestService;



public class ServerConfiguration {


    private IBookDAO bookDAO;

    private IClientDAO clientDAO;

    private IOrderDAO orderDAO;

    private IRequestDAO requestDAO;

    private IBookService bookService;

    private IClientService clientService;

    private IOrderService orderService;

    private IRequestService requestService;

    
}
