package com.low.level.parkinglot.exception;

public class NoSlotAvailableException extends RuntimeException {
    public NoSlotAvailableException(String message) {
        super(message);
    }
}
