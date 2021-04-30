package com.senla.model.configuration;

import com.senla.di.annotation.Auttowared;
import com.senla.di.annotation.Configuration;
import com.senla.model.api.dao.IBookDAO;
import com.senla.model.api.dao.IClientDAO;
import com.senla.model.api.dao.IOrderDAO;
import com.senla.model.api.dao.IRequestDAO;
import com.senla.model.api.service.IBookService;
import com.senla.model.api.service.IClientService;
import com.senla.model.api.service.IOrderService;
import com.senla.model.api.service.IRequestService;
import com.senla.model.dao.RequestDAO;
import com.senla.model.facade.BookShopFacade;

@Configuration
public class ServerConfig {

    @Auttowared
    private IOrderDAO orderDAO;
    @Auttowared
    private IBookDAO bookDAO;
    @Auttowared
    private IBookService bookService;
    @Auttowared
    private IClientDAO clientDAO;
    @Auttowared
    private IClientService clientService;
    @Auttowared
    private IRequestDAO requestDAO;
    @Auttowared
    private IRequestService requestService;
    @Auttowared
    private IOrderService orderService;
    @Auttowared
    private BookShopFacade bookShopFacade;


}
