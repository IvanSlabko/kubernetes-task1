package com.slabko.task1.postservice.exceptions;

public class RequestedDataNotFoundException extends RuntimeException {
    public RequestedDataNotFoundException(String message) {
        super(message);
    }
}
