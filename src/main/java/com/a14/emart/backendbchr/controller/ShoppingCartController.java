package com.a14.emart.backendbchr.controller;

import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // Endpoint to create a new shopping cart
    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart newCart = shoppingCartService.createShoppingCart(shoppingCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCart);
    }
}
