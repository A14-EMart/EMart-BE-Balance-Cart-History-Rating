package com.a14.emart.backendbchr.models;
import com.a14.emart.backendbchr.models.Product;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity
@Table(name = "shoppingCart")
public class ShoppingCart {
    @Id
    private Long pembeliId;

    @Setter
    private String supermaketId;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"shoppingCart", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @JsonManagedReference
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