package com.low.level.vendingmachine.model;

public enum Coin {
    TEN(10.0),
    FIVE(5.0),
    TWO(2.0),
    ONE(1.0);

    private final Double value;

    Coin(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

}
