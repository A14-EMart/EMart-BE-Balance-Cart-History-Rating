package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.model.Product;
import com.a14.emart.backendbchr.model.ShoppingCart;
import com.a14.emart.backendbchr.observer.CartObserver;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart createShoppingCart(String pembeliId) {
        return new ShoppingCart(new HashMap<>(), pembeliId);
    }

    public void addProduct(ShoppingCart cart, Product product, int quantity) {
        if (cart != null && product != null) {
            cart.addProduct(product, quantity);
        }
    }

    public void removeProduct(ShoppingCart cart, Product product) {
        if (cart != null && product != null) {
            cart.removeProduct(product);
        }
    }

    public void addObserver(ShoppingCart cart, CartObserver observer) {
        if (cart != null && observer != null) {
            cart.addObserver(observer);
        }
    }
}
