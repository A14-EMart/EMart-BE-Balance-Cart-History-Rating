package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.models.CartItem;
import com.a14.emart.backendbchr.models.Product;
import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.observer.CartObserver;
import com.a14.emart.backendbchr.repository.CartItemRepository;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public ShoppingCart createShoppingCart(String pembeliId) {
        ShoppingCart shoppingCart = ShoppingCart.getBuilder()
                .setPembeliId(pembeliId)
                .setSupermarketId(null)
                .build();
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCart(String pembeliId) {
        return shoppingCartRepository.findById(pembeliId);
    }

    @Override
    @Transactional
    public ShoppingCart addItemToCart(String pembeliId, String productId, String supermarketId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(pembeliId)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    newCart.setPembeliId(pembeliId);
                    newCart.setSupermaketId(null);
                    return newCart;
                });
        List<CartItem> cartItems = shoppingCart.getItems();

        if (!shoppingCart.getSupermaketId().equals(supermarketId)) {
            throw new RuntimeException("All items in the cart must be from the same supermarket");
        }else if(shoppingCart.getSupermaketId()==null){
            shoppingCart.setSupermaketId(supermarketId);
        }

        Optional<CartItem> existingItem = shoppingCart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

//        if (existingItem.isPresent()) {
//            CartItem cartItem = existingItem.get();
//            cartItem.setAmount(cartItem.getAmount() + 1);
//            cartItemRepository.save(cartItem);
//        } else
        for(CartItem item: cartItems){
            if(item.getProductId() == productId){
                item.setAmount(item.getAmount()+1);
                break;
            }
        }

        if(existingItem.isEmpty()) {
            CartItem cartItem = CartItem.getBuilder()
                    .setPembeliId(pembeliId)
                    .setProductId(productId)
                    .setAmount(1)
                    .build();
            shoppingCart.addItem(cartItem);
            cartItemRepository.save(cartItem);

        }

        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart removeItemFromCart(String pembeliId, String productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(pembeliId)
                .orElseThrow(() -> new RuntimeException("ShoppingCart not found"));

        CartItem cartItem = shoppingCart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        if (cartItem.getAmount() > 1) {
            cartItem.setAmount(cartItem.getAmount() - 1);
            cartItemRepository.save(cartItem);
        } else {
            shoppingCart.removeItem(cartItem);
            cartItemRepository.delete(cartItem);
        }

        return shoppingCartRepository.save(shoppingCart);
    }

}
