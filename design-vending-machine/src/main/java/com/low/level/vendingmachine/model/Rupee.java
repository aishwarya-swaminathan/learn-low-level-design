package com.low.level.vendingmachine.model;

public enum Rupee {
    FIFTY(50.0),
    HUNDRED(100.0),
    FIVE_HUNDRED(500.0);

    private final Double value;

    Rupee(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
