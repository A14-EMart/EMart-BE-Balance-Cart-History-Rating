package com.a14.emart.backendbchr.service;
import com.a14.emart.backendbchr.models.Product;
import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.observer.CartObserver;

import java.util.HashMap;
import java.util.Optional;

public interface ShoppingCartService {
    ShoppingCart createShoppingCart(ShoppingCart shoppingCart);
    void addProduct(Product product, int quantity, String pembeliId);
    public void removeProductCart(Product product, String pembeliId);
    public void addObserver(String pembeliId, CartObserver observer);
    ShoppingCart clearShoppingCart(ShoppingCart shoppingCart);
    public void checkoutKeranjang(HashMap<String, Integer> productQuantities);

   Optional<ShoppingCart> findById(String pembeliId);

}
