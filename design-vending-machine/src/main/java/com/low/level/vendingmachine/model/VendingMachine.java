package com.low.level.vendingmachine.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VendingMachine {
    private Map<Product, Integer> products;
    private Cash cash;

    public VendingMachine() {
        this.products = new HashMap<>();
        this.cash = new Cash(new HashMap<>(), new HashMap<>());
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachine that = (VendingMachine) o;
        return Objects.equals(products, that.products) && Objects.equals(cash, that.cash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, cash);
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "products=" + products +
                ", cash=" + cash +
                '}';
    }
}
