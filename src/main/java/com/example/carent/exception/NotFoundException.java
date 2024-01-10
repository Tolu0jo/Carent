package com.example.carent.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException (String message){
        super(message);
    }
}
