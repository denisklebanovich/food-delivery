package com.epam.training.fooddelivery.controllers;

import com.epam.training.fooddelivery.service.LowBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> emptyCart() {
        return new ResponseEntity<>("Cart can't be empty", HttpStatus.valueOf(400));
    }

    @ExceptionHandler(LowBalanceException.class)
    public ResponseEntity<String> balanceNotEnough() {
        return new ResponseEntity<>("Not enough balance", HttpStatus.valueOf(400));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> orderNotFound() {
        return new ResponseEntity<>("Order isn't found", HttpStatus.valueOf(404));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> orderNotBelongToCustomer(){
        return new ResponseEntity<>("Order doesn't belong to you", HttpStatus.valueOf(403));
    }

}
