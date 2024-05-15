package com.a14.emart.backendbchr.observer;

import org.springframework.stereotype.Component;
import com.a14.emart.backendbchr.models.ShoppingCart;

@Component
public class CartNotificationObserver implements CartObserver {
    public void update(ShoppingCart shoppingCart) {
        System.out.println("Cart has been updated: " + shoppingCart);
        // More complex notification logic can be added here
    }
}
