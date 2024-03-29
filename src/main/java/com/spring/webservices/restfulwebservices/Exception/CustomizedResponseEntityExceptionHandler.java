package com.spring.webservices.restfulwebservices.Exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object>handleAllException(Exception ex, WebRequest request)
    {
        ResponseException responseException = new ResponseException(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object>handleUserNotFoundException(Exception ex, WebRequest request)
    {
        ResponseException responseException = new ResponseException(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseException responseException = new ResponseException(new Date(), ex.getMessage(), "Validation failed");
        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }
}
