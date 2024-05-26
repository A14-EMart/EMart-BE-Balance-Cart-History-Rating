package com.a14.emart.backendbchr.service;
import com.a14.emart.backendbchr.models.ShoppingCart;


import java.util.HashMap;
import java.util.Optional;

public interface ShoppingCartService {
    public ShoppingCart createShoppingCart(Long pembeliId);
    public Optional<ShoppingCart> getShoppingCart(Long pembeliId);
    public ShoppingCart addItemToCart(Long pembeliId, String productId, String supermarketId);
    public ShoppingCart removeItemFromCart(Long pembeliId, String productId);
    public double calculateTotalPrice(Long pembeliId);
}