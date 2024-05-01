package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.model.Product;
import com.a14.emart.backendbchr.model.ShoppingCart;
import com.a14.emart.backendbchr.observer.CartObserver;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Transactional
    public void addProductToCart(Long cartId, Product product, int quantity) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping Cart not found with ID: " + cartId));
        cart.addProduct(product, quantity);
        shoppingCartRepository.save(cart);
    }


    @Transactional
    public void removeProductFromCart(Long cartId, Product product) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping Cart not found with ID: " + cartId));
        cart.removeProduct(product);
        shoppingCartRepository.save(cart);
    }


    @Transactional(readOnly = true)
    public Optional<ShoppingCart> getShoppingCartById(Long cartId) {
        return shoppingCartRepository.findById(cartId);
    }


    @Transactional
    public void addObserverToCart(Long cartId, CartObserver observer) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping Cart not found with ID: " + cartId));
        cart.addObserver(observer);
        shoppingCartRepository.save(cart);
    }
}
