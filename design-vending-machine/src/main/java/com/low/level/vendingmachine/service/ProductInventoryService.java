package com.low.level.vendingmachine.service;

import com.low.level.vendingmachine.exception.ProductOutOfStockException;
import com.low.level.vendingmachine.model.OrderType;
import com.low.level.vendingmachine.model.Product;
import com.low.level.vendingmachine.model.VendingMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductInventoryService {

    private static ProductInventoryService instance;
    private Map<String, Product> products; // this map is needed to lookup products by product Id in O(1) time
    private Map<Product, Integer> productInventory;
    private VendingMachine vendingMachine;

    private ProductInventoryService(VendingMachine vendingMachine) {
        this.products = new HashMap<>();
        this.productInventory = new HashMap<>();
        this.vendingMachine = vendingMachine;
    }

    public static ProductInventoryService getInstance(VendingMachine vendingMachine) {
        if (instance == null) {
            instance = new ProductInventoryService(vendingMachine);
        }
        return instance;
    }

    public void initializeProductInventory() {
        List<Product> items = List.of(new Product("Lays Chips", 20.0),
        new Product("Uncle Chips", 25.0),
        new Product("Coke Can", 5.0),
        new Product("Kit Kat", 10.0),
        new Product("Haldirams Mixture", 50.0),
        new Product("DairyMilk Silk", 45.0),
        new Product("Pepsi 500ml", 50.0));

        for(Product product : items) {
            products.put(product.getName(), product);
            productInventory.put(product, 5);
        }
        vendingMachine.setProducts(productInventory);
    }

    public void addProduct(Product product, Integer quantity) {
        products.put(product.getId(), product);
        productInventory.put(product, quantity);
    }

    public void updateInventory(String productName, OrderType orderType) {
        Product product = null;
       if(products.containsKey(productName)) {
           product = products.get(productName);
           if(OrderType.PURCHASE == orderType) {
               productInventory.put(product, productInventory.get(product) - 1);
           } else if(OrderType.RETURN == orderType) {
               productInventory.put(product, productInventory.get(product) + 1);
           }
       }
    }

    public Product getProductDetails(String productName) {
        return products.getOrDefault(productName, null);
    }

    public Product retrieveProductFromInventory(String productName) {
        Product product = products.getOrDefault(productName, null);
        return Optional.of(product)
                .filter(item -> productInventory.containsKey(item) && productInventory.get(item) > 0)
                .orElseThrow(() -> new ProductOutOfStockException("Product is out of stock"));
    }
}
