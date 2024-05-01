package com.a14.emart.backendbchr.model;
import java.util.ArrayList;
import java.util.List;
import com.a14.emart.backendbchr.model.Product;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShoppingCart {
    private List<CartItem> items;
    private String pembeliId;
    private List<CartObserver> observers = new ArrayList<>();

    // Constructor

    public ShoppingCart(List<CartItem> items, String pembeliId) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be empty");
        }
        this.items = items;
        this.pembeliId = pembeliId;
    }

    private void notifyObservers() {
        for (CartObserver observer : observers) {
            observer.update(this);
        }
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                notifyObservers();
                return;
            }
        }
        CartItem newItem = new CartItem(product, quantity);
        items.add(newItem);
        notifyObservers();
    }

    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
        notifyObservers();
    }

    public void addObserver(CartObserver observer) {
        observers.add(observer);
    }

    // Getters
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        for (CartItem item : items) {
            productList.add(item.getProduct());
        }
        return productList;
    }

//    public String getPembeli() {
//        return pembeliId.getName();
//    }
}

