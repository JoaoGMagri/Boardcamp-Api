package com.boardcamp.api.exceptions;

public class ExistingCPFException extends RuntimeException{
    public ExistingCPFException(String message) {
        super(message);
    }
}
