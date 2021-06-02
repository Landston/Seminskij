package com.senla.start.mvc.config;

import com.senla.api.exception.service.DAOException;
import com.senla.api.exception.service.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.security.Provider;
import java.util.ResourceBundle;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> serviceExceptionHandler(ServiceException ex){
        ResponseEntity<String > stringResponseEntity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

        log.warn(ex);
        
        return stringResponseEntity;
    }

    @ExceptionHandler
    public ResponseEntity<String> daoExceptionHandler(DAOException ex){
        log.warn(ex);
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
