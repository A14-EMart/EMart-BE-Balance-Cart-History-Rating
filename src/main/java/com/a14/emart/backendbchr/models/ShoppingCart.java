package com.a14.emart.backendbchr.models;
import com.a14.emart.backendbchr.models.Product;
import com.a14.emart.backendbchr.observer.CartObserver;

import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pembeliId;

    @Setter
    private String supermaketId;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public static ShoppingCartBuilder getBuilder() {
        return new ShoppingCartBuilder();
    }

    public void addItem(CartItem cartItem) {
        cartItem.setShoppingCart(this);
        this.items.add(cartItem);
    }

    // Method to remove an item from the cart
    public void removeItem(CartItem cartItem) {
        cartItem.setShoppingCart(null);
        this.items.remove(cartItem);
    }
}

//    public String getPembeli() {
//        return pembeliId.getName();
//    }
//        }
//public class ShoppingCart {
//    @Transient
//    private List<Product> products = new ArrayList<>();
//
//    @Transient
//    private Map<Product, Integer> productQuantities = new HashMap<>();
//
//    private String pembeliId;
//
//    @Setter
//    private String supermarketId;
//    @Transient
//    private List<CartObserver> observers = new ArrayList<>();
//
//    // Constructor
//
//    public ShoppingCart(Map<Product, Integer> productQuantities, String pembeliId) {
//        if (productQuantities == null || productQuantities.isEmpty()) {
//            throw new IllegalArgumentException("Product list cannot be empty");
//        }
//        this.pembeliId = pembeliId;
//    }
//
//    public ShoppingCart() {
//    }
//
//    private void notifyObservers() {
//        for (CartObserver observer : observers) {
//            observer.update(this);
//        }
//    }
//
//    public void addProduct(Product product, int quantity) {
//        if (productQuantities.containsKey(product)) {
//            productQuantities.put(product, productQuantities.get(product) + quantity);
//        } else {
//            productQuantities.put(product, quantity);
//            products.add(product);
//        }
//        notifyObservers();
//    }
//
//    public void removeProduct(Product product) {
//        if (productQuantities.containsKey(product)) {
//            int newQuantity = productQuantities.get(product) - 1;
//            if (newQuantity > 0) {
//                productQuantities.put(product, newQuantity);
//            } else {
//                productQuantities.remove(product);
//                products.remove(product);
//            }
//        }
//        notifyObservers();
//    }



//    public void removeCart(){
//        return;
//    }
//
//    public void addObserver(CartObserver observer) {
//        observers.add(observer);
//    }
//
//    // Getters
//    public List<Product> getProducts() {
//        return new ArrayList<>(productQuantities.keySet());
//    }
//
//    public int getProductQuantity(Product product) {
//        return productQuantities.get(product);
//    }
//
//}

