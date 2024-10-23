package com.low.level.vendingmachine.model;

import java.util.List;
import java.util.Map;

public class Cash {
    private Map<Coin, Integer> coins;
    private Map<Rupee, Integer> rupees;

    public Cash(Map<Coin, Integer> coins, Map<Rupee, Integer> rupees) {
        this.coins = coins;
        this.rupees = rupees;
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }

    public Map<Rupee, Integer> getRupees() {
        return rupees;
    }

    public void setCoins(Map<Coin, Integer> coins) {
        this.coins = coins;
    }

    public void setRupees(Map<Rupee, Integer> rupees) {
        this.rupees = rupees;
    }

    public Double getTotal() {
        Double amount = Double.valueOf(0.0);

        if(!coins.isEmpty()) {
            for(Coin coin : coins.keySet()) {
                amount += coin.getValue() * coins.get(coin);
            }
        }

        if(!rupees.isEmpty()) {
            for(Rupee rupee : rupees.keySet()) {
                amount += rupee.getValue() * rupees.get(rupee);
            }
        }
        return amount;
    }
}
