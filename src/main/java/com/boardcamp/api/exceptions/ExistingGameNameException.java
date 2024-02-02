package com.boardcamp.api.exceptions;

public class ExistingGameNameException extends RuntimeException{
    public ExistingGameNameException(String message) {
        super(message);
    }
}
