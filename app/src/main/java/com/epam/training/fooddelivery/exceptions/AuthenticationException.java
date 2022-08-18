package com.epam.training.fooddelivery.exceptions;


public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message){
        super(message);
    }
}
