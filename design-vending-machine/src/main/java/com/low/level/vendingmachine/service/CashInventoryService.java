package com.low.level.vendingmachine.service;

import com.low.level.vendingmachine.exception.InsufficientCashInventoryException;
import com.low.level.vendingmachine.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CashInventoryService {
    private static CashInventoryService instance;
    private VendingMachine vendingMachine;

    private CashInventoryService(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public static CashInventoryService getInstance(VendingMachine vendingMachine) {
        if (instance == null) {
            instance = new CashInventoryService(vendingMachine);
        }
        return instance;
    }

    public void initializeCashInventory() {
        Map<Coin, Integer> coinsLoaded = new TreeMap<>();
        Map<Rupee, Integer> rupeesLoaded = new TreeMap<>();
        coinsLoaded.put(Coin.ONE, 20);
        coinsLoaded.put(Coin.TWO, 20);
        coinsLoaded.put(Coin.FIVE, 20);
        coinsLoaded.put(Coin.TEN, 20);

        rupeesLoaded.put(Rupee.FIFTY, 20);
        rupeesLoaded.put(Rupee.HUNDRED, 20);
        rupeesLoaded.put(Rupee.FIVE_HUNDRED, 20);
        vendingMachine.setAvailableCash(new Cash(coinsLoaded, rupeesLoaded));
    }

    public void loadCash(Cash cash) {
        vendingMachine.setAvailableCash(cash);
        System.out.println("Cash loaded to the vending machine: "+cash.getTotal());
    }

    public boolean isSufficientCashAvailable(Double cashToDispense) {
        return vendingMachine.getAvailableCash().getTotal() >= cashToDispense;
    }

    public void updateCashBalance(Cash recentCashTransaction, CashTransferMode cashTransferMode) {
        Cash currentCashBalance = vendingMachine.getAvailableCash();
        Map<Coin, Integer> coinBalance = currentCashBalance.getCoins();
        Map<Rupee, Integer> rupeeBalance = currentCashBalance.getRupees();

       if(CashTransferMode.ACCEPT == cashTransferMode) {
           // increment balance
           recentCashTransaction.getCoins().keySet().forEach(coin -> {
               coinBalance.put(coin, currentCashBalance.getCoins().getOrDefault(coin, 0)
                       + recentCashTransaction.getCoins().get(coin));
           });

           recentCashTransaction.getRupees().keySet().forEach(rupee -> {
               rupeeBalance.put(rupee, rupeeBalance.getOrDefault(rupee, 0)
                       + recentCashTransaction.getRupees().get(rupee));
           });
           currentCashBalance.setCoins(coinBalance);
           currentCashBalance.setRupees(rupeeBalance);
           vendingMachine.setAvailableCash(currentCashBalance);

       } else if(CashTransferMode.DISPENSE == cashTransferMode) {
           // decrement balance

           if(isSufficientCashAvailable(recentCashTransaction.getTotal())) {
               recentCashTransaction.getCoins().keySet().forEach(coin -> {
                   coinBalance.put(coin, currentCashBalance.getCoins().getOrDefault(coin, 0)
                           - recentCashTransaction.getCoins().get(coin));
               });

               recentCashTransaction.getRupees().keySet().forEach(rupee -> {
                   rupeeBalance.put(rupee, rupeeBalance.getOrDefault(rupee, 0)
                           - recentCashTransaction.getRupees().get(rupee));
               });
               currentCashBalance.setCoins(coinBalance);
               currentCashBalance.setRupees(rupeeBalance);
               vendingMachine.setAvailableCash(currentCashBalance);
           }

       }
//        System.out.println("Updated cash balance in the cash inventory. Current Balance: "+currentCashBalance.getTotal());
    }

    public Cash getCashBalance() {
       return vendingMachine.getAvailableCash();
    }

    public Cash computeDenominationsForAmount(Double cashToDispense) {
        Map<Coin, Integer> coins = new HashMap<>();
        Map<Rupee, Integer> rupees = new HashMap<>();
        Integer cashRemaining = cashToDispense.intValue();
        int count = 0;

        for(Rupee rupee : Rupee.values()) {
            if(cashRemaining > 0 && cashRemaining >= rupee.getValue()) {
                count = cashRemaining / rupee.getValue().intValue();
                rupees.put(rupee, count);
                cashRemaining = cashRemaining % rupee.getValue().intValue();
            }
        }

        if(cashRemaining > 0) {
            for(Coin coin : Coin.values()) {
                if(cashRemaining > 0 && cashRemaining >= coin.getValue()) {
                    count = cashRemaining / coin.getValue().intValue();
                    coins.put(coin, count);
                    cashRemaining = cashRemaining % coin.getValue().intValue();
                }
            }
        }

        if(cashRemaining > 0) {
            throw new InsufficientCashInventoryException("Unable to return change " +
                    "due to insufficient cash in inventory.");
        }

        return new Cash(coins, rupees);
    }
}
