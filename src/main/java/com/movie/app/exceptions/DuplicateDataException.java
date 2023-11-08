package com.movie.app.exceptions;

public class DuplicateDataException extends RuntimeException{
    public DuplicateDataException() {
    }

    public DuplicateDataException(String message) {
        super(message);
    }
}
