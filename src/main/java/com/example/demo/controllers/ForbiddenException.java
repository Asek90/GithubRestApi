package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ForbiddenException(HttpStatus httpStatus,String message) {
        super(message);
        this.status=httpStatus;
        this.message=message;
    }
}
