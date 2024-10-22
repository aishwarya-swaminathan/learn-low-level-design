package com.low.level.vendingmachine.service;

import com.low.level.vendingmachine.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductInventoryService {

    private static ProductInventoryService instance;
    private Map<String, Product> products; // this map is needed to lookup products by product Id in O(1) time
    private Map<Product, Integer> productInventory;

    private ProductInventoryService() {
        this.products = new HashMap<>();
        this.productInventory = new HashMap<>();
    }

    public static ProductInventoryService getInstance() {
        if (instance == null) {
            instance = new ProductInventoryService();
        }
        return instance;
    }

    public void addProduct(Product product, Integer quantity) {
        products.put(product.getId(), product);
        productInventory.put(product, quantity);
    }

    public void updateInventory(String productId) {
        Product product = null;
       if(products.containsKey(productId)) {
           product = products.get(productId);
           productInventory.put(product, productInventory.get(product) - 1);
       }
    }

    public Product getProduct(String productId) {
        return products.getOrDefault(productId, null);
    }
}
