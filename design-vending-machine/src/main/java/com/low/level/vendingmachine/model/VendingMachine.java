package com.low.level.vendingmachine.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class VendingMachine {
    private Map<Product, Integer> products;
    private Cash availableCash;
    private Product selectedProduct;
    private Double cashPaid;
    private Double cashToReturn;

    public VendingMachine() {
        this.products = new HashMap<>();
        this.availableCash = new Cash(new HashMap<>(), new HashMap<>());
        this.selectedProduct = null;
        this.cashToReturn = 0.0;
        this.cashPaid = 0.0;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Cash getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(Cash availableCash) {
        this.availableCash = availableCash;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Double getCashPaid() {
        return cashPaid;
    }

    public void setCashPaid(Double cashPaid) {
        this.cashPaid = cashPaid;
    }

    public Double getCashToReturn() {
        return cashToReturn;
    }

    public void setCashToReturn(Double cashToReturn) {
        this.cashToReturn = cashToReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachine that = (VendingMachine) o;
        return Objects.equals(products, that.products) && Objects.equals(availableCash, that.availableCash) && Objects.equals(selectedProduct, that.selectedProduct) && Objects.equals(cashPaid, that.cashPaid) && Objects.equals(cashToReturn, that.cashToReturn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, availableCash, selectedProduct, cashPaid, cashToReturn);
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "products=" + products +
                ", availableCash=" + availableCash +
                ", selectedProduct=" + selectedProduct +
                ", cashPaid=" + cashPaid +
                ", cashToReturn=" + cashToReturn +
                '}';
    }
}
