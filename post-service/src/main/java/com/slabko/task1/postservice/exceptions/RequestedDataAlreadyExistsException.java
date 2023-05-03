package com.slabko.task1.postservice.exceptions;

public class RequestedDataAlreadyExistsException extends RuntimeException {
    public RequestedDataAlreadyExistsException(String message) {
        super(message);
    }
}
