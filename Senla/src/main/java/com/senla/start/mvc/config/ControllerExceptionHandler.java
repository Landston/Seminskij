package com.senla.start.mvc.config;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.security.Provider;
import java.util.ResourceBundle;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> serviceExceptionHandler(ServiceException ex){
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<String> daoExceptionHandler(DAOException ex){
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
