package com.senla.di.exception;


public class FATALERROR extends RuntimeException{

    public FATALERROR(String message){
        super(message);
    }
    public FATALERROR(String message, Throwable stack){
        super(message);
    }
}
