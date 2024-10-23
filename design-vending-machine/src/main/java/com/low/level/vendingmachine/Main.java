package com.low.level.vendingmachine;

import com.low.level.vendingmachine.exception.InsufficientCashInventoryException;
import com.low.level.vendingmachine.model.Cash;
import com.low.level.vendingmachine.model.PaymentStatus;
import com.low.level.vendingmachine.model.Product;
import com.low.level.vendingmachine.model.VendingMachine;
import com.low.level.vendingmachine.service.CashInventoryService;
import com.low.level.vendingmachine.service.ProductInventoryService;
import com.low.level.vendingmachine.service.VendingMachineManagerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine();
        ProductInventoryService productInventoryService = ProductInventoryService.getInstance(vendingMachine);
        CashInventoryService cashInventoryService = CashInventoryService.getInstance(vendingMachine);
        VendingMachineManagerService vendingMachineManagerService = VendingMachineManagerService.getInstance(
                vendingMachine, productInventoryService, cashInventoryService);

        // load product inventory and cash inventory
        vendingMachineManagerService.initializeVendingMachine();

        System.out.println("Hi there! What would you like to have today?");
        // list all items and their prices
        vendingMachineManagerService.showProducts();
        System.out.println("Please key in the product you would like to have:");
        Scanner sc = new Scanner(System.in);
        String productName = sc.nextLine();

        // select product
        vendingMachineManagerService.selectProduct(productName);
        Product selectedProduct = vendingMachine.getSelectedProduct();
        System.out.println("Please feel free to insert your cash into the machine now");
        Double cashFedByCustomer = sc.nextDouble();
        Cash cashFed = cashInventoryService.computeDenominationsForAmount(cashFedByCustomer);
        // pay in cash
        PaymentStatus paymentStatus = vendingMachineManagerService.acceptCash(cashFed);
        // prompt for remaining amount if partially paid
        while(paymentStatus==PaymentStatus.PARTIALLY_PAID) {
            System.out.println("Please feed the remaining amount: ");
            Double remainingAmountFed = sc.nextDouble();
            paymentStatus = vendingMachineManagerService.acceptCash(
                    cashInventoryService.computeDenominationsForAmount(remainingAmountFed));
        }

        // dispense product
        Product dispensedProduct = vendingMachineManagerService.dispenseProduct();

        // return change if any
        try {
            vendingMachineManagerService.returnChange();
        } catch (InsufficientCashInventoryException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Would you like to return the product and process the refund? (y/n): ");
            String response = sc.nextLine();

            // return the product in case of insufficient cash inventory and refund amount paid
            if("y".equalsIgnoreCase(response)) {
               vendingMachineManagerService.returnProduct(dispensedProduct);
               vendingMachineManagerService.refundAmount(dispensedProduct.getPrice());
            } else {
                System.out.println("Thanks for being a generous customer!!");
            }
        }
        System.out.println("Thank you, Please do visit again");
    }
}