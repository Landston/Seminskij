package com.senla.api.exception.DI;

public class ConfigurationError extends RuntimeException{

    public ConfigurationError(String mes){
        super(mes);
    }
    public ConfigurationError(){
        super();
    }
    public ConfigurationError(String mes, Throwable ex){
        super(mes, ex);
    }
}
