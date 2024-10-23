package com.low.level.vendingmachine.service;

import com.low.level.vendingmachine.exception.InsufficientCashInventoryException;
import com.low.level.vendingmachine.exception.ProductNotExistsException;
import com.low.level.vendingmachine.exception.ProductOutOfStockException;
import com.low.level.vendingmachine.model.*;

import java.util.Objects;
import java.util.Scanner;


public class VendingMachineManagerService {
    private static VendingMachineManagerService instance;
    private ProductInventoryService productInventoryService;
    private CashInventoryService cashInventoryService;
    private VendingMachine vendingMachine;

    private VendingMachineManagerService(VendingMachine vendingMachine, ProductInventoryService productInventoryService, CashInventoryService cashInventoryService) {
        this.vendingMachine = vendingMachine;
        this.productInventoryService = productInventoryService;
        this.cashInventoryService = cashInventoryService;
    }

    public static VendingMachineManagerService getInstance(VendingMachine vendingMachine, ProductInventoryService productInventoryService, CashInventoryService cashInventoryService) {
        if (instance == null) {
            instance = new VendingMachineManagerService(vendingMachine, productInventoryService, cashInventoryService);
        }
        return instance;
    }

    public void initializeVendingMachine() {
        productInventoryService.initializeProductInventory();
        cashInventoryService.initializeCashInventory();
    }

    public void showProducts() {
        System.out.println("ProductId\t\t Product Name\t\t Price");
        System.out.println("---------------------------------------");;
        vendingMachine.getProducts().keySet().forEach(product -> {
            System.out.println(product.getId()+"\t\t"+product.getName()+"\t\t"+product.getPrice());
        });
    }

    public void selectProduct(String productName) {
        Product product = productInventoryService.getProductDetails(productName);
        try {
            if(product == null) {
                throw new ProductNotExistsException("The keyed in product doesn't exist. " +
                        "Please choose a product from the list");
            }
            product = productInventoryService.retrieveProductFromInventory(productName);
            vendingMachine.setSelectedProduct(product);
            productInventoryService.updateInventory(productName, OrderType.PURCHASE);
            System.out.println(productName+" locked for purchase. Please pay "+product.getPrice()+" in cash.");
        } catch (ProductOutOfStockException ex) {
            System.out.println("Product "+product.getName()+" is out of stock at present");
        } catch (ProductNotExistsException e) {
            System.out.println(e.getMessage());
            showProducts();
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            selectProduct(name);
        }
    }

    public PaymentStatus acceptCash(Cash cash) {
        PaymentStatus paymentStatus = null;
        Double amountToPay = vendingMachine.getSelectedProduct().getPrice() - vendingMachine.getCashPaid();
        Double currentInputAmount = cash.getTotal();

        if(Objects.nonNull(vendingMachine.getSelectedProduct()) &&
                amountToPay > currentInputAmount) {
            Double diff = amountToPay - currentInputAmount;
            cashInventoryService.updateCashBalance(cash, CashTransferMode.ACCEPT);
            System.out.println(currentInputAmount+" received. Price of the product is "+
                    vendingMachine.getSelectedProduct().getPrice()+". " +
                    "Please pay the remaining "+diff+" in cash.");
            vendingMachine.setCashPaid(vendingMachine.getCashPaid() + currentInputAmount);
            paymentStatus = PaymentStatus.PARTIALLY_PAID;
        } else if(Objects.isNull(vendingMachine.getSelectedProduct())) {
            System.out.println("Please select a product first.");
        } else {
            Double diff = currentInputAmount - amountToPay;
            cashInventoryService.updateCashBalance(cash, CashTransferMode.ACCEPT);
            vendingMachine.setCashToReturn(diff);
            vendingMachine.setCashPaid(0.0);
            paymentStatus = PaymentStatus.COMPLETED;
        }
        return paymentStatus;
    }

    public Product dispenseProduct() {
        Product product = vendingMachine.getSelectedProduct();
        vendingMachine.setSelectedProduct(null);
        System.out.println("Product "+product.getName()+" dispensed.");
        return product;
    }

    public void returnProduct(Product product) {
        productInventoryService.updateInventory(product.getName(), OrderType.RETURN);
        System.out.println("Product "+product.getName()+" returned.");
    }

    public Cash refundAmount(Double amount) {
        Cash cash = cashInventoryService.computeDenominationsForAmount(amount);
        System.out.println("Refund complete. Amount refunded to customer: "+cash);
        return cash;
    }

    public void returnChange() {
        Double cashToReturn = vendingMachine.getCashToReturn();
        System.out.println("Change to be returned to the customer: "+cashToReturn);
        if(cashToReturn > 0.0) {
            if(cashInventoryService.isSufficientCashAvailable(cashToReturn)) {
                Cash cashReturned = cashInventoryService.computeDenominationsForAmount(cashToReturn);
                cashInventoryService.updateCashBalance(cashReturned, CashTransferMode.DISPENSE);
                System.out.println("Change returned to the customer: "+cashReturned.getTotal()
                        +" with the below split up - Coins: "+ cashReturned.getCoins()+
                        " and Rupees: "+cashReturned.getRupees());
            } else {
                throw new InsufficientCashInventoryException("Unable to dispense cash due to " +
                        "insufficient cash in the inventory.");
            }
        }
    }

}
