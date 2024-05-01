package com.a14.emart.backendbchr.service;
import com.a14.emart.backendbchr.model.Product;
import com.a14.emart.backendbchr.model.ShoppingCart;
import com.a14.emart.backendbchr.observer.CartObserver;

import java.util.Optional;

public interface ShoppingCartService {
    ShoppingCart addProductToCart(Long cartId, Product product, int quantity);
    public void removeProductFromCart(Long cartId, Product product);
    public Optional<ShoppingCart> getShoppingCartById(Long cartId);
    public void addObserverToCart(Long cartId, CartObserver observer);

}