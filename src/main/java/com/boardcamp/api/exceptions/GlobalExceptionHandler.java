package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({ ExceptionConflict.class })
    public ResponseEntity<Object> handleConflict(ExceptionConflict exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({ ExceptionUnprocessableEntity.class })
    public ResponseEntity<Object> handleUnprocessableEntity(ExceptionUnprocessableEntity exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ExceptionHandler({ ExceptionNotFound.class })
    public ResponseEntity<Object> handleNotFound(ExceptionNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
