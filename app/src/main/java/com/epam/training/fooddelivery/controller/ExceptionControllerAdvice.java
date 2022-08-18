package com.epam.training.fooddelivery.controller;

import com.epam.training.fooddelivery.exceptions.LowBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> emptyCart(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(400));
    }

    @ExceptionHandler(LowBalanceException.class)
    public ResponseEntity<String> balanceNotEnough(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(400));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> orderNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(404));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> orderNotBelongToCustomer(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(403));
    }

}
