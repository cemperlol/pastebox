package com.example.demo.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String e) {
        super(e);
    }
}
