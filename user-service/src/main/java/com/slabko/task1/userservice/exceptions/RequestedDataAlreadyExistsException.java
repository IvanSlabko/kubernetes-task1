package com.slabko.task1.userservice.exceptions;

public class RequestedDataAlreadyExistsException extends RuntimeException {
    public RequestedDataAlreadyExistsException(String message) {
        super(message);
    }
}
