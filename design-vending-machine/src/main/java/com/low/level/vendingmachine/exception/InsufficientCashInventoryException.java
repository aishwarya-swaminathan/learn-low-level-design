package com.low.level.vendingmachine.exception;

public class InsufficientCashInventoryException extends RuntimeException{
    public InsufficientCashInventoryException(String message) {
        super(message);
    }
}
