package com.senla.model.exceptions;

public class ServiceException extends  Exception{

        public ServiceException(String message){ super(message);}

        public ServiceException(String message, Throwable stack){ super(message, stack);}

        public ServiceException(Throwable stack){ super(stack);}


}
