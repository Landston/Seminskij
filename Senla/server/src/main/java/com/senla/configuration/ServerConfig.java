package com.senla.configuration;

import com.senla.api.dao.IBookDAO;
import com.senla.api.dao.IClientDAO;
import com.senla.api.dao.IOrderDAO;
import com.senla.api.dao.IRequestDAO;
import com.senla.api.service.IBookService;
import com.senla.api.service.IClientService;
import com.senla.api.service.IOrderService;
import com.senla.api.service.IRequestService;

import com.senla.facade.BookShopFacade;


public class ServerConfig {


    private IOrderDAO orderDAO;

    private IBookDAO bookDAO;

    private IBookService bookService;

    private IClientDAO clientDAO;

    private IClientService clientService;

    private IRequestDAO requestDAO;

    private IRequestService requestService;

    private IOrderService orderService;

    private BookShopFacade bookShopFacade;


}
