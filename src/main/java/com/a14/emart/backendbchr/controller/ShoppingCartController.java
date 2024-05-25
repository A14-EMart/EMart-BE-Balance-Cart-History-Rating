package com.a14.emart.backendbchr.controller;
import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;


@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
//    @Value("${spring.route.gateway_url}")
//    private String GATEWAY_URL;


    private JwtService jwtService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        Long userId = jwtService.extractUserId(jwt);
        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(userId.toString());
        return ResponseEntity.ok(shoppingCart);
    }

    @GetMapping("/{pembeliId}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable String pembeliId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.getShoppingCart(pembeliId);
        return shoppingCart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-item")
    public ResponseEntity<ShoppingCart> addItemToCart(
            @RequestHeader("Authorization") String token,
            @RequestParam String productId,
            @RequestParam String supermarketId) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        Long userId = jwtService.extractUserId(jwt);
        ShoppingCart shoppingCart = shoppingCartService.addItemToCart(userId.toString(), productId, supermarketId);
        return ResponseEntity.ok(shoppingCart);
    }

    @DeleteMapping("/remove-item")
    public ResponseEntity<ShoppingCart> removeItemFromCart(
            @RequestHeader("Authorization") String token,
            @RequestParam String productId) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        Long userId = jwtService.extractUserId(jwt);
        ShoppingCart shoppingCart = shoppingCartService.removeItemFromCart(userId.toString(), productId);
        return ResponseEntity.ok(shoppingCart);
    }

}