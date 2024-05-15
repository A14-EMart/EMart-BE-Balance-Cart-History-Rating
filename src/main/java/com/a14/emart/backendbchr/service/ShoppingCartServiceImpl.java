package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.model.Product;
import com.a14.emart.backendbchr.model.ShoppingCart;
import com.a14.emart.backendbchr.observer.CartObserver;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCart;
    }

    @Override
    public void addProduct(Product product, int quantity, String pembeliId) {
        Map<Product, Integer> productQuantities = new HashMap<>();
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(product, quantity);
        shoppingCartRepository.save(cart);
    }

    @Override
    public void removeProductCart(Product product, String pembeliId) {
        ShoppingCart cart = shoppingCartRepository.findById(pembeliId).orElse(null);
        if (cart != null) {
            cart.removeProduct(product);
            shoppingCartRepository.save(cart);
        }
    }

    @Override
    public void checkoutKeranjang(HashMap<String, Integer> productQuantities) {
        List list_produk = (List) productQuantities.keySet();
        Product product;

    }

    @Override
    public void addObserver(String pembeliId, CartObserver observer) {
        ShoppingCart cart = shoppingCartRepository.findById(pembeliId).orElse(null);
        if (cart != null) {
            cart.addObserver(observer);
            shoppingCartRepository.save(cart);
        }
    }

    @Override
    public ShoppingCart clearShoppingCart(ShoppingCart shoppingCart) {
        return null;
    }
}
