package com.low.level.vendingmachine.service;

import com.low.level.vendingmachine.model.Cash;
import com.low.level.vendingmachine.model.CashTransferMode;
import com.low.level.vendingmachine.model.VendingMachine;

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

    public void loadCash(Cash cash) {
        vendingMachine.setCash(cash);
        System.out.println("Cash loaded to the vending machine: "+cash.getTotal());
    }

    public void updateCashBalance(Cash cash, CashTransferMode cashTransferMode) {
       if(CashTransferMode.ACCEPT == cashTransferMode) {
           // increment balance
       } else if(CashTransferMode.DISPENSE == cashTransferMode) {
           // decrement balance
       }
    }

    public Cash getCashBalance() {
       return vendingMachine.getCash();
    }
}
