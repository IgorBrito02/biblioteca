package com.igor.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExemplarIndisponivelException extends RuntimeException {

    public ExemplarIndisponivelException(String message) {
        super(message);
    }
}