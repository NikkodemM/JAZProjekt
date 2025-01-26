package com.example.JazProjekt.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not found the cat with id "+ id);
    }
}
