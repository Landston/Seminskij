package com.senla.dao;

public class Constants {
    ////////////////////////////////////////ORDER_SPECIFICATION////////////////////////////////////////////////////////
    public static final String ORDER_SPECIFICATION_TABLE = "order_specification";
    public static final String ORDER_SPECIFICATION_ID = "order_specification_id";
    public static final String ORDER_SPECIFICATION_ORDER_ID = "order_id";
    public static final String ORDER_SPECIFICATION_BOOK_ID = "book_id";
    ////////////////////////////////////////ORDERS/////////////////////////////////////////////////////////////////////
    public static final String ORDERS_TABLE = "orders";
    public static final String ORDERS_ID = "order_id";
    public static final String ORDERS_TOTAL_PRICE = "total_price";
    public static final String ORDERS_DATE = "date";
    public static final String ORDERS_ORDER_STATUS = "order_status";
    public static final String ORDERS_ORDER_CLIENT_ID = "client_id";
    ////////////////////////////////////////ORDER_STATUS///////////////////////////////////////////////////////////////
    public static final String ORDER_STATUS_TABLE = "order_status";
    public static final String ORDER_STATUS_ORDER_STATUS = "order_status";
    ////////////////////////////////////////CLIENTS////////////////////////////////////////////////////////////////////
    public static final String CLIENTS_TABLE = "clients";
    public static final String CLIENTS_CLIENT_ID = "client_id";
    public static final String CLIENTS_CLIENT_NAME = "client_name";
    public static final String CLIENTS_CLIENT_EMAIL = "client_email";
    ////////////////////////////////////////BOOK_STATUS////////////////////////////////////////////////////////////////
    public static final String BOOK_STATUS_TABLE = "book_status";
    public static final String BOOK_STATUS_STATUS = "status";
    ////////////////////////////////////////BOOKS//////////////////////////////////////////////////////////////////////////
    public static final String BOOKS_TABLE = "books";
    public static final String BOOKS_BOOK_ID = "book_id";
    public static final String BOOKS_GENRE =  "genre";
    public static final String BOOKS_NAME =   "name";
    public static final String BOOKS_YEAR =   "year";
    public static final String BOOKS_COST =   "cost";
    public static final String BOOKS_DATE_OF_ADMISSION = "date_of_admission";
    public static final String BOOKS_BOOK_STATUS = "book_status";
////////////////////////////////////////REQUESTS///////////////////////////////////////////////////////////////////////
    public static final String REQUESTS_TABLE = "requests";
    public static final String REQUESTS_REQUEST_ID = "request_id";
    public static final String REQUESTS_BOOK_ID = "book_id";
    public static final String REQUESTS_COUNT = "count";
    public static final String REQUESTS_OPEN_CLOSE = "open_close";
}
