package com.a14.emart.backendbchr.service;
import com.a14.emart.backendbchr.models.Product;
import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.observer.CartObserver;

import java.util.HashMap;
import java.util.Optional;

public interface ShoppingCartService {
    public ShoppingCart createShoppingCart(String pembeliId);
    public Optional<ShoppingCart> getShoppingCart(String pembeliId);
    public ShoppingCart addItemToCart(String pembeliId, String productId, String supermarketId);
    public ShoppingCart removeItemFromCart(String pembeliId, String productId);
//    ShoppingCart clearShoppingCart(ShoppingCart shoppingCart);
//    public void checkoutKeranjang(HashMap<String, Integer> productQuantities);



}
