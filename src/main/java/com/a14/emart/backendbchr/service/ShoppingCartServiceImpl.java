package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.DTO.GetProductResponse;
import com.a14.emart.backendbchr.models.CartItem;
import com.a14.emart.backendbchr.models.Product;
import com.a14.emart.backendbchr.models.ShoppingCart;

import com.a14.emart.backendbchr.repository.CartItemRepository;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;
import com.a14.emart.backendbchr.rest.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    public ShoppingCart createShoppingCart(Long pembeliId) {
        ShoppingCart shoppingCart = ShoppingCart.getBuilder()
                .setPembeliId(pembeliId)
                .setSupermarketId(null)
                .build();
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCart(Long pembeliId) {
        return shoppingCartRepository.findShoppingCartByPembeliId(pembeliId);
    }

    @Override
    @Transactional
    public ShoppingCart addItemToCart(Long pembeliId, String productId, String supermarketId) {
        // Retrieve or create the shopping cart
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByPembeliId(pembeliId)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart();
                    newCart.setPembeliId(pembeliId);
                    newCart.setSupermaketId(supermarketId); // Set the supermarketId for the new cart
                    return shoppingCartRepository.save(newCart); // Save and return the new cart
                });

        // If the cart exists, ensure items are from the same supermarket
        if (shoppingCart.getSupermaketId() != null && !shoppingCart.getSupermaketId().equals(supermarketId)) {
            throw new RuntimeException("All items in the cart must be from the same supermarket");
        }

        // Set the supermarketId if it's a new cart
        if (shoppingCart.getSupermaketId() == null) {
            shoppingCart.setSupermaketId(supermarketId);
        }

        // Fetch product details and check stock
        GetProductResponse productResponse = productService.getProductById(UUID.fromString(productId));
        if (productResponse == null) {
            throw new IllegalArgumentException("Product not found");
        }

        Integer stock = productResponse.getStock();
        if (stock == 0) {
            throw new IllegalArgumentException("Product is out of stock");
        }

        // Find the existing item in the cart, if any
        Optional<CartItem> existingItem = shoppingCart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            // If the item exists, update the amount
            CartItem cartItem = existingItem.get();
            cartItem.setAmount(cartItem.getAmount() + 1);
        } else {
            // If the item does not exist, create a new CartItem
            CartItem newItem = new CartItem();
            newItem.setPembeliId(pembeliId);
            newItem.setProductId(productId);
            newItem.setAmount(1);
            newItem.setShoppingCart(shoppingCart);
            // Set the shopping cart reference
            shoppingCart.getItems().add(newItem); // Add the new item to the cart
        }

        // Save the updated cart
        return shoppingCartRepository.save(shoppingCart);
    }



    @Override
    @Transactional
    public ShoppingCart removeItemFromCart(Long pembeliId, String productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByPembeliId(pembeliId)
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