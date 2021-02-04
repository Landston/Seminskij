package main.Task3.Fourth.Services;

import main.Task3.Fourth.Models.Book;
import main.Task3.Fourth.Models.BookStatus;
import main.Task3.Fourth.Models.Request;

public class Service {

    private OrderService orderService;
    private RequestService requestService;
    private BookService bookService;

    public void orderBook(Book book, String client){

        if(BookStatus.ABSENT.equals(book.getStatus())){

            requestService.createRequest(book);
            System.out.println("Sorry, this is book is unreacheable now, we have created Request for it and it will come soon");
        }
        else{
            // Если клиент иммет открытый заказ, добавляем в его заказ новую книгу.
            //



        }




    }

}
