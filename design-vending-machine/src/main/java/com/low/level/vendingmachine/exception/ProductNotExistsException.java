package com.low.level.vendingmachine.exception;

public class ProductNotExistsException extends RuntimeException{
    public ProductNotExistsException(String message) {
        super(message);
    }
}
