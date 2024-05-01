package com.a14.emart.backendbchr.observer;

import com.a14.emart.backendbchr.model.ShoppingCart;

public interface CartObserver {
    void update(ShoppingCart shoppingCart);
}