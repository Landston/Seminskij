package com.Models.exceptions;

public class DAOException extends Exception {

        public DAOException(String message){ super(message);}

        public DAOException(String message, Throwable stack){ super(message, stack);}

        public DAOException(Throwable stack){ super(stack);}

}
