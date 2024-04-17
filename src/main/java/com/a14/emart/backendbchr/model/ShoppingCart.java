package com.a14.emart.backendbchr.model;
import java.util.List;
public class ShoppingCart {
    private List<Product> products;
    private String pembeli;

    // Constructor
    public ShoppingCart(List<Product> products, String pembeli) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be empty");
        }
        this.products = products;
        this.pembeli = pembeli;
    }

    // Getters
    public List<Product> getProducts() {
        return products;
    }

    public String getPembeli() {
        return pembeli;
    }
}

