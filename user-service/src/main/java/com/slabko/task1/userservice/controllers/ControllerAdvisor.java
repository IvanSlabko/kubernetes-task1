package com.slabko.task1.userservice.controllers;

import com.slabko.task1.userservice.exceptions.RequestedDataAlreadyExistsException;
import com.slabko.task1.userservice.exceptions.RequestedDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestedDataAlreadyExistsException.class)
    protected ResponseEntity<Object> handleRequestedDataAlreadyExistsException(RequestedDataAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestedDataNotFoundException.class)
    protected ResponseEntity<Object> handleRequestedDataNotFoundException(RequestedDataNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
