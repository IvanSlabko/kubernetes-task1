package com.slabko.task1.userservice.exceptions;

public class RequestedDataNotFoundException extends RuntimeException {
    public RequestedDataNotFoundException(String message) {
        super(message);
    }
}
