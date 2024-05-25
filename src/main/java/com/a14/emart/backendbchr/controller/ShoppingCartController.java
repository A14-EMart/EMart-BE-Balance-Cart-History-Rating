package com.a14.emart.backendbchr.controller;
import com.a14.emart.backendbchr.dto.CreateShoppingCartRequest;
import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;


@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private  ProductService productService;
//    @PostMapping("/create")
//    public ResponseEntity<ShoppingCart> createShoppingCart(
//            @RequestHeader(value = "Authorization") String token) throws IllegalAccessException {
//        String jwt = token.replace("Bearer ", "");
//        if (!jwtService.extractRole(jwt).equalsIgnoreCase("customer")) {
//            throw new IllegalAccessException("You have no access.");
//        }
//        Long userId = jwtService.extractUserId(jwt);
//        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(userId);
//        return ResponseEntity.ok(shoppingCart);
//    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ShoppingCart>> createShoppingCart(
            @RequestParam("userId") Long userId,
            @RequestHeader("Authorization") String token) {
        try {
            String tokenWithoutBearer = token.replace("Bearer ", "");
            String role = jwtService.extractRole(tokenWithoutBearer);

            if (!role.equalsIgnoreCase("customer")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse<>(false, null, "You have no access."));
            }

            ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(userId);
            return ResponseEntity.ok(new ApiResponse<>(true, shoppingCart, "Product added successfully"));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{pembeliId}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable Long pembeliId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.getShoppingCart(pembeliId);
        return shoppingCart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-item")
    public ResponseEntity<ShoppingCart> addItemToCart(
            @RequestHeader("Authorization") String token,
            @RequestParam String productId,
            @RequestParam String supermarketId) {
        String jwt = token.replace("Bearer ", "");   // Remove "Bearer " prefix
        Long userId = jwtService.extractUserId(jwt);
        ShoppingCart shoppingCart = shoppingCartService.addItemToCart(userId, productId, supermarketId);
        return ResponseEntity.ok(shoppingCart);
    }

    @DeleteMapping("/remove-item")
    public ResponseEntity<ShoppingCart> removeItemFromCart(
            @RequestHeader("Authorization") String token,
            @RequestParam String productId) {
        String jwt = token.replace("Bearer ", "");
        Long userId = jwtService.extractUserId(jwt);
        ShoppingCart shoppingCart = shoppingCartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok(shoppingCart);
    }

}