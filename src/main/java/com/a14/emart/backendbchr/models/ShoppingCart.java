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
