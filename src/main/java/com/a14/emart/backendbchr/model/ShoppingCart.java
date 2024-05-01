package com.a14.emart.backendbchr.model;
import com.a14.emart.backendbchr.observer.CartObserver;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    private String pembeliId;

    @Transient
    private List<CartObserver> observers = new ArrayList<>();

    // Constructor

    public ShoppingCart(List<CartItem> items, String pembeliId) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be empty");
        }
        this.items = items;
        this.pembeliId = pembeliId;
    }

    public ShoppingCart() {
    }

    private void notifyObservers() {
        for (CartObserver observer : observers) {
            observer.update(this);
        }
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity - 1);
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

}

