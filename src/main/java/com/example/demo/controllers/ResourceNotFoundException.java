package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private HttpStatus status;
    private String message;
    public ResourceNotFoundException(HttpStatus httpStatus,String message) {
        super(message);
        this.status=httpStatus;
        this.message=message;
    }
}