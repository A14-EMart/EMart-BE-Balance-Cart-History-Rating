package com.a14.emart.backendbchr.observer;

import com.a14.emart.backendbchr.models.ShoppingCart;

public interface CartObserver {
    void update(ShoppingCart shoppingCart);
}